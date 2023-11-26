package com.example.fundyapi.common.utils.token.user;

import com.example.fundyapi.common.utils.token.core.JwtUtil;
import com.example.fundyapi.common.utils.token.core.exception.NotVerifyTokenException;
import com.example.fundyapi.common.utils.token.user.dto.request.UserInfo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class UserTokenProvider {
    private final Key accessKey;
    private final long accessDuration;
    private final String CLAIM_NAME = "authorities";
    public UserTokenProvider(@Value("${token.user.access.key}") String accessSecret,
                             @Value("${token.user.access.duration}") String accessDuration) {
        this.accessKey = JwtUtil.parseKey(accessSecret);
        this.accessDuration = JwtUtil.parseDuration(accessDuration);
    }

    public String generateToken(String email, List<String> authorities) {
        Date now = new Date();
        Date accessExpirationDate = new Date(now.getTime() + accessDuration);

        String accessToken = Jwts.builder()
            .setSubject(email)
            .claim(CLAIM_NAME,authorities)
            .setIssuedAt(now)
            .setExpiration(accessExpirationDate)
            .signWith(accessKey, SignatureAlgorithm.HS256)
            .compact();

        return accessToken;
    }

    public Optional<UserInfo> getUserInfo(String accessToken) {
        try {
            Claims claims = JwtUtil.parseClaims(accessToken, accessKey);
            if(claims.get(CLAIM_NAME) == null)
                return Optional.empty();

            return Optional.of(UserInfo.builder()
                .email(claims.getSubject())
                .authorities((List<String>) claims.get(CLAIM_NAME))
                .build());
        } catch (NotVerifyTokenException | ExpiredJwtException e) {
            return Optional.empty();
        }
    }

    public boolean isVerifyAccessToken(String accessToken) {
        return JwtUtil.isVerifyToken(accessKey, accessToken);
    }
}
