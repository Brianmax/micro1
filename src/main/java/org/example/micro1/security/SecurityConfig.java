package org.example.micro1.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Autowired
    private JwtConverter jwtConverter;
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf->csrf.disable())
                .authorizeHttpRequests(
                        http->http.anyRequest().authenticated())
                .oauth2ResourceServer(
                        oauth2->{
                            oauth2.jwt(
                                    jwt->jwt.jwtAuthenticationConverter(jwtConverter));
                        })
                .sessionManagement(
                        session->session.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS))
                .build();
    }
}