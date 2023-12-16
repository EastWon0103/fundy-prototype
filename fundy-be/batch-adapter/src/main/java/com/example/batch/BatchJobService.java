package com.example.batch;

import com.example.core.application.intermiddle.user.AccountConnector;
import com.example.core.application.project.input.GetProjectInfoUseCase;
import com.example.core.application.project.input.SetProjectUseCase;
import com.example.core.application.project.input.dto.res.NotTransactedProjectResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BatchJobService {
    private final GetProjectInfoUseCase getProjectInfoUseCase;
    private final AccountConnector accountConnector;
    private final SetProjectUseCase setProjectUseCase;

    public void execute() {
        List<NotTransactedProjectResponse> projects = getProjectInfoUseCase.getNotTransactedProject();

        for (NotTransactedProjectResponse project: projects) {
            accountConnector.depositById(project.getDepositAccountId(), project.getTotalFundingAmount());
            setProjectUseCase.setProjectTransactionEnd(project.getId());
        }
    }



    // TODO: 배치 프로그램 사용하지 않고 만들기
    /**
     * 0. 임의의 프로젝트 기간 만료 시키기
     * 1. 기간 완료된 프로젝트면서 자금조달이 되지 않은 프로젝트 가져오기
     * 2. 프로젝트에 관련된 모든 리워드 뽑기
     * 3. 리워드에 펀딩이 기록된 것 환불된 거 빼고 모두 찾기
     * 4. 기록된 펀딩 금액만큼 프로젝트 유저에 deposit
     * 5. 자금조달 완료 하기
     * */
}