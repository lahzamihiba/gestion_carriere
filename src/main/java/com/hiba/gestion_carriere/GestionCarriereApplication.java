package com.hiba.gestion_carriere;

import com.hiba.gestion_carriere.model.contrat.Contrat;
import com.hiba.gestion_carriere.model.contrat.TypeContrat;
import com.hiba.gestion_carriere.model.gestion_utilisateur.affectation_employee.Employee;
import com.hiba.gestion_carriere.model.poste.Departement;
import com.hiba.gestion_carriere.model.poste.Poste;
import com.hiba.gestion_carriere.repository.DepartementRepository;
import com.hiba.gestion_carriere.repository.PosteRepository;
import com.hiba.gestion_carriere.repository.contrat_rep.ContratRepository;
import com.hiba.gestion_carriere.repository.utilisateur_rep.affectation_empl_rep.EmployeeRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;

@SpringBootApplication
public class GestionCarriereApplication {

	public static void main(String[] args) {
	ApplicationContext ctx= SpringApplication.run(GestionCarriereApplication.class, args);
		/* tester l'ajout d'un contrat
		// ✅ Contrat valide pour un EMPLOYÉ
		Employee emp = new Employee();
		Contrat contratEmp = new Contrat(TypeContrat.CDI, true, LocalDate.now(), LocalDate.now().plusYears(1), 3000.0, emp, null);

// ✅ Contrat valide pour un STAGIAIRE
		Stagiaire stag = new Stagiaire();
		Contrat contratStag = new Contrat(TypeContrat.STAGE, true, LocalDate.now(), LocalDate.now().plusMonths(6), 800.0, null, stag);

// ❌ Erreur : un contrat de stage ne peut pas être associé à un employé
		Contrat contratInvalide = new Contrat(TypeContrat.STAGE, true, LocalDate.now(), LocalDate.now().plusMonths(6), 800.0, null, stag); // Exception levée
		*/
		DepartementRepository departementRepository=ctx.getBean(DepartementRepository.class);
		Departement itDept = new Departement("Informatique");
		departementRepository.save(itDept);

		Departement rhDept = new Departement("Ressources Humaines");
		departementRepository.save(rhDept);

		// Créer des postes
		Poste devPoste = new Poste(itDept);
		Poste hrManagerPoste = new Poste(rhDept);

		PosteRepository posteRepository=ctx.getBean(PosteRepository.class);
		posteRepository.save(devPoste);
		posteRepository.save(hrManagerPoste);

		Contrat contrat = new Contrat(TypeContrat.CDD, true, LocalDate.of(2025, 3, 1), LocalDate.of(2026, 3, 1), 220.0);
		ContratRepository contratRepository=ctx.getBean(ContratRepository.class);
		contratRepository.save(contrat);

	/*	Employee employe = new Employee(
				"keycloak-123", true, "hiba@gmail.com",
				"369", new Contrat(TypeContrat.CDD, true, LocalDate.of(2025, 3, 1), LocalDate.of(2026, 3, 1), 220.0), LocalDate.of(1990, 5, 15),
				"996633", true
		);
		EmployeeRepository employeeRepository=ctx.getBean(EmployeeRepository.class);
		employeeRepository.save(employe);
*/

	}

}
