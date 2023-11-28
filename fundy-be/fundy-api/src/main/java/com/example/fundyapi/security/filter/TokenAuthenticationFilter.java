package com.example.fundyapi.security.filter;

import com.example.fundyapi.common.utils.token.core.JwtUtil;
import com.example.fundyapi.common.utils.token.core.exception.NotVerifyTokenException;
import com.example.fundyapi.common.utils.token.user.UserTokenProvider;
import com.example.fundyapi.common.utils.token.user.dto.request.UserInfo;
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
        log.info("Token Filter");
        try {
            String accessToken = JwtUtil.resolveToken(request);
            if(isAvailableToken(accessToken))
                setAuthentication(accessToken);
        } catch (NotVerifyTokenException e) {
            log.info("Not Verify Token");
        } finally {
            filterChain.doFilter(request,response);
        }
    }

    private void setAuthentication(String accessToken) throws NotVerifyTokenException {
        UserInfo userInfo = userTokenProvider.getUserInfo(accessToken).orElseThrow(NotVerifyTokenException::createBasic);

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
}
