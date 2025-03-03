package com.hiba.gestion_carriere.controller.user_controller;

import com.hiba.gestion_carriere.model.gestion_utilisateur.Utilisateur;
import com.hiba.gestion_carriere.service.utilisateur_service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/utilisateurs")
@CrossOrigin("*")  // Permet l'accès depuis Angular
public class UtilisateurController {

    @Autowired
    private UtilisateurService utilisateurService;

    //  Ajouter un utilisateur
    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody Utilisateur utilisateur) {
        try {
            Utilisateur savedUser = utilisateurService.save(utilisateur);
            return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    //  Récupérer un utilisateur par ID
    @GetMapping("/get/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try {
            Utilisateur utilisateur = utilisateurService.findById(id);
            return (utilisateur != null) ? new ResponseEntity<>(utilisateur, HttpStatus.OK)
                    : new ResponseEntity<>("Utilisateur non trouvé", HttpStatus.NOT_FOUND);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    //  Récupérer tous les utilisateurs
    @GetMapping("/getAll")
    public ResponseEntity<List<Utilisateur>> getAll() {
        List<Utilisateur> utilisateurs = utilisateurService.findAll();
        return new ResponseEntity<>(utilisateurs, HttpStatus.OK);
    }

    //  Mettre à jour un utilisateur
    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody Utilisateur utilisateur) {
        try {
            Utilisateur updatedUser = utilisateurService.update(utilisateur);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    //  Supprimer un utilisateur
    @PatchMapping("/disable/{id}")
    public ResponseEntity<?> disableUser(@PathVariable Long id) {
        try {
            utilisateurService.disable(id);
            return new ResponseEntity<>("Utilisateur supprimé avec succès", HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
