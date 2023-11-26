package com.example.fundyapi.controller.user;

import com.example.fundyapi.common.response.GlobalExceptionResponse;
import com.example.fundyapi.common.response.GlobalResponse;
import com.example.fundyapi.controller.user.dto.request.SignInRequest;
import com.example.fundyapi.controller.user.dto.request.SignUpRequest;
import com.example.fundyapi.service.user.UserUseCase;
import com.example.fundyapi.service.user.dto.request.SignInServiceRequest;
import com.example.fundyapi.service.user.dto.request.SignUpServiceRequest;
import com.example.fundyapi.service.user.dto.response.DuplicateNicknameResponse;
import com.example.fundyapi.service.user.dto.response.SignInResponse;
import com.example.fundydomain.repository.project.ProjectRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/user")
@Tag(name = "User", description = "User API")
@RequiredArgsConstructor
public class UserController {
    private final UserUseCase userUsecase;
    private final ProjectRepository projectRepository; // test

    @Operation(summary = "이메일 회원가입", description = "유저가 이메일로 회원가입")
    @ApiResponse(responseCode = "200", description = "성공", useReturnTypeSchema = true)
    @ApiResponse(responseCode = "400", description = "에러 발생",
        content = @Content(schema = @Schema(implementation = GlobalExceptionResponse.class)))
    @PostMapping("/sign-up")
    public final GlobalResponse<Long> signUp(@RequestBody final SignUpRequest request) {
        return GlobalResponse.<Long>builder()
            .message("유저 생성 완료")
            .result(userUsecase.signUp(SignUpServiceRequest.builder()
                    .email(request.getEmail())
                    .password(request.getPassword())
                    .nickname(request.getNickname())
                .build()))
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
            .result(userUsecase.checkDuplicateNickname(nickname))
            .build();
    }

    @Operation(summary = "유저 로그인", description = "로그인 하여 토큰 발생")
    @ApiResponse(responseCode = "200", description = "성공",
        useReturnTypeSchema = true)
    @ApiResponse(responseCode = "400", description = "에러 발생",
        content = @Content(schema = @Schema(implementation = GlobalExceptionResponse.class)))
    @PostMapping("/sign-in")
    public final GlobalResponse<SignInResponse> signIn(@RequestBody final SignInRequest request) {
        return GlobalResponse.<SignInResponse>builder()
            .message("토큰 생성 완료")
            .result(userUsecase.signIn(SignInServiceRequest.builder()
                    .email(request.getEmail())
                    .password(request.getPassword())
                .build()))
            .build();
    }
}
