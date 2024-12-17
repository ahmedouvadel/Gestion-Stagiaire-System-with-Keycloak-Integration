package com.vadel.evaluation.Clients;

import com.vadel.evaluation.Model.Project;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "project-service")
public interface ProjectRestClient {
    @GetMapping("/api/project/{id}")
    Project getProjectById(@PathVariable Long id);
    @GetMapping("/projects/{id}")
    Boolean existsProjectById(@PathVariable Long id);
}
