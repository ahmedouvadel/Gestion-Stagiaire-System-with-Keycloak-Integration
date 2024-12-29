package com.vadel.projectms.Clients;

import com.vadel.projectms.Model.Stagiaire;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "stagiaire-service")
public interface StagiaireRestClient {
    @GetMapping("/api/stagiaire/{id}")
    Stagiaire getStagiaireById(@PathVariable Long id);
}
