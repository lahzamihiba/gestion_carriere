package com.hiba.gestion_carriere.repository.utilisateur_rep;

import com.hiba.gestion_carriere.model.gestion_utilisateur.Stagiaire;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StagiaireRepository extends JpaRepository<Stagiaire, Long> {
}
