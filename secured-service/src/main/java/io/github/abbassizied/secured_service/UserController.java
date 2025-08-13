package io.github.abbassizied.secured_service;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public String getUserMessage(@AuthenticationPrincipal Jwt jwt) {

        return String.format("Hello %s, You have standard user privileges", jwt.getClaimAsString("preferred_username"));
    }
}
