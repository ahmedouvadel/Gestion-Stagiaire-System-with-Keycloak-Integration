package com.vadel.projectms.Services;

import com.vadel.projectms.Clients.StagiaireRestClient;
import com.vadel.projectms.Clients.SupervisorRestClient;
import com.vadel.projectms.Entity.Project;
import com.vadel.projectms.Model.Stagiaire;
import com.vadel.projectms.Model.Supervisor;
import com.vadel.projectms.Repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    private final SupervisorRestClient supervisorClient;
    private final StagiaireRestClient stagiaireClient;

    @Override
    public Project getProjectById(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));
    }
    @Override
    public boolean existsById(Long id) {
        return projectRepository.existsById(id);
    }
    public Map<String, Object> getProjectWithDetails(Long projectId) {
        Project project = getProjectById(projectId);

        // Fetch Supervisor details
        Supervisor supervisor = supervisorClient.getSupervisorById(project.getSupervisorId());

        // Fetch Stagiaires assigned to this project
        List<Stagiaire> stagiaires = stagiaireClient.getStagiairesByProjectId(projectId);

        // Combine data into a Map
        Map<String, Object> response = new HashMap<>();
        response.put("project", project);
        response.put("supervisor", supervisor);
        response.put("stagiaires", stagiaires);

        return response;
    }

    @Override
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    @Override
    public Project createProject(Project project) {
        // Check if the supervisor exists using SupervisorRestClient
        if (project.getSupervisorId() != null) {
            Supervisor supervisor = supervisorClient.getSupervisorById(project.getSupervisorId());
            if (supervisor == null) {
                throw new RuntimeException("Supervisor with ID " + project.getSupervisorId() + " does not exist");
            }
            // Set the transient supervisor field for the response
            project.setSupervisor(supervisor);
        }

        // Save the project to the database
        return projectRepository.save(project);
    }
    @Override
    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }
}
