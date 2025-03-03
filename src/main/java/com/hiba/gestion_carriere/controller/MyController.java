package com.hiba.gestion_carriere.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class MyController {

    @GetMapping("/roles")
    public Map<String, Object> getRoles(Authentication authentication) {
        return Map.of("authorities", authentication.getAuthorities());
    }


    @GetMapping("/public")
    public String publicEndpoint() {
        return "Ceci est un endpoint public.";
    }

    @GetMapping("/rh")
    public String userEndpoint() {
        return "Ceci est un endpoint pour les utilisateurs avec le rôle USER.";
    }

    @GetMapping("/admin")
    public String adminEndpoint() {
        return "Ceci est un endpoint pour les utilisateurs avec le rôle ADMIN.";
    }
}