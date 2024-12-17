package com.vadel.stagiairems.Services;

import com.vadel.stagiairems.Clients.ProjectRestClient;
import com.vadel.stagiairems.Entity.Stagiaire;
import com.vadel.stagiairems.Model.Project;
import com.vadel.stagiairems.Repository.StagiaireRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class StagiaireServiceImpl implements StagiaireService {

    private final StagiaireRepository stagiaireRepository;

    private final ProjectRestClient projectClient;
    @Override
    public Stagiaire getStagiaireById(Long id) {
        return stagiaireRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Stagiaire not found"));
    }
    @Override
    public boolean existsById(Long id) {
        return stagiaireRepository.existsById(id);
    }
    @Override
    public Map<String, Object> getStagiaireWithProject(Long stagiaireId) {
        Stagiaire stagiaire = stagiaireRepository.findById(stagiaireId)
                .orElseThrow(() -> new RuntimeException("Stagiaire not found"));

        // Fetch project data dynamically using FeignClient
        Project project = projectClient.getProjectById(stagiaire.getProjectId());

        // Combine entities into a map (to avoid DTO)
        Map<String, Object> response = new HashMap<>();
        response.put("stagiaire", stagiaire);
        response.put("project", project);

        return response;
    }

    @Override
    public List<Stagiaire> getAllStagiaires() {
        return stagiaireRepository.findAll();
    }


    @Override
    public Stagiaire saveStagiaire(Stagiaire stagiaire) {
        return stagiaireRepository.save(stagiaire);
    }

    @Override
    public void deleteStagiaire(Long id) {
        stagiaireRepository.deleteById(id);
    }
}
