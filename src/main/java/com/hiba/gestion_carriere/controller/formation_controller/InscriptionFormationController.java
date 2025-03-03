package com.hiba.gestion_carriere.controller.formation_controller;

import com.hiba.gestion_carriere.exception.formation_exception.InvalidFormationException;
import com.hiba.gestion_carriere.exception.formation_exception.NotFoundFormationException;
import com.hiba.gestion_carriere.model.formation.InscriptionFormation;
import com.hiba.gestion_carriere.model.formation.StatutFormation;
import com.hiba.gestion_carriere.service.formation_service.InscriptionFormationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inscriptions")
@CrossOrigin(origins = "*")
public class InscriptionFormationController {

    @Autowired
    private InscriptionFormationService inscriptionFormationService;

    @PostMapping("/add/{utilisateurId}/{formationId}")
    public ResponseEntity<?> inscrireUtilisateur(@PathVariable Long utilisateurId, @PathVariable Long formationId) {
        try {
            InscriptionFormation inscription = inscriptionFormationService.inscrireUtilisateur(utilisateurId, formationId);
            return ResponseEntity.ok(inscription);
        } catch (InvalidFormationException | NotFoundFormationException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Erreur lors de l'inscription.");
        }
    }


    @GetMapping("/getAll")
    public ResponseEntity<?> getAllInscriptions() {
        return ResponseEntity.ok(inscriptionFormationService.getAllInscriptions());
    }


    @GetMapping("/getByIDUSER/{utilisateurId}")
    public ResponseEntity<?> getInscriptionsByUtilisateur(@PathVariable Long utilisateurId) {
        return ResponseEntity.ok(inscriptionFormationService.getInscriptionsByUtilisateur(utilisateurId));
    }

    @GetMapping("/getAllByformation/{formationId}")
    public ResponseEntity<?> getInscriptionsByFormation(@PathVariable Long formationId) {
        return ResponseEntity.ok(inscriptionFormationService.getInscriptionsByFormation(formationId));
    }


    @PutMapping("/update/statut/{inscriptionId}/{statut}")
    public ResponseEntity<?> updateStatut(@PathVariable Long inscriptionId, @PathVariable StatutFormation statut) {
        try {
            InscriptionFormation updated = inscriptionFormationService.updateStatut(inscriptionId, statut);
            return ResponseEntity.ok(updated);
        } catch (InvalidFormationException | NotFoundFormationException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @DeleteMapping("/delete/{inscriptionId}")
    public ResponseEntity<?> deleteInscription(@PathVariable Long inscriptionId) {
        try {
            inscriptionFormationService.deleteInscription(inscriptionId);
            return ResponseEntity.ok("Inscription supprimée avec succès.");
        } catch (InvalidFormationException | NotFoundFormationException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
