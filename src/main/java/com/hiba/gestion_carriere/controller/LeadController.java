package com.hiba.gestion_carriere.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import java.util.Map;

@RestController
@RequestMapping("/api/leads")
public class LeadController {

    @GetMapping
    public Map<String, Object> getUserInfo(JwtAuthenticationToken token) {
        return token.getTokenAttributes(); // Retourne les infos de l'utilisateur connecté
    }
}
