package com.example.fundyapi.common.utils.token.core;

import com.example.fundyapi.common.utils.token.core.exception.NotVerifyTokenException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.security.Key;

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

    public static Claims parseClaims(String token, Key key) throws NotVerifyTokenException, ExpiredJwtException {
        try {
            return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.info("Invalid JWT Token", e);
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT Token", e);
        } catch (IllegalArgumentException e) {
            log.info("JWT claims string is empty.", e);
        }
        throw NotVerifyTokenException.createBasic();
    }

    public static String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith(GRANT_TYPE)) {
            return bearerToken.substring(GRANT_TYPE.length()+1); // 띄어쓰기 때문에
        }

        return null;
    }
}
