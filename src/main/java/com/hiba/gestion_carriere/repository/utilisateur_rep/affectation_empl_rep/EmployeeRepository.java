package com.hiba.gestion_carriere.repository.utilisateur_rep.affectation_empl_rep;

import com.hiba.gestion_carriere.model.gestion_utilisateur.affectation_employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
