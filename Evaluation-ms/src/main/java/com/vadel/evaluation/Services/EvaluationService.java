package com.vadel.evaluation.Services;

import com.vadel.evaluation.Entity.Evaluation;

import java.util.List;
import java.util.Map;

public interface EvaluationService {
    Map<String, Object> getEvaluationDetails(Long evaluationId);
    Evaluation createEvaluation(Evaluation evaluation);
    List<Evaluation> getAllEvaluations();
    Evaluation getEvaluationById(Long id);
    void deleteEvaluation(Long id);
}
