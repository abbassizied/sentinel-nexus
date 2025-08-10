package io.github.abbassizied.secured_service;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public String getAdminMessage() {
        return "{\"message\": \"You have full administrative privileges\"}";
    }    
}
