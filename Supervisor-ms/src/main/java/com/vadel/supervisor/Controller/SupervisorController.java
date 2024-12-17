package com.vadel.supervisor.Controller;

import com.vadel.supervisor.Entity.Supervisor;
import com.vadel.supervisor.Services.SupervisorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/supervisor")
public class SupervisorController {

    private final SupervisorService supervisorService;

    @GetMapping
    public List<Supervisor> getAllSupervisors() {
        return supervisorService.getAllSupervisors();
    }

    @GetMapping("/{id}")
    public Supervisor getSupervisor(@PathVariable Long id) {
        return supervisorService.getSupervisorById(id);
    }

    @PostMapping
    public Supervisor createSupervisor(@RequestBody Supervisor supervisor) {
        return supervisorService.saveSupervisor(supervisor);
    }

    @DeleteMapping("/{id}")
    public void deleteSupervisor(@PathVariable Long id) {
        supervisorService.deleteSupervisor(id);
    }
}
