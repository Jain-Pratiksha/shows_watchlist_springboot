package com.shows_watchlist.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.*;
import org.slf4j.*;
import org.springframework.stereotype.*;

import java.security.*;
import java.util.*;

@Component
public class JwtUtil {

    private static final String SECRET_KEY = "6dBeRJPP6R1D3MKF/s9Ok+/P0k1PupdkRkNkjPBf++w=";
    private static final long EXPIRATION_TIME = 5 * 60 * 1000; // 5 minutes
    private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);

    private final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

//    public static void main(String[] args) {
//    For generating a secret key, uncomment the following code block
//        try {
//            KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA256");
//            keyGenerator.init(256); // 256 bits
//            SecretKey secretKey = keyGenerator.generateKey();
//            String encodedKey = Base64.getEncoder().encodeToString(secretKey.getEncoded());
//            System.out.println("Generated secret key: " + encodedKey);
//        } catch (NoSuchAlgorithmException e) {
//            throw new RuntimeException(e);
//        }
//    }
    public String generateToken(String email, String role) {
        return Jwts.builder()
                .setSubject(email)
                .addClaims(Map.of("role", "ROLE_" + role))
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key)
                .compact();
    }

    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String extractEmail(String token) {
        return getClaims(token).getSubject();
    }

    public String extractRole(String token) {
        return getClaims(token).get("role", String.class);
    }

    public boolean validateToken(String token) {
        try {
            getClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            logger.error("JWT token is invalid: {}", e.getMessage());
            return false;
        }
    }
}
