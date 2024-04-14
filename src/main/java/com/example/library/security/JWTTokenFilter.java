package com.example.library.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.micrometer.common.lang.NonNullApi;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

/**
 * A filter for JWT (JSON Web Token) authentication.
 * This filter intercepts each request and checks for the presence of a JWT in the Authorization header.
 * If a valid JWT is found, it sets up the security context with the user details contained in the JWT.
 */
@NonNullApi
public class JWTTokenFilter extends OncePerRequestFilter {

    /**
     * The key used to sign the JWT.
     */
    private String key;

    /**
     * Constructs a new JWTTokenFilter with the specified signing key.
     *
     * @param key the signing key
     */
    public JWTTokenFilter(String key) {
        this.key = key;
    }

    /**
     * Checks each request for a JWT in the Authorization header.
     * If a valid JWT is found, it sets up the security context with the user details contained in the JWT.
     *
     * @param request the HTTP request
     * @param response the HTTP response
     * @param filterChain the filter chain
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String headerAuth = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (headerAuth != null && headerAuth.startsWith("Bearer ")) {
            String token = headerAuth.split(" ")[1];
            Claims claims = Jwts.parser()
                    .setSigningKey(key)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
            String userId = String.valueOf(claims.get("id"));
            String role = (String) claims.get("role");
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    userId, null, List.of(new SimpleGrantedAuthority(role))
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
        } else {
            SecurityContextHolder.getContext().setAuthentication(null);
        }
        filterChain.doFilter(request, response); // always at the end of a filter!
    }
}
