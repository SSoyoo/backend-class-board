package com.soyoo.board.povider;


import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import javax.security.auth.Subject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtProvider {
    
    @Value("${jwt.secret-key}")
    private String SECRET_KEY;

    public String create(String email){
        Date expriedDate = Date.from((Instant.now()).plus(1, ChronoUnit.HOURS));
        
        String jwt = Jwts.builder() // 왜 어제는 subject고 오늘은 email이지?
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .setSubject(email).setIssuedAt(new Date()).setExpiration(expriedDate)
                .compact();

        return jwt;

    }
    
    // 검증
    public String validate(String jwt) {
        Claims claims = 
            Jwts.parser().setSigningKey(SECRET_KEY)
                .parseClaimsJws(jwt).getBody();

        return claims.getSubject();

    }

}
