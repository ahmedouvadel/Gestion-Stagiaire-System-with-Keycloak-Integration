package com.vadel.projectms.Services;

import com.vadel.projectms.Entity.Project;

import java.util.List;
import java.util.Map;

public interface ProjectService {

    Map<String, Object> getProjectWithDetails(Long projectId);
    Project createProject(Project project);
    boolean existsById(Long id);
    List<Project> getAllProjects();
    Project getProjectById(Long id);
    void deleteProject(Long id);
}
