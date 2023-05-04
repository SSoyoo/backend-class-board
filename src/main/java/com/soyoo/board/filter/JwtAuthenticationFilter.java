package com.soyoo.board.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.soyoo.board.povider.JwtProvider;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private JwtProvider jwtProvider;
    @Autowired
    public JwtAuthenticationFilter(JwtProvider jwtProvider){
        this.jwtProvider = jwtProvider;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

                try {
                    String jwt = paseToken(request);
                    boolean hasJwt = jwt !=null;
                    if(!hasJwt){ //null일 경우 필터체인 돌린 후 return
                        filterChain.doFilter(request, response);
                        return;
                    }

                    String email = jwtProvider.validate(jwt);
                    AbstractAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(email, null,AuthorityUtils.NO_AUTHORITIES);

                        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        
                        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
                        securityContext.setAuthentication(authenticationToken);

                        SecurityContextHolder.setContext(securityContext);;

                    
                } catch (Exception exception) {
                    exception.printStackTrace();
                }

                filterChain.doFilter(request, response);
       
    }

    //헤더에서 jwt토큰을 가져옴 
    private String paseToken(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        
        boolean hasToken = token != null && !token.equalsIgnoreCase("null");
        if(!hasToken) return null;
        
        boolean isBearer = token.startsWith("Bearer ");
        if(!isBearer) return null;

        String jwt = token.substring(7);
        return jwt;
    }
    

    
}
