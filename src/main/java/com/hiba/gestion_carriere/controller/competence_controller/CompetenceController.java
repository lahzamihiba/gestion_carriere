package com.hiba.gestion_carriere.controller.competence_controller;

import com.hiba.gestion_carriere.model.competence.Competence;
import com.hiba.gestion_carriere.service.competence_service.CompetenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //retournera les données directement au format JSON ou XML
@RequestMapping("/competence")
@CrossOrigin("*")//accessible depuis n'importe quelle origine de requette(#ports)

public class CompetenceController {
    @Autowired
    private CompetenceService competenceService;

    @GetMapping("/getAll")
    public List<Competence> getAllCompetences() {
        return competenceService.findAll();
    }

    @GetMapping("/getByID/{id}")
    public ResponseEntity<Competence> getCompetence(@PathVariable Long id) {
        Competence competence = competenceService.findById(id);
        if (competence == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(competence, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Competence> addCompetence(@RequestBody Competence competence) {
        Competence c=competenceService.addCompetence(competence);
        return new ResponseEntity<>(c, HttpStatus.CREATED);
    }

    @PatchMapping("/update")
    public ResponseEntity<Competence> updateCompetence(@RequestBody Competence competence) {
        Competence c=competenceService.updateCompetence(competence);
        if (c == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(competence, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Competence> deleteCompetence(@PathVariable Long id) {
        competenceService.deleteCompetence(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
