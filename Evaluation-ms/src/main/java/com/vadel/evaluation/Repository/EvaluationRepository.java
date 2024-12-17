package com.vadel.evaluation.Repository;

import com.vadel.evaluation.Entity.Evaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {
    List<Evaluation> findByStagiaireId(Long stagiaireId);
    List<Evaluation> findByProjectId(Long projectId);
}
