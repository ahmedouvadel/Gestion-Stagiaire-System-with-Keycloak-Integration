package com.vadel.stagiairems.Services;

import com.vadel.stagiairems.Entity.Stagiaire;

import java.util.List;
import java.util.Map;

public interface StagiaireService {

    Map<String, Object> getStagiaireWithProject(Long stagiaireId);
    List<Stagiaire> getAllStagiaires();
    Stagiaire getStagiaireById(Long id);
    boolean existsById(Long id);
    Stagiaire saveStagiaire(Stagiaire stagiaire);
    void deleteStagiaire(Long id);

}
