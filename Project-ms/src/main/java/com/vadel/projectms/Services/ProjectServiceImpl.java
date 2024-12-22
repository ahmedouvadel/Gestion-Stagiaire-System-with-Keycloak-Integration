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
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    private final SupervisorRestClient supervisorClient;
    private final StagiaireRestClient stagiaireClient;

    @Override
    public Project getProjectById(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found with ID: " + id));

        if (project.getSupervisorId() != null) {
            try {
                Supervisor supervisor = supervisorClient.getSupervisorById(project.getSupervisorId());
                project.setSupervisor(supervisor);
            } catch (Exception e) {
                throw new RuntimeException("Failed to fetch Supervisor with ID: " + project.getSupervisorId(), e);
            }
        }

        if (project.getStagiaireId() != null) {
            try {
                Stagiaire stagiaire = stagiaireClient.getStagiaireById(project.getStagiaireId());
                project.setStagiaire(stagiaire);
            } catch (Exception e) {
                throw new RuntimeException("Failed to fetch Stagiaire with ID: " + project.getStagiaireId(), e);
            }
        }

        return project;
    }

    @Override
    public boolean existsById(Long id) {
        return projectRepository.existsById(id);
    }

    @Override
    public Map<String, Object> getProjectWithDetails(Long projectId) {
        Project project = getProjectById(projectId);

        Map<String, Object> response = new HashMap<>();
        response.put("project", project);

        if (project.getSupervisorId() != null) {
            try {
                Supervisor supervisor = supervisorClient.getSupervisorById(project.getSupervisorId());
                response.put("supervisor", supervisor);
            } catch (Exception e) {
                throw new RuntimeException("Failed to fetch Supervisor with ID: " + project.getSupervisorId(), e);
            }
        }

        if (project.getStagiaireId() != null) {
            try {
                Stagiaire stagiaire = stagiaireClient.getStagiaireById(project.getStagiaireId());
                response.put("stagiaires", stagiaire);
            } catch (Exception e) {
                throw new RuntimeException("Failed to fetch Stagiaire with ID: " + project.getStagiaireId(), e);
            }
        }

        return response;
    }

    @Override
    public List<Project> getAllProjects() {
        List<Project> projects = projectRepository.findAll();

        return projects.stream().map(project -> {
            if (project.getSupervisorId() != null) {
                try {
                    Supervisor supervisor = supervisorClient.getSupervisorById(project.getSupervisorId());
                    project.setSupervisor(supervisor);
                } catch (Exception e) {
                    throw new RuntimeException("Failed to fetch Supervisor with ID: " + project.getSupervisorId(), e);
                }
            }

            if (project.getStagiaireId() != null) {
                try {
                    Stagiaire stagiaire = stagiaireClient.getStagiaireById(project.getStagiaireId());
                    project.setStagiaire(stagiaire);
                } catch (Exception e) {
                    throw new RuntimeException("Failed to fetch Stagiaire with ID: " + project.getStagiaireId(), e);
                }
            }

            return project;
        }).collect(Collectors.toList());
    }
    @Override
    public Project createProject(Project project) {
        // Check if the supervisor exists using SupervisorRestClient
        if (project.getSupervisorId() != null) {
            Supervisor supervisor = supervisorClient.getSupervisorById(project.getSupervisorId());
            if (supervisor == null) {
                throw new RuntimeException("Supervisor with ID " + project.getSupervisorId() + " does not exist");
            }
            // Set the supervisor in the project
            project.setSupervisor(supervisor);
        } else {
            throw new RuntimeException("Supervisor ID cannot be null");
        }

        // Check if the stagiaire exists (if applicable)
        if (project.getStagiaireId() != null) {
            Stagiaire stagiaire = stagiaireClient.getStagiaireById(project.getStagiaireId());
            if (stagiaire == null) {
                throw new RuntimeException("Stagiaire with ID " + project.getStagiaireId() + " does not exist");
            }
            // Set the stagiaire in the project
            project.setStagiaire(stagiaire);
        } else {
            throw new RuntimeException("Stagiaire ID cannot be null");
        }

        // Save the project to the database
        return projectRepository.save(project);
    }


    @Override
    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }
}
