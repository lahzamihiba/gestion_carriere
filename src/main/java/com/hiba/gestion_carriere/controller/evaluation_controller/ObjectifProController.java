package com.hiba.gestion_carriere.controller.evaluation_controller;
/*
import com.hiba.gestion_carriere.model.formation.Formation;
//import com.hiba.gestion_carriere.model.evaluation.ObjectifProfessionnel;
import com.hiba.gestion_carriere.model.autre.evaluation.Statut;
import com.hiba.gestion_carriere.model.test.Test;
import com.hiba.gestion_carriere.model.competence.Competence;
import com.hiba.gestion_carriere.service.evaluation_service.ObjectifProService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@RestController //retournera les données directement au format JSON ou XML
@RequestMapping("/objectif")
@CrossOrigin("*")//accessible depuis n'importe quelle origine de requette(#ports)
public class ObjectifProController {
    @Autowired
    private ObjectifProService objectifProService;

    @GetMapping("/getAll")
    public List<ObjectifProfessionnel> getAll() {
        return objectifProService.findAll();
    }

    @PostMapping("/add")
    public ResponseEntity<ObjectifProfessionnel> add(@RequestBody LocalDate dateL, @RequestBody LocalDate dateDebut,@RequestBody String description,@RequestBody
    Set<Competence> competences,@RequestBody Set<Test>tests,@RequestBody Set<Formation>formations) {
        ObjectifProfessionnel objectifProfessionnel = objectifProService.addObjectif(dateL, dateDebut, description, competences, tests, formations);
        if (objectifProfessionnel == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(objectifProfessionnel, HttpStatus.CREATED);
    }

    @PatchMapping("/updateStatut/{id}")
    public ResponseEntity<ObjectifProfessionnel> updateStatut(@PathVariable Long id, @RequestBody Statut statut) {
        ObjectifProfessionnel obj=objectifProService.MAJStatut(id,statut);
        if (obj == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

    @PatchMapping("/update")
    public ResponseEntity<ObjectifProfessionnel>update (@RequestBody ObjectifProfessionnel obj) {
       ObjectifProfessionnel ob= objectifProService.updateObjectif(obj);
        if(ob == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(ob, HttpStatus.OK);
    }

}


 */