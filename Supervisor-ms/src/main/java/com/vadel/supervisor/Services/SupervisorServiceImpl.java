package com.vadel.supervisor.Services;

import com.vadel.supervisor.Entity.Supervisor;
import com.vadel.supervisor.Repository.SupervisorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SupervisorServiceImpl implements SupervisorService {

    private final SupervisorRepository supervisorRepository;
    @Override
    public List<Supervisor> getAllSupervisors() {
        return supervisorRepository.findAll();
    }
    @Override
    public Supervisor getSupervisorById(Long id) {
        return supervisorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Supervisor not found"));
    }
    @Override
    public Supervisor saveSupervisor(Supervisor supervisor) {
        return supervisorRepository.save(supervisor);
    }
    @Override
    public void deleteSupervisor(Long id) {
        supervisorRepository.deleteById(id);
    }
}
