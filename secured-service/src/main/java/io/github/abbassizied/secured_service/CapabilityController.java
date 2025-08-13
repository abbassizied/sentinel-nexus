package io.github.abbassizied.secured_service;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/what-i-can-do")
public class CapabilityController {

    @GetMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN', 'DATA_ANALYST')")
    public String whatCanIGet(@AuthenticationPrincipal Jwt jwt) {

        return String.format("Hello %s, You can read data", jwt.getClaimAsString("preferred_username"));
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'DATA_ANALYST')")
    public String whatCanIPost(@AuthenticationPrincipal Jwt jwt) {

        return String.format("Hello %s, You can add new data", jwt.getClaimAsString("preferred_username"));
    }

    @PutMapping
    @PreAuthorize("hasRole('ADMIN')")
    public String whatCanIPut(@AuthenticationPrincipal Jwt jwt) {

        return String.format("Hello %s, You can update existing data", jwt.getClaimAsString("preferred_username"));
    }

    @DeleteMapping
    @PreAuthorize("hasRole('ADMIN')")
    public String whatCanIDelete(@AuthenticationPrincipal Jwt jwt) {

        return String.format("Hello %s, You can delete data", jwt.getClaimAsString("preferred_username"));
    }
}
