package com.example.api.security.filter;

import com.example.core.utils.token.JwtUtil;
import com.example.core.utils.token.user.UserInfo;
import com.example.core.utils.token.user.UserTokenProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class TokenAuthenticationFilter extends OncePerRequestFilter {
    private final UserTokenProvider userTokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String accessToken = resolveToken(request);
        if(isAvailableToken(accessToken))
            setAuthentication(accessToken);

        filterChain.doFilter(request,response);
    }

    private void setAuthentication(String accessToken) {
        UserInfo userInfo = userTokenProvider.getUserInfo(accessToken).orElse(null);
        if(userInfo == null)
            return;

        List<GrantedAuthority> authorities = userInfo.getAuthorities().stream()
            .map(SimpleGrantedAuthority::new).collect(Collectors.toList());

        UserDetails principal = new User(
            userInfo.getEmail(),
            "",
            authorities);
        Authentication authentication = new UsernamePasswordAuthenticationToken(principal, "", authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private boolean isAvailableToken(String token) {
        return token != null &&
            userTokenProvider.isVerifyAccessToken(token);
    }

    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith(JwtUtil.GRANT_TYPE)) {
            return bearerToken.substring(JwtUtil.GRANT_TYPE.length()+1); // 띄어쓰기 때문에
        }

        return null;
    }
}
