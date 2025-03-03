package com.hiba.gestion_carriere.repository.competence_rep;

import com.hiba.gestion_carriere.model.competence.CompetenceAcquise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CompetenceAcquiseRepository extends JpaRepository<CompetenceAcquise, Long> {

   public boolean existsByUtilisateurIdUserAndCompetenceIdCompetence(Long utilisateurId, Long competenceId);

   @Modifying
   @Query("DELETE FROM CompetenceAcquise ca WHERE ca.utilisateur.idUser = :utilisateurId AND ca.competence.idCompetence = :competenceId")
   public void deleteByUtilisateurIdUserAndCompetenceIdCompetence(@Param("utilisateurId") Long utilisateurId, @Param("competenceId") Long competenceId);

   public List<CompetenceAcquise> findByUtilisateurIdUser(Long utilisateurId);
}
