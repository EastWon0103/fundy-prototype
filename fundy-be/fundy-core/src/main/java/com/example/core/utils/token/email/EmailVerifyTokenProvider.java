package com.example.core.utils.token.email;

import com.example.core.utils.token.JwtUtil;
import com.example.core.utils.token.email.request.EmailVerifyRequest;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Slf4j
@Component
public class EmailVerifyTokenProvider {
    private final Key key;
    private final long accessDuration;

    public EmailVerifyTokenProvider(@Value("${token.email.key}") String key,
                                    @Value("${token.email.duration}") String duration) {
        this.key = JwtUtil.parseKey(key);
        this.accessDuration = JwtUtil.parseDuration(duration);
    }

    public String generateToken(final String email, final String verifyCode) {
        Date now = new Date();
        Date expirationDate = new Date(now.getTime()+accessDuration);

        return Jwts.builder()
            .setSubject(email)
            .claim("code", verifyCode)
            .setIssuedAt(now)
            .setExpiration(expirationDate)
            .signWith(key, SignatureAlgorithm.HS256)
            .compact();
    }

    public boolean isVerifyCodeAndToken(final EmailVerifyRequest request) {
        Claims claims = JwtUtil.parseClaims(request.getToken(), key).orElse(null);
        if (claims == null)
            return false;

        String email = claims.getSubject();
        String code = claims.get("code").toString();

        return request.getEmail().equals(email) &&
            request.getCode().equals(code);
    }
}
