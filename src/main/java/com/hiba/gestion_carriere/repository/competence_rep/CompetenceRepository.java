package com.hiba.gestion_carriere.repository.competence_rep;

import com.hiba.gestion_carriere.model.competence.Competence;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompetenceRepository extends JpaRepository<Competence, Long> {
}
