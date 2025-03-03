package com.hiba.gestion_carriere.repository.utilisateur_rep.affectation_empl_rep;

import com.hiba.gestion_carriere.model.gestion_utilisateur.affectation_employee.Affectation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AffectationRepository extends JpaRepository<Affectation,Long> {
}
