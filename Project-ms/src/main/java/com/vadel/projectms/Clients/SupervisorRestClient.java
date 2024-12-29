package com.vadel.projectms.Clients;

import com.vadel.projectms.Model.Supervisor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "supervisor-service")
public interface SupervisorRestClient {
    @GetMapping("/api/supervisor/{id}")
    Supervisor getSupervisorById(@PathVariable Long id);
}
