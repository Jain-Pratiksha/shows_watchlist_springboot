package com.shows_watchlist.config.security;

import com.shows_watchlist.utils.*;
import io.jsonwebtoken.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.*;
import org.springframework.security.core.authority.*;
import org.springframework.security.core.context.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.*;
import org.springframework.web.filter.*;

import java.io.*;
import java.util.*;

@Component
public class RequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    private static final Logger logger = LoggerFactory.getLogger(RequestFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        try {
            String authToken = request.getHeader("Authorization");

            if (authToken != null && authToken.startsWith("Bearer ")) {
                String token = authToken.substring(7);
                if (jwtUtil.validateToken(token)) {
                    String email = jwtUtil.extractEmail(token);
                    UserDetails userDetails = new User(email, "", new ArrayList<>());

                    String role = jwtUtil.extractRole(token); // e.g., ROLE_USER
                    List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(role));

                    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                            userDetails, null, authorities);
                    SecurityContextHolder.getContext().setAuthentication(auth);
                } else {
                    logger.warn("JWT token is invalid or expired");
                    throw new JwtException("Invalid or expired JWT token");
                }
            }
            filterChain.doFilter(request, response);
        } catch (JwtException e) {
            logger.error("JWT token processing failed: {}", e.getMessage());
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("{\"error\": \"Unauthorized - Invalid or expired token\"}");
        }
        catch (Exception e) {
            logger.error("An error occurred while processing the JWT token: {}", e.getMessage());
        }

    }
}
