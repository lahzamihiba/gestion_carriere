package com.hiba.gestion_carriere.controller.keycloak;

import com.hiba.gestion_carriere.service.KeycloakService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private  KeycloakService keycloakService;


    @GetMapping
    public List<Map<String, Object>> getUsers() {
        return keycloakService.getUsers();
    }
}
