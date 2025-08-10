package io.github.abbassizied.secured_service;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    
    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public String getUserMessage() {
        return "{\"message\": \"You have standard user privileges\"}";
    }    
}
