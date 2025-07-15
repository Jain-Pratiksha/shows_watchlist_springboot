package com.shows_watchlist.config.security;

import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.web.builders.*;
import org.springframework.security.config.annotation.web.configurers.*;
import org.springframework.security.web.*;
import org.springframework.security.web.authentication.*;

@Configuration
public class SecurityConfig {

    @Autowired
    private RequestFilter requestFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable) // Disable CSRF for simplicity
                .authorizeHttpRequests(request -> request
                        // Allow public access to specific APIs
//                        .requestMatchers("/shows/**").hasRole("ADMIN")
//                        .requestMatchers("/watchlist/**").hasRole("USER")
                        .requestMatchers("/auth/**",
                                "/swagger-ui.html",
                                "/swagger-ui/**",
                                "/v3/api-docs/**")
                        .permitAll()
                        // Require authentication for all other endpoints
                        .anyRequest().authenticated())
                .addFilterBefore(requestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
