package com.hiba.gestion_carriere.controller.competence_controller;

import com.hiba.gestion_carriere.model.competence.CompetenceAcquise;
import com.hiba.gestion_carriere.service.competence_service.CompetenceAcquiseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/competenceAcquise")
@CrossOrigin("*")
public class CompetenceAcquiseController {


    private final CompetenceAcquiseService competenceAcquiseService;

    public CompetenceAcquiseController(CompetenceAcquiseService competenceAcquiseService) {

        this.competenceAcquiseService = competenceAcquiseService;
    }

    // Récupérer toutes les compétences acquises par un utilisateur spécifique
    @GetMapping("/getByUSERID/{utilisateurId}")
    public ResponseEntity<List<CompetenceAcquise>> getCompetencesAcquisesByUtilisateurId(@PathVariable Long utilisateurId) {
        List<CompetenceAcquise> competences = competenceAcquiseService.getCompetencesAcquisesByUtilisateurId(utilisateurId);
        return ResponseEntity.ok(competences);
    }

    // Récupérer une compétence acquise par son ID
    @GetMapping("/getByID/{id}")
    public ResponseEntity<CompetenceAcquise> getCompetenceAcquiseById(@PathVariable Long id) {
        return ResponseEntity.ok(competenceAcquiseService.getCompetenceAcquiseById(id));
    }

    // Ajouter une compétence acquise à un utilisateur
    @PostMapping("/add")
    public ResponseEntity<CompetenceAcquise> addCompetenceAcquise(@RequestBody CompetenceAcquise competenceAcquise) {
        return ResponseEntity.ok(competenceAcquiseService.addCompetenceAcquise(competenceAcquise));
    }

    // Vérifier si une compétence est déjà acquise par un utilisateur
    @GetMapping("/check")
    public ResponseEntity<Boolean> isCompetenceAcquise(@RequestParam Long utilisateurId,
                                                       @RequestParam Long competenceId) {
        return ResponseEntity.ok(competenceAcquiseService.isCompetenceAcquise(utilisateurId, competenceId));
    }

    // Supprimer une compétence acquise par un utilisateur
    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteCompetenceAcquise(@RequestParam Long utilisateurId,
                                                        @RequestParam Long competenceId) {
        competenceAcquiseService.deleteCompetenceAcquise(utilisateurId, competenceId);
        return ResponseEntity.noContent().build();
    }
}
