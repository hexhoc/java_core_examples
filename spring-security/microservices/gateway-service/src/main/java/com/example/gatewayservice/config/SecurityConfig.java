package com.example.gatewayservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain springSecurityFilterChain(HttpSecurity http) throws Exception {
        // any request that comes in must be authenticated,
        // and in the case of a not logged-in user, it should use the OAuth2 login page.
        http.csrf()
            .disable()
            .authorizeHttpRequests()
            .requestMatchers(HttpMethod.DELETE)
            .hasRole("ADMIN")
            .requestMatchers("/admin/**")
            .hasAnyRole("ADMIN")
            .requestMatchers("/user/**")
            .hasAnyRole("USER", "ADMIN")
            .requestMatchers("/login/**")
            .anonymous()
            .anyRequest()
            .authenticated()
            .and()
            .httpBasic()
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        return http.build();

    }
}