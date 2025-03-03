package com.hiba.gestion_carriere.controller.user_controller;

import com.hiba.gestion_carriere.model.gestion_utilisateur.affectation_employee.Employee;
import com.hiba.gestion_carriere.service.utilisateur_service.affectation_emp_service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
@CrossOrigin("*")  // Permet l'accès depuis Angular
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    //  Ajouter un employé
    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody Employee employee) {
        try {
            Employee savedEmployee = employeeService.save(employee);
            return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Récupérer un employé par ID
    @GetMapping("/get/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try {
            Employee employee = employeeService.findById(id);
            return (employee != null) ? new ResponseEntity<>(employee, HttpStatus.OK)
                    : new ResponseEntity<>("Employé non trouvé", HttpStatus.NOT_FOUND);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    //  Récupérer tous les employés
    @GetMapping("/getAll")
    public ResponseEntity<List<Employee>> getAll() {
        List<Employee> employees = employeeService.findAll();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    //  Mettre à jour un employé
    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody Employee employee) {
        try {
            Employee updatedEmployee = employeeService.update(employee);
            return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Supprimer un employé
    @PatchMapping("/disable/{id}")
    public ResponseEntity<?> disableEmployee(@PathVariable Long id) {
        try {
            employeeService.disable(id);
            return new ResponseEntity<>("Employé supprimé avec succès", HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
