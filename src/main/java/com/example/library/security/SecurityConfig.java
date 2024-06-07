package com.example.library.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Configuration for web security.
 * This class sets up the security filter chain for HTTP requests.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * The key used for JWT (JSON Web Token) authentication.
     */
    @Value("${jwt.token.key}")
    private String key;

    /**
     * Configures the security filter chain for HTTP requests.
     * The filter chain includes a JWT token filter and authorization rules for different request matchers.
     *
     * @param http the HttpSecurity to modify
     * @return the configured SecurityFilterChain
     * @throws Exception if an error occurs
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .addFilterBefore(new JWTTokenFilter(key), UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(
                        authorizationManagerRequestMatcherRegistry ->
                                authorizationManagerRequestMatcherRegistry
                                        .requestMatchers("/login").permitAll()
                                        .requestMatchers("book/getAll").authenticated()
                                        .requestMatchers("book/getAllDetails").authenticated()
                                        .requestMatchers("book/getByAuthor").authenticated()
                                        .requestMatchers("book/getByIsbn").authenticated()
                                        .requestMatchers("book/getByTitle").authenticated()
                                        .requestMatchers("review/**").authenticated()
                                        .requestMatchers("rental/showHistory").authenticated()
                                        .requestMatchers("rental/add").hasRole("STAFF")
                                        .requestMatchers("rental/end/**").hasRole("STAFF")
                                        .requestMatchers("rental/getAll").hasRole("STAFF")
                                        .requestMatchers("/user/**").hasRole("STAFF")
                                        .requestMatchers("/book/add").hasRole("STAFF")
                                        .requestMatchers("book/update/**").hasRole("STAFF")
                                        .requestMatchers("/book/addDetails").hasRole("STAFF")
                                        .requestMatchers("book/delete/**").hasRole("STAFF")
                                        .requestMatchers("/error").anonymous()
                                        .requestMatchers(
                                                "/v1/api/get-token",
                                                "/swagger-ui.html",
                                                "/swagger-ui/*",
                                                "/v3/api-docs/**",
                                                "/swagger-resources/**",
                                                "/webjars/**").permitAll()
                )
                .sessionManagement(sessionManagement ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }
}
