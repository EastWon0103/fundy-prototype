package com.example.fundyapi.service.devnotecomment;

import com.example.fundyapi.service.devnotecomment.dto.request.SaveDevNoteCommentServiceRequest;
import com.example.fundyapi.service.devnotecomment.dto.response.DevNoteCommentResponse;
import com.example.fundyapi.service.devnotecomment.dto.response.DevNoteCommentUserInfoResponse;
import com.example.fundydomain.domain.devnote.DevNote;
import com.example.fundydomain.domain.devnotecomment.DevNoteComment;
import com.example.fundydomain.logic.devnote.DevNoteLogic;
import com.example.fundydomain.logic.devnotecomment.DevNoteCommentLogic;
import com.example.fundydomain.logic.user.FundyUserLogic;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class DevNoteCommentService implements DevNoteCommentUseCase {
    private final DevNoteCommentLogic devNoteCommentLogic;
    private final DevNoteLogic devNoteLogic;
    private final FundyUserLogic fundyUserLogic;
    @Override
    public List<DevNoteCommentResponse> getCommentByDevNote(long devNoteId) {
        DevNote devNote = devNoteLogic.findById(devNoteId).orElseThrow();
        List<DevNoteComment> devNoteComments = devNoteCommentLogic.findByDevNote(devNote);

        return devNoteComments.stream().map(devNoteComment ->
            DevNoteCommentResponse.builder()
                .id(devNoteComment.getId())
                .content(devNoteComment.getContent())
                .createdAt(devNoteComment.getCreatedAt())
                .writer(DevNoteCommentUserInfoResponse.builder()
                    .id(devNoteComment.getWriter().getId())
                    .nickname(devNoteComment.getWriter().getNickname())
                    .profile(devNoteComment.getWriter().getProfile())
                    .build())
                .build())
            .collect(Collectors.toList());
    }

    @Override
    public long saveComment(final SaveDevNoteCommentServiceRequest request) {
        DevNoteComment devNoteComment = DevNoteComment.builder()
            .devNote(devNoteLogic.findById(request.getDevNoteId()).orElseThrow())
            .content(request.getContent())
            .writer(fundyUserLogic.findByEmail(request.getWriterEmail()).orElseThrow())
            .build();
        return devNoteCommentLogic.saveDevNoteComment(devNoteComment);
    }
}
