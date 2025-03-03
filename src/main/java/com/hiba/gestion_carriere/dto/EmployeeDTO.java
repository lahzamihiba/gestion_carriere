package com.hiba.gestion_carriere.dto;

import com.hiba.gestion_carriere.model.contrat.Contrat;
import com.hiba.gestion_carriere.model.gestion_utilisateur.Stagiaire;
import com.hiba.gestion_carriere.model.gestion_utilisateur.affectation_employee.Employee;
import com.hiba.gestion_carriere.model.poste.Poste;

import java.time.LocalDate;

public record EmployeeDTO(Long idUser, String cin, String numCNSS, LocalDate dateNaissance, Contrat contrat,Long posteId) {
    public Employee toEmployee() {
        Employee employee = new Employee();
        employee.setEmployeeEnable(true);
        employee.setCin(cin);
        employee.setContrat(contrat);
        employee.setNumCNSS(numCNSS);
        employee.setDateNaissance(dateNaissance);
        return employee;
    }

}
