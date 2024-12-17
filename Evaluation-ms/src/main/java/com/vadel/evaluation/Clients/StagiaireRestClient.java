package com.vadel.evaluation.Clients;

import com.vadel.evaluation.Model.Stagiaire;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "stagiaire-service")
public interface StagiaireRestClient {
    @GetMapping("/api/stagiaires/{id}")
    Stagiaire getStagiaireById(@PathVariable Long id);
    @GetMapping("/stagiaires/{id}")
    Boolean existsStagiaireById(@PathVariable Long id);
}
