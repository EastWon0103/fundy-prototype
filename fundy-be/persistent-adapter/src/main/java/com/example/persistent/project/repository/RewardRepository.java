package com.example.persistent.project.repository;

import com.example.persistent.project.model.ProjectModel;
import com.example.persistent.project.model.RewardModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RewardRepository extends JpaRepository<RewardModel, Long> {
    List<RewardModel> findByProject(ProjectModel projectModel);
}
