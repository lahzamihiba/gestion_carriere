package com.hiba.gestion_carriere.controller.formation_controller;

import com.hiba.gestion_carriere.dto.FormationDTO;
import com.hiba.gestion_carriere.exception.formation_exception.InvalidFormationException;
import com.hiba.gestion_carriere.exception.formation_exception.NotFoundFormationException;
import com.hiba.gestion_carriere.model.formation.Formation;
import com.hiba.gestion_carriere.service.formation_service.FormationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/formation")
@CrossOrigin("*") // Permet l'accès depuis n'importe quelle origine
public class FormationController {

    @Autowired
    private FormationService formationService;

    @GetMapping("/getByID/{id}")
    public ResponseEntity<?> getByID(@PathVariable Long id) {
        try {
            if (id == null || id <= 0) {
                throw new InvalidFormationException("ID de formation invalide !");
            }
            Formation formation = formationService.findById(id);
            return ResponseEntity.ok(formation);
        } catch (NotFoundFormationException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Erreur lors de la récupération de la formation.");
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() {
        try {
            List<Formation> formations = formationService.findAll();
            return ResponseEntity.ok(formations);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Impossible de récupérer les formations.");
        }
    }

    @GetMapping("/getByDate")
    public ResponseEntity<List<Formation>> getByDate(@RequestParam("date") String dateTimeStr) {
        try {
            LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr);
            List<Formation> formations = formationService.findByDateD(dateTime);
            return ResponseEntity.ok(formations);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null); // Gestion des erreurs de parsing
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> addFormation(@RequestBody FormationDTO formationDTO) {
        try {
            if (formationDTO == null || formationDTO.formation() == null) {
                throw new InvalidFormationException("La formation ne peut pas être null !");
            }
            Formation savedFormation = formationService.save(formationDTO.toFormation(),formationDTO.competences());
            if (savedFormation != null) {
                return ResponseEntity.ok(savedFormation);
            }
        } catch (InvalidFormationException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Une erreur est survenue lors de l'ajout de la formation.");
        }
        return null;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        if (id == null) {
            return ResponseEntity.badRequest().body("ID invalide !");
        }
        formationService.delete(id);
        return ResponseEntity.ok("Deleted Successfully");
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateFormation(@RequestBody FormationDTO formationDTO) {

            Formation updatedFormation = formationService.update(formationDTO.toFormation(),formationDTO.competences());
            if(updatedFormation != null) {
                return ResponseEntity.ok(updatedFormation);
            }

            return ResponseEntity.internalServerError().body("Erreur lors de la mise à jour de la formation.");
        }


    @PostMapping("/{formationId}/competences/{competenceId}")
    public ResponseEntity<?> addCompetenceToFormation(@PathVariable Long formationId, @PathVariable Long competenceId) {
        try {
            if (formationId == null || formationId <= 0 || competenceId == null || competenceId <= 0) {
                throw new InvalidFormationException("IDs invalides !");
            }
            Formation updatedFormation = formationService.addCompetenceToFormation(formationId, competenceId);
            return ResponseEntity.ok(updatedFormation);
        } catch (NotFoundFormationException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Erreur lors de l'ajout de la compétence.");
        }
    }

    @DeleteMapping("/{formationId}/competences/{competenceId}")
    public ResponseEntity<?> removeCompetenceFromFormation(@PathVariable Long formationId, @PathVariable Long competenceId) {
        try {
            if (formationId == null || formationId <= 0 || competenceId == null || competenceId <= 0) {
                throw new InvalidFormationException("IDs invalides !");
            }
            Formation updatedFormation = formationService.removeCompetenceFromFormation(formationId, competenceId);
            return ResponseEntity.ok(updatedFormation);
        } catch (NotFoundFormationException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Erreur lors de la suppression de la compétence.");
        }
    }
}
