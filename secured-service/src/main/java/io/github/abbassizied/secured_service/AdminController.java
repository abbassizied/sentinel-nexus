package io.github.abbassizied.secured_service;

import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public String getAdminMessage(@AuthenticationPrincipal Jwt jwt) {
        return String.format("Hello %s, You have full administrative privileges",
                jwt.getClaimAsString("preferred_username"));
    }
}
