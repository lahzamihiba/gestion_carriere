package com.hiba.gestion_carriere.service.competence_service;

import com.hiba.gestion_carriere.exception.competence_exception.InvalidCompetenceException;
import com.hiba.gestion_carriere.model.competence.CompetenceAcquise;
import com.hiba.gestion_carriere.repository.competence_rep.CompetenceAcquiseRepository;
import com.hiba.gestion_carriere.repository.competence_rep.CompetenceRepository;
import com.hiba.gestion_carriere.repository.utilisateur_rep.UtilisateurRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CompetenceAcquiseService {
    @Autowired
    private  CompetenceAcquiseRepository competenceAcquiseRepository;
    @Autowired
    private  UtilisateurRepository utilisateurRepository;
    @Autowired
    private  CompetenceRepository competenceRepository;


    public List<CompetenceAcquise> getAllCompetenceAcquise() {
        return competenceAcquiseRepository.findAll();
    }

    // Récupérer toutes les compétences acquises par un utilisateur spécifique
    public List<CompetenceAcquise> getCompetencesAcquisesByUtilisateurId(Long utilisateurId) {
        return competenceAcquiseRepository.findByUtilisateurIdUser(utilisateurId);
    }

    public CompetenceAcquise getCompetenceAcquiseById(Long id) {
        return competenceAcquiseRepository.findById(id)
                .orElseThrow(() -> new InvalidCompetenceException("Compétence acquise non trouvée avec ID: " + id));
    }

    public CompetenceAcquise addCompetenceAcquise(CompetenceAcquise competenceAcquise) {
        // Vérification de l'existence de l'utilisateur et de la compétence
        var utilisateur = utilisateurRepository.findById(competenceAcquise.getUtilisateur().getIdUser())
                .orElseThrow(() -> new InvalidCompetenceException("Utilisateur non trouvé avec ID: " + competenceAcquise.getUtilisateur().getIdUser()));
        var competence = competenceRepository.findById(competenceAcquise.getCompetence().getIdCompetence())
                .orElseThrow(() -> new InvalidCompetenceException("Compétence non trouvée avec ID: " + competenceAcquise.getCompetence().getIdCompetence()));

        CompetenceAcquise comp = new CompetenceAcquise(utilisateur, competence, competenceAcquise.getDate(), competenceAcquise.getNiveau());
        return competenceAcquiseRepository.save(competenceAcquise);
    }

    // Vérifier si une compétence est déjà acquise par un utilisateur
    public boolean isCompetenceAcquise(Long utilisateurId, Long competenceId) {
        return competenceAcquiseRepository.existsByUtilisateurIdUserAndCompetenceIdCompetence(utilisateurId, competenceId);
    }

    // Supprimer une compétence acquise par un utilisateur
    public void deleteCompetenceAcquise(Long utilisateurId, Long competenceId) {
        competenceAcquiseRepository.deleteByUtilisateurIdUserAndCompetenceIdCompetence(utilisateurId, competenceId);
    }
}
