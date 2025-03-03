package com.hiba.gestion_carriere.repository.utilisateur_rep.candidature_rep;

import com.hiba.gestion_carriere.model.gestion_utilisateur.candidature.Candidat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidatRepository extends JpaRepository<Candidat, Long> {

}
