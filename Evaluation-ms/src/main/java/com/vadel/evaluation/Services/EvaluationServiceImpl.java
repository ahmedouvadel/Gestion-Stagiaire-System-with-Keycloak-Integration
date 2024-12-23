package com.vadel.evaluation.Services;

import com.vadel.evaluation.Clients.ProjectRestClient;
import com.vadel.evaluation.Entity.Evaluation;
import com.vadel.evaluation.Model.Project;
import com.vadel.evaluation.Repository.EvaluationRepository;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class EvaluationServiceImpl implements EvaluationService {

    private final EvaluationRepository evaluationRepository;
    private final ProjectRestClient projectClient;

    @Override
    public Evaluation createEvaluation(Evaluation evaluation) {
        try {
            Project project = projectClient.getProjectById(evaluation.getProjectId());
            if (project == null) {
                throw new RuntimeException("Project with ID " + evaluation.getProjectId() + " does not exist");
            }
        } catch (FeignException.NotFound e) {
            throw new RuntimeException("Project with ID " + evaluation.getProjectId() + " does not exist");
        }

        // Save the evaluation to the repository
        return evaluationRepository.save(evaluation);
    }
    @Override
    public Map<String, Object> getEvaluationDetails(Long evaluationId) {
        Evaluation evaluation = evaluationRepository.findById(evaluationId)
                .orElseThrow(() -> new RuntimeException("Evaluation not found"));

        Project project = projectClient.getProjectById(evaluation.getProjectId());

        // Combine entities into a map
        Map<String, Object> response = new HashMap<>();
        response.put("evaluation", evaluation);
        response.put("project", project);

        return response;
    }
    @Override
    public List<Evaluation> getAllEvaluations() {
        return evaluationRepository.findAll();
    }
    @Override
    public Evaluation getEvaluationById(Long id) {
        // Fetch the evaluation by ID
        Evaluation evaluation = evaluationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evaluation not found"));

        // Use the RestClient to fetch the full project details
        if (evaluation.getProjectId() != null) {
            try {
                Project project = projectClient.getProjectById(evaluation.getProjectId());
                evaluation.setProject(project);
            } catch (FeignException.NotFound e) {
                throw new RuntimeException("Project with ID " + evaluation.getProjectId() + " does not exist");
            }
        }

        return evaluation;
    }
    @Override
    public void deleteEvaluation(Long id) {
        evaluationRepository.deleteById(id);
    }
}
