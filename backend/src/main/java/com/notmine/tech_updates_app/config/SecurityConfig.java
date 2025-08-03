package com.notmine.tech_updates_app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@org.springframework.context.annotation.Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authorize -> authorize
                                .requestMatchers(
                                        "/api/users/register",
                                        "/api/users/login",
                                        "/api/articles",
                                        "/api/users/*/bookmarks/**"    
                                ).permitAll()
                                .anyRequest().authenticated()
                );
        return http.build();
    }
}
