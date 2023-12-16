package com.example.batch;


import com.example.core.application.project.input.SetProjectUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/batch")
@RequiredArgsConstructor
@Slf4j
public class BatchController {
    private final SetProjectUseCase setProjectUseCase;
    private final BatchJobService batchJobService;


    @GetMapping("/execute")
    public ResponseEntity<String> handle(@RequestParam(name = "id") long id) {
        if(!setProjectUseCase.setProjectExpired(id))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("실행 실패");

        batchJobService.execute();
        return ResponseEntity.ok("배치 실행");
    }
}
