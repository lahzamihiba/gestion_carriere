package com.hiba.gestion_carriere.repository.formation_rep;

import com.hiba.gestion_carriere.model.formation.Formation;
import com.hiba.gestion_carriere.model.formation.InscriptionFormation;
import com.hiba.gestion_carriere.model.gestion_utilisateur.Utilisateur;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InscriptionFormationRepository extends JpaRepository<InscriptionFormation,Long> {
    boolean existsByUtilisateurAndFormation(@NonNull Utilisateur utilisateur, @NonNull Formation formation);

    List<InscriptionFormation> findByUtilisateurIdUser(Long utilisateurId);

    List<InscriptionFormation> findByFormationIdFormation(Long formationId);
}
