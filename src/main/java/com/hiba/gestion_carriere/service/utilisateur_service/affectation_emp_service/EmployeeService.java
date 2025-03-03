package com.hiba.gestion_carriere.service.utilisateur_service.affectation_emp_service;

import com.hiba.gestion_carriere.model.gestion_utilisateur.affectation_employee.Employee;
import com.hiba.gestion_carriere.repository.utilisateur_rep.affectation_empl_rep.EmployeeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    // Ajouter un employé
    @Transactional
    public Employee save(Employee employee) {
        if (employee == null) {
            throw new IllegalArgumentException("L'employé ne peut pas être null !");
        }
        if (employee.getCin() == null || employee.getCin().isEmpty()) {
            throw new IllegalArgumentException("Le CIN est obligatoire !");
        }
        if (employee.getNumCNSS() == null || employee.getNumCNSS().isEmpty()) {
            throw new IllegalArgumentException("Le numéro CNSS est obligatoire !");
        }
        if (employee.getDateNaissance() == null) {
            throw new IllegalArgumentException("La date de naissance est obligatoire !");
        }
        if (employee.getKeyCloakId() == null || employee.getKeyCloakId().isEmpty()) {
            throw new IllegalArgumentException("Le Keycloak ID est obligatoire !");
        }

        employee.getContrat().setEmployee(employee);
        return employeeRepository.save(employee);
    }

    // employer avec id
    public Employee findById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID invalide !");
        }
        Optional<Employee> employee = employeeRepository.findById(id);
        return employee.orElse(null);
    }

    //  Récupérer tous les employés
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    //  Mettre à jour un employé avec validation
    @Transactional
    @Modifying
    public Employee update(Employee employee) {
        if (employee == null || employee.getIdUser() == null) {
            throw new IllegalArgumentException("L'employé à mettre à jour doit avoir un ID !");
        }

        Employee existingEmployee = findById(employee.getIdUser());
        if (existingEmployee == null) {
            throw new IllegalArgumentException("Employé non trouvé !");
        }

        existingEmployee.setCin(employee.getCin());
        existingEmployee.setNumCNSS(employee.getNumCNSS());
        existingEmployee.setEmail(employee.getEmail());
        existingEmployee.setNumCNSS(employee.getNumCNSS());
        existingEmployee.setDateNaissance(employee.getDateNaissance());
        existingEmployee.setAffectations(employee.getAffectations());
        if(existingEmployee.getContrat()!=employee.getContrat())
        {
            existingEmployee.getContrat().setEmployee(employee);
            existingEmployee.setContrat(employee.getContrat());


        }
        existingEmployee.setKeyCloakId(employee.getKeyCloakId());
        existingEmployee.setTests(employee.getTests());

        return employeeRepository.save(existingEmployee);
    }

    // 🔹 Supprimer un employé avec validation
    @Transactional
    @Modifying
    public void disable(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID invalide !");
        }
        Employee employee = findById(id);
        employee.setEmployeeEnable(false);
        employee.setEnable(false);
       // employee.setKeyCloakId(null);

        employeeRepository.save(employee);
    }
}
