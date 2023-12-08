package com.example.core.utils.token.user;

import com.example.core.utils.token.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class UserTokenProviderImpl implements UserTokenProvider {
    private final Key accessKey;
    private final long accessDuration;
    private final String CLAIM_NAME = "authorities";

    public UserTokenProviderImpl(@Value("${token.user.access.key}") String accessSecret,
                             @Value("${token.user.access.duration}") String accessDuration) {
        this.accessKey = JwtUtil.parseKey(accessSecret);
        this.accessDuration = JwtUtil.parseDuration(accessDuration);
    }

    @Override
    public String generateToken(UserInfo userInfo) {
        Date now = new Date();
        Date accessExpirationDate = new Date(now.getTime() + accessDuration);

        return Jwts.builder()
            .setSubject(userInfo.getEmail())
            .claim(CLAIM_NAME,userInfo.getAuthorities())
            .setIssuedAt(now)
            .setExpiration(accessExpirationDate)
            .signWith(accessKey, SignatureAlgorithm.HS256)
            .compact();
    }

    @Override
    public Optional<UserInfo> getUserInfo(String accessToken) {
        Claims claims = JwtUtil.parseClaims(accessToken, accessKey).orElse(null);

        if (claims == null)
            return Optional.empty();

        if(claims.get(CLAIM_NAME) == null)
            return Optional.empty();

        return Optional.of(UserInfo.builder()
            .email(claims.getSubject())
            .authorities((List<String>) claims.get(CLAIM_NAME))
            .build());
    }

    @Override
    public boolean isVerifyAccessToken(String accessToken) {
        return JwtUtil.isVerifyToken(accessKey, accessToken);
    }
}
