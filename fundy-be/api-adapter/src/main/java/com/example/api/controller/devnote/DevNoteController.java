package com.example.api.controller.devnote;

import com.example.api.common.response.GlobalResponse;
import com.example.api.controller.devnote.dto.req.SaveDevNoteCommentRequest;
import com.example.core.application.devnote.input.GetDevNoteCommentUseCase;
import com.example.core.application.devnote.input.GetDevNoteUseCase;
import com.example.core.application.devnote.input.SaveDevNoteCommentUseCase;
import com.example.core.application.devnote.input.dto.req.SaveDevNoteCommentServiceRequest;
import com.example.core.application.devnote.input.dto.res.DevNoteCommentResponse;
import com.example.core.application.devnote.input.dto.res.DevNoteDetailResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequiredArgsConstructor
@Tag(name = "DevNote", description = "개발노트 API")
@RequestMapping("/devnotes")
public class DevNoteController {
    private final GetDevNoteUseCase getDevNoteUseCase;
    private final GetDevNoteCommentUseCase getDevNoteCommentUseCase;
    private final SaveDevNoteCommentUseCase saveDevNoteCommentUseCase;

    @Operation(summary = "개발노트 조회", description = "개발노트 상세 조회")
    @ApiResponse(responseCode = "200", description = "성공", useReturnTypeSchema = true)
    @GetMapping("/{id}")
    public GlobalResponse<DevNoteDetailResponse> findById(
        @PathVariable(name = "id") final long devnoteId) {
        return GlobalResponse.<DevNoteDetailResponse>builder()
            .message("개발노트 조회 완료")
            .result(getDevNoteUseCase.getDevNoteDetail(devnoteId))
            .build();
    }

    @Operation(summary = "개발노트 댓글 조회", description = "개발노트 댓글 리스트 조회")
    @ApiResponse(responseCode = "200", description = "성공", useReturnTypeSchema = true)
    @GetMapping("/{devnoteId}/comments")
    public final GlobalResponse<List<DevNoteCommentResponse>> getAllDevNoteComments(@PathVariable(name = "devnoteId") final long devnoteId) {
        return GlobalResponse.<List<DevNoteCommentResponse>>builder()
            .message("조회 완료")
            .result(getDevNoteCommentUseCase.getCommentByDevNote(devnoteId))
            .build();
    }

    @Operation(summary = "개발노트 댓글 저장", description = "댓글 저장",
        security = @SecurityRequirement(name = "bearerAuth"))
    @PostMapping("/{devnoteId}/comments")
    public final GlobalResponse<Long> saveDevNoteComment(
        @PathVariable(name = "devnoteId") final long devnoteId,
        @RequestBody final SaveDevNoteCommentRequest request,
        @AuthenticationPrincipal User user) {
        return GlobalResponse.<Long>builder()
            .message("개발노트댓글 생성")
            .result(saveDevNoteCommentUseCase.saveComment(SaveDevNoteCommentServiceRequest.builder()
                .devNoteId(devnoteId)
                .content(request.getContent())
                .writerEmail(user.getUsername())
                .build()))
            .build();
    }
}
