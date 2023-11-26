package com.example.fundyapi.common.utils.token.email;

import com.example.fundyapi.common.utils.token.core.JwtUtil;
import com.example.fundyapi.common.utils.token.core.exception.NotVerifyTokenException;
import com.example.fundyapi.common.utils.token.email.dto.request.EmailVerifyRequest;
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
        try {
            Claims claims = JwtUtil.parseClaims(request.getToken(), key);
            String email = claims.getSubject();
            String code = claims.get("code").toString();

            return request.getEmail().equals(email) &&
                request.getCode().equals(code);

        } catch (NotVerifyTokenException e) {
            log.info("Email Code and Token Not vaild");
            return false;
        }
    }
}
