package com.example.api.controller.user;

import com.example.api.common.response.GlobalExceptionResponse;
import com.example.api.common.response.GlobalResponse;
import com.example.api.controller.user.dto.req.SignInRequest;
import com.example.api.controller.user.dto.req.SignUpRequest;
import com.example.api.security.authentication.GetAuthentication;
import com.example.core.application.user.input.AuthUseCase;
import com.example.core.application.user.input.CheckDuplicateNicknameUseCase;
import com.example.core.application.user.input.GetUserInfoUseCase;
import com.example.core.application.user.input.dto.req.SignInServiceRequest;
import com.example.core.application.user.input.dto.req.SignUpServiceRequest;
import com.example.core.application.user.input.dto.res.DuplicateNicknameResponse;
import com.example.core.application.user.input.dto.res.SignInResponse;
import com.example.core.application.user.input.dto.res.UserInfoResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
@Tag(name = "User", description = "User API")
@RequiredArgsConstructor
public class UserController {
    private final AuthUseCase authUseCase;
    private final GetUserInfoUseCase getUserInfoUseCase;
    private final PasswordEncoder passwordEncoder;
    private final GetAuthentication authenticationGenerator;
    private final CheckDuplicateNicknameUseCase checkDuplicateNicknameUseCase;

    @Operation(summary = "이메일 회원가입", description = "유저가 이메일로 회원가입")
    @ApiResponse(responseCode = "200", description = "성공", useReturnTypeSchema = true)
    @ApiResponse(responseCode = "400", description = "에러 발생",
        content = @Content(schema = @Schema(implementation = GlobalExceptionResponse.class)))
    @PostMapping("/sign-up")
    public final GlobalResponse<Long> signUp(@RequestBody final SignUpRequest request) {
        return GlobalResponse.<Long>builder()
            .message("유저 생성 완료")
            .result(authUseCase.signUp(SignUpServiceRequest.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .nickname(request.getNickname())
                .build()))
            .build();
    }

    @Operation(summary = "유저 로그인", description = "로그인 하여 토큰 발생")
    @ApiResponse(responseCode = "200", description = "성공",
        useReturnTypeSchema = true)
    @ApiResponse(responseCode = "400", description = "에러 발생",
        content = @Content(schema = @Schema(implementation = GlobalExceptionResponse.class)))
    @PostMapping("/sign-in")
    public final GlobalResponse<SignInResponse> signIn(@RequestBody final SignInRequest request) {
        Authentication authentication = authenticationGenerator.getAuthentication(request.getEmail(), request.getPassword());
        List<String> authorities = authentication.getAuthorities()
            .stream().map(GrantedAuthority::getAuthority)
            .collect(Collectors.toList());

        return GlobalResponse.<SignInResponse>builder()
            .result(authUseCase.signIn(SignInServiceRequest.builder()
                    .email(request.getEmail())
                .authorities(authorities)
                .build()))
            .build();
    }

    @Operation(summary = "유저 정보 조회", description = "로그인한 유저 정보 조회",
        security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "성공", useReturnTypeSchema = true)
    @ApiResponse(responseCode = "401", description = "토큰 문제",
        content = @Content(schema = @Schema(implementation = GlobalExceptionResponse.class)))
    @GetMapping("/info")
    public final GlobalResponse<UserInfoResponse> getUserInfo(@AuthenticationPrincipal User user) {
        return GlobalResponse.<UserInfoResponse>builder()
            .message("유저 정보 조회")
            .result(getUserInfoUseCase.getUserInfoByEmail(user.getUsername()))
            .build();
    }

    @Operation(summary = "닉네임 중복 여부", description = "닉네임 중복 여부 검사")
    @ApiResponse(responseCode = "200", description = "성공",
        useReturnTypeSchema = true)
    @ApiResponse(responseCode = "400", description = "에러 발생",
        content = @Content(schema = @Schema(implementation = GlobalExceptionResponse.class)))
    @GetMapping("check-nickname")
    public final GlobalResponse<DuplicateNicknameResponse> checkDuplicateNickname(
        @Parameter(description = "타겟 닉네임", example = "유저-123")
        @RequestParam(name = "nickname") String nickname
    ) {
        return GlobalResponse.<DuplicateNicknameResponse>builder()
            .message("유저 닉네임 중복 API")
            .result(checkDuplicateNicknameUseCase.checkDuplicateNickname(nickname))
            .build();
    }
}