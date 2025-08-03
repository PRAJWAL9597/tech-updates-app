package com.notmine.tech_updates_app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@org.springframework.context.annotation.Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // For APIs, you often want CSRF disabled
                .authorizeHttpRequests(authorize -> authorize
                                .requestMatchers("/api/users/register", "/api/users/login").permitAll() // Allow public access to these
                                .anyRequest().authenticated() // Everything else needs authentication
                );
        return http.build();
    }
}
