package com.vadel.stagiairems.Clients;

import com.vadel.stagiairems.Model.Project;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "project-service")
public interface ProjectRestClient {
    @GetMapping("/api/project/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    Project getProjectById(@PathVariable Long id);
}
