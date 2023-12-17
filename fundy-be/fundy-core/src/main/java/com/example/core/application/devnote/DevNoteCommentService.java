package com.example.core.application.devnote;

import com.example.core.application.devnote.input.GetDevNoteCommentUseCase;
import com.example.core.application.devnote.input.SaveDevNoteCommentUseCase;
import com.example.core.application.devnote.input.dto.req.SaveDevNoteCommentServiceRequest;
import com.example.core.application.devnote.input.dto.res.DevNoteCommentResponse;
import com.example.core.application.devnote.input.dto.res.DevNoteCommentUserInfoResponse;
import com.example.core.application.devnote.output.FindDevNoteCommentPort;
import com.example.core.application.devnote.output.FindDevNotePort;
import com.example.core.application.devnote.output.SaveDevNoteCommentPort;
import com.example.core.application.devnote.output.dto.req.SaveDevNoteCommentPersistRequest;
import com.example.core.application.intermiddle.user.UserInfoConnector;
import com.example.core.application.intermiddle.user.dto.res.UserInfoConnectorResponse;
import com.example.core.utils.exception.CoreExceptionFactory;
import com.example.core.utils.exception.CoreExceptionType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DevNoteCommentService implements SaveDevNoteCommentUseCase, GetDevNoteCommentUseCase {
    private final FindDevNoteCommentPort findDevNoteCommentPort;
    private final FindDevNotePort findDevNotePort;
    private final SaveDevNoteCommentPort saveDevNoteCommentPort;
    private final UserInfoConnector userInfoConnector;

    @Override
    public List<DevNoteCommentResponse> getCommentByDevNote(long devNoteId) {
        return findDevNoteCommentPort.findByDevNoteId(devNoteId)
            .stream().map((devNoteComment) -> {
                UserInfoConnectorResponse ownerInfo = userInfoConnector.searchUserById(devNoteComment.getWriterId());

                return DevNoteCommentResponse.builder()
                    .id(devNoteComment.getId())
                    .content(devNoteComment.getContent())
                    .createdAt(devNoteComment.getCreatedAt())
                    .writer(DevNoteCommentUserInfoResponse.builder()
                        .id(ownerInfo.getId())
                        .nickname(ownerInfo.getNickname())
                        .profile(ownerInfo.getProfile())
                        .build())
                    .build();
            })
            .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public long saveComment(SaveDevNoteCommentServiceRequest request) {
        if (findDevNotePort.findById(request.getDevNoteId()).isEmpty())
            throw CoreExceptionFactory.createBasic(CoreExceptionType.NO_OBJECT);

        return saveDevNoteCommentPort.save(SaveDevNoteCommentPersistRequest.builder()
                .devNoteId(request.getDevNoteId())
                .writerId(userInfoConnector.searchUserByEmail(request.getWriterEmail()).getId())
                .content(request.getContent())
            .build());
    }
}
