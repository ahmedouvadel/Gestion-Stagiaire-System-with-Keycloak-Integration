package com.vadel.projectms.Controller;

import com.vadel.projectms.Entity.Project;
import com.vadel.projectms.Model.Stagiaire;
import com.vadel.projectms.Model.Supervisor;
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
    public ResponseEntity<Project> getProjectById(@PathVariable Long id) {
        try {
            Project project = projectService.getProjectById(id);
            return ResponseEntity.ok(project);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<Project>> getAllProjects() {
        List<Project> projects = projectService.getAllProjects();
        return ResponseEntity.ok(projects);
    }

    @GetMapping("/{id}/details")
    public ResponseEntity<Map<String, Object>> getProjectWithDetails(@PathVariable Long id) {
        try {
            Map<String, Object> projectDetails = projectService.getProjectWithDetails(id);
            return ResponseEntity.ok(projectDetails);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(null);
        }
    }

    // Check if a project exists by ID
    @GetMapping("/{id}/exists")
    public ResponseEntity<Boolean> existsProjectById(@PathVariable Long id) {
        boolean exists = projectService.existsById(id);
        return ResponseEntity.ok(exists);
    }

    // Get project with detailed information (supervisor and stagiaires)


    // Create a new project
    @PostMapping
    public ResponseEntity<?> createProject(@RequestBody Project project) {
        try {
            Project savedProject = projectService.createProject(project);
            return new ResponseEntity<>(savedProject, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", e.getMessage()));
        }
    }

    // Delete a project by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        try {
            projectService.deleteProject(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
