package com.vadel.evaluation.Services;

import com.vadel.evaluation.Clients.ProjectRestClient;
import com.vadel.evaluation.Clients.StagiaireRestClient;
import com.vadel.evaluation.Entity.Evaluation;
import com.vadel.evaluation.Model.Project;
import com.vadel.evaluation.Model.Stagiaire;
import com.vadel.evaluation.Repository.EvaluationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class EvaluationServiceImpl implements EvaluationService {

    private final EvaluationRepository evaluationRepository;

    private final StagiaireRestClient stagiaireClient;


    private final ProjectRestClient projectClient;
    @Override
    public Evaluation createEvaluation(Evaluation evaluation) {
        // Verify if the Stagiaire exists
        if (!stagiaireClient.existsStagiaireById(evaluation.getStagiaireId())) {
            throw new RuntimeException("Stagiaire with ID " + evaluation.getStagiaireId() + " does not exist");
        }

        // Verify if the Project exists
        if (!projectClient.existsProjectById(evaluation.getProjectId())) {
            throw new RuntimeException("Project with ID " + evaluation.getProjectId() + " does not exist");
        }

        // Save the evaluation if both references are valid
        return evaluationRepository.save(evaluation);
    }
    @Override
    public Map<String, Object> getEvaluationDetails(Long evaluationId) {
        Evaluation evaluation = evaluationRepository.findById(evaluationId)
                .orElseThrow(() -> new RuntimeException("Evaluation not found"));

        Stagiaire stagiaire = stagiaireClient.getStagiaireById(evaluation.getStagiaireId());
        Project project = projectClient.getProjectById(evaluation.getProjectId());

        // Combine entities into a map
        Map<String, Object> response = new HashMap<>();
        response.put("evaluation", evaluation);
        response.put("stagiaire", stagiaire);
        response.put("project", project);

        return response;
    }
    @Override
    public List<Evaluation> getAllEvaluations() {
        return evaluationRepository.findAll();
    }
    @Override
    public Evaluation getEvaluationById(Long id) {
        return evaluationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evaluation not found"));
    }
    @Override
    public Evaluation saveEvaluation(Evaluation evaluation) {
        return evaluationRepository.save(evaluation);
    }
    @Override
    public void deleteEvaluation(Long id) {
        evaluationRepository.deleteById(id);
    }
}
