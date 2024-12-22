package com.vadel.projectms.Controller;

import com.vadel.projectms.Entity.Project;
import com.vadel.projectms.Repository.ProjectRepository;
import com.vadel.projectms.Services.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/project")
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping("/{id}")
    public ResponseEntity<Project> getProject(@PathVariable Long id) {
        Project project = projectService.getProjectById(id);
        return ResponseEntity.ok(project);
    }

    // 2. Check if Project Exists by ID (unique path)
    @GetMapping("/{id}/exists")
    public ResponseEntity<Boolean> existsProjectById(@PathVariable Long id) {
        boolean exists = projectService.existsById(id);
        return ResponseEntity.ok(exists);
    }
    @GetMapping("/{id}/details")
    public Map<String, Object> getProjectWithDetails(@PathVariable Long id) {
        return projectService.getProjectWithDetails(id);
    }

    @GetMapping
    public ResponseEntity<List<Project>> getAllProjects() {
        List<Project> projects = projectService.getAllProjects();
        return ResponseEntity.ok(projects);
    }

    @PostMapping
    public ResponseEntity<Project> createProject(@RequestBody Project project) {
        Project savedProject = projectService.createProject(project);
        return new ResponseEntity<>(savedProject, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public void deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
    }
}
