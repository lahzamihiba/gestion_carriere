package com.hiba.gestion_carriere.controller.user_controller;

import com.hiba.gestion_carriere.dto.EmployeeDTO;
import com.hiba.gestion_carriere.model.gestion_utilisateur.affectation_employee.Employee;
import com.hiba.gestion_carriere.model.gestion_utilisateur.Stagiaire;
import com.hiba.gestion_carriere.service.utilisateur_service.StagiaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/stagiaires")
@CrossOrigin("*")
public class StagiaireController {
@Autowired
    private  StagiaireService stagiaireService;

    // Récupérer tous les stagiaires
    @GetMapping("/getAll")
    public List<Stagiaire> getAllStagiaires() {
        return stagiaireService.getAllStagiaires();
    }

    // Récupérer un stagiaire par son ID
    @GetMapping("/getBYID/{id}")
    public ResponseEntity<Stagiaire> getStagiaireById(@PathVariable Long id) {
        Stagiaire stagiaire = stagiaireService.getStagiaireById(id);
        return ResponseEntity.ok(stagiaire);
    }

    // Sauvegarder un stagiaire (avec ou sans contrat)
    @PostMapping("/add")
    public ResponseEntity<Stagiaire> saveStagiaire(@RequestBody  Stagiaire stagiaire) {
        try {
            Stagiaire savedStagiaire = stagiaireService.saveStagiaire(stagiaire);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedStagiaire);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    // Mettre à jour un stagiaire
    @PutMapping("/update/{id}")
    public ResponseEntity<Stagiaire> updateStagiaire(@PathVariable Long id, @RequestBody  Stagiaire updatedStagiaire) {
        try {
            Stagiaire stagiaire = stagiaireService.updateStagiaire(id, updatedStagiaire);
            return ResponseEntity.ok(stagiaire);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Supprimer un stagiaire
    @PatchMapping("/disable/{id}")
    public ResponseEntity<?> DisableStagiaire(@PathVariable Long id) {

            stagiaireService.DisableStagiaire(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    // Transformer un stagiaire en employé
    @PostMapping("/transform")
    public ResponseEntity<Employee> transformToEmployee(@RequestBody EmployeeDTO employeeDTO) {
// todo employe
            Employee employee = employeeDTO.toEmployee();
//pour importrt les anciennes info des stagiaires:idStagiaire
            employee = stagiaireService.transformToEmployee(employeeDTO.idUser(),employee,employeeDTO.posteId());
            return ResponseEntity.status(HttpStatus.CREATED).body(employee);

            //Stagiaire s= employeeDTO.toStagiaire();



    }
}
