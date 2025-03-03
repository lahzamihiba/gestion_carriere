package com.hiba.gestion_carriere.controller.contrat_controller;

import com.hiba.gestion_carriere.model.contrat.Contrat;
import com.hiba.gestion_carriere.service.contrat_service.ContratService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contrat")
@CrossOrigin("*")
public class ContratController {

    @Autowired
    private ContratService contratService;

    @PostMapping("/add")
    public ResponseEntity<Contrat> add(@RequestBody Contrat contrat) {
        return new ResponseEntity<>(contratService.save(contrat), HttpStatus.CREATED);
    }

    @GetMapping("/getByID/{id}")
    public ResponseEntity<Contrat> getById(@PathVariable Long id) {
        return ResponseEntity.ok(contratService.findById(id));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Contrat>> getAll() {
        return ResponseEntity.ok(contratService.findAll());
    }

    @PutMapping("/update")
    public ResponseEntity<Contrat> update( @RequestBody Contrat contrat) {
        return ResponseEntity.ok(contratService.update(contrat));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        contratService.delete(id);
        return ResponseEntity.ok("Contrat supprimé avec succès !");
    }
}
