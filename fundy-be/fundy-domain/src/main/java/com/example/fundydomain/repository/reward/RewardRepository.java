package com.example.fundydomain.repository.reward;

import com.example.fundydomain.domain.project.Project;
import com.example.fundydomain.domain.reward.Reward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RewardRepository extends JpaRepository<Reward, Long> {
    List<Reward> findByProject(Project project);
}
