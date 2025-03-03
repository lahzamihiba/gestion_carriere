package com.hiba.gestion_carriere.controller.user_controller.candidat_controller;

import com.hiba.gestion_carriere.model.gestion_utilisateur.candidature.Candidature;
import com.hiba.gestion_carriere.service.utilisateur_service.candidature_service.CandidatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/candidatures")
@CrossOrigin("*")
public class CandidatureController {

    @Autowired
    private CandidatureService candidatureService;

    @GetMapping("/getAll")
    public ResponseEntity<List<Candidature>> getAllCandidatures() {
        return ResponseEntity.ok(candidatureService.findAllCandidature());
    }

    @GetMapping("/getByID/{id}")
    public ResponseEntity<Candidature> getCandidatureById(@PathVariable Long id) {
        return ResponseEntity.ok(candidatureService.findCandidatureById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<Candidature> createCandidature(@RequestBody Candidature candidature) {
        return ResponseEntity.ok(candidatureService.save(candidature));
    }

    @PutMapping("/update")
    public ResponseEntity<Candidature> updateCandidature(@RequestBody Candidature candidature) {
        candidatureService.update(candidature);
        return ResponseEntity.ok(candidatureService.update(candidature));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCandidature(@PathVariable Long id) {
        candidatureService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
