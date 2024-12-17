package com.vadel.supervisor.Services;

import com.vadel.supervisor.Entity.Supervisor;

import java.util.List;

public interface SupervisorService {
    List<Supervisor> getAllSupervisors();
    Supervisor getSupervisorById(Long id);
    Supervisor saveSupervisor(Supervisor supervisor);
    void deleteSupervisor(Long id);
}
