package com.hiba.gestion_carriere.repository.utilisateur_rep;

import com.hiba.gestion_carriere.model.gestion_utilisateur.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
}
