package com.example.core.utils.token;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

import java.security.Key;
import java.util.Optional;

@Slf4j
public class JwtUtil {
    public static String GRANT_TYPE = "Bearer";
    public static Key parseKey(String secretKey) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public static long parseDuration(String duration) {
        // s, h, d, m(30)
        long result = 0;

        StringBuilder num = new StringBuilder();
        for(String s: duration.split("")) {
            if(isInteger(s)) {
                num.append(s);
                continue;
            }

            long target = Long.parseLong(num.toString());
            switch (s) {
                case "s" -> target *= 1;
                case "m" -> target *= 60;
                case "h" -> target *= 60 * 60;
                case "d" -> target *= 24 * 60 * 60;
            }
            result+=target;
            num.setLength(0);
        }

        return result*1000; // parse second
    }

    private static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isVerifyToken(Key key, String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.info("Invalid JWT Token", e);
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT Token", e);
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT Token", e);
        } catch (IllegalArgumentException e) {
            log.info("JWT claims string is empty.", e);
        }
        return false;
    }

    public static Optional<Claims> parseClaims(String token, Key key) {
        try {
            return Optional.of(Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody());
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.info("Invalid JWT Token", e);
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT Token", e);
        } catch (IllegalArgumentException e) {
            log.info("JWT claims string is empty.", e);
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT Token", e);
        }
        return Optional.empty();
    }
}
