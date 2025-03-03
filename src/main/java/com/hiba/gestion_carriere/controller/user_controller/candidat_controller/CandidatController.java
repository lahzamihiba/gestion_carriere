package com.hiba.gestion_carriere.controller.user_controller.candidat_controller;

import com.hiba.gestion_carriere.dto.EmployeeDTO;
import com.hiba.gestion_carriere.model.contrat.Contrat;
import com.hiba.gestion_carriere.model.gestion_utilisateur.candidature.Candidat;
import com.hiba.gestion_carriere.model.competence.Competence;
import com.hiba.gestion_carriere.model.gestion_utilisateur.affectation_employee.Employee;
import com.hiba.gestion_carriere.model.gestion_utilisateur.Stagiaire;
import com.hiba.gestion_carriere.service.utilisateur_service.candidature_service.CandidatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/candidats")
@CrossOrigin("*")
public class CandidatController {
    @Autowired
    private  CandidatService candidatService;

    // Récupérer tous les candidats
    @GetMapping("/getAll")
    public List<Candidat> getAllCandidats() {
        return candidatService.getAllCandidats();
    }

    // Récupérer un candidat par son ID
    @GetMapping("/getByID/{id}")
    public ResponseEntity<Candidat> getCandidatById(@PathVariable Long id) {
       Candidat candidat = candidatService.getCandidatById(id);
        return ResponseEntity.ok(candidatService.getCandidatById(id));

    }

    // Sauvegarder un candidat
    @PostMapping("/add")
    public ResponseEntity<Candidat> saveCandidat(@RequestBody  Candidat candidat) {
        try {
            Candidat savedCandidat = candidatService.saveCandidat(candidat);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedCandidat);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    // Transformer un candidat en employé
 /*   @PostMapping(value = "/transform_employee", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Employee> transformToEmployee(@RequestBody  EmployeeDTO employeeDTO) {

        Employee employee = employeeDTO.toEmployee();

         employee = candidatService.transformToEmployee(employeeDTO.idUser(),
                    employee,
                    employeeDTO.posteId());

        return ResponseEntity.status(HttpStatus.CREATED).body(employee);
    }*/
    @PostMapping("/transform")
    public ResponseEntity<Employee> transformToEmployee(@RequestBody EmployeeDTO employeeDTO) {
// todo employe
        Employee employee = employeeDTO.toEmployee();
//pour importrt les anciennes info des stagiaires:idStagiaire
        employee = candidatService.transformToEmployee(employeeDTO.idUser(),employee,employeeDTO.posteId());
        return ResponseEntity.status(HttpStatus.CREATED).body(employee);

        //Stagiaire s= employeeDTO.toStagiaire();



    }

    // Transformer un candidat en stagiaire
    @PostMapping("/{id}/transform_stagiaire")
    public ResponseEntity<Stagiaire> transformToStagiaire(
            @PathVariable Long id,
            @RequestBody Contrat contrat) {
        try {
            Stagiaire stagiaire = candidatService.transformToStagiaire(id, contrat);
            return ResponseEntity.status(HttpStatus.CREATED).body(stagiaire);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PatchMapping("/disable/{id}")
    public ResponseEntity<Competence> disableCandidat(@PathVariable Long id) {
        candidatService.disableCandidat(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
