package com.soyoo.board.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.soyoo.board.filter.JwtAuthenticationFilter;


@EnableWebSecurity
@Configuration
public class WebSecurityConfig {

    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    public WebSecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    protected SecurityFilterChain config(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.cors().and()
                .csrf().disable()
                .httpBasic().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests().antMatchers("/jwt/**").permitAll() // 모든패턴 허용에서 바꿨음.
                .anyRequest().authenticated();

        httpSecurity.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }

}
