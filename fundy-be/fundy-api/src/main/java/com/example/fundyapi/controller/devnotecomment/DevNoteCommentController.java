package com.example.fundyapi.controller.devnotecomment;

import com.example.fundyapi.common.response.GlobalResponse;
import com.example.fundyapi.controller.devnotecomment.dto.request.SaveDevNoteCommentRequest;
import com.example.fundyapi.service.devnotecomment.DevNoteCommentUseCase;
import com.example.fundyapi.service.devnotecomment.dto.request.SaveDevNoteCommentServiceRequest;
import com.example.fundyapi.service.devnotecomment.dto.response.DevNoteCommentResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
@RequestMapping("/devnotes/{devnoteId}/comments")
public class DevNoteCommentController {
    private final DevNoteCommentUseCase devNoteCommentUseCase;

    @GetMapping
    public final GlobalResponse<List<DevNoteCommentResponse>> getAllDevNoteComments(@PathVariable(name = "devnoteId") final long devnoteId) {
        return GlobalResponse.<List<DevNoteCommentResponse>>builder()
            .message("조회 완료")
            .result(devNoteCommentUseCase.getCommentByDevNote(devnoteId))
            .build();
    }


    @Operation(summary = "개발노트 댓글 저장", description = "댓글 저장",
        security = @SecurityRequirement(name = "bearerAuth"))
    @PostMapping
    public final GlobalResponse<Long> saveDevNoteComment(
        @PathVariable(name = "devnoteId") final long devnoteId,
        @RequestBody final SaveDevNoteCommentRequest request,
        @AuthenticationPrincipal User user) {
        return GlobalResponse.<Long>builder()
            .message("개발노트댓글 생성")
            .result(devNoteCommentUseCase.saveComment(SaveDevNoteCommentServiceRequest.builder()
                    .devNoteId(devnoteId)
                    .content(request.getContent())
                    .writerEmail(user.getUsername())
                .build()))
            .build();
    }
}
