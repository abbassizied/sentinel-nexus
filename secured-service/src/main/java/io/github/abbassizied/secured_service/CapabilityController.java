package io.github.abbassizied.secured_service;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/what-i-can-do")
public class CapabilityController {

    @GetMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN', 'DATA_ANALYST')")
    public String whatCanIGet() {
        return "{\"message\": \"You can read data\"}";
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'DATA_ANALYST')")
    public String whatCanIPost() {
        return "{\"message\": \"You can add new data\"}";
    }

    @PutMapping
    @PreAuthorize("hasRole('ADMIN')")
    public String whatCanIPut() {
        return "{\"message\": \"You can update existing data\"}";
    }

    @DeleteMapping
    @PreAuthorize("hasRole('ADMIN')")
    public String whatCanIDelete() {
        return "{\"message\": \"You can delete data\"}";
    }    
}
