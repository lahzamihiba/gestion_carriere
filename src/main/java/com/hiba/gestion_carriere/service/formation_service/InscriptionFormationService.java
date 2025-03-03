package com.hiba.gestion_carriere.service.formation_service;

import com.hiba.gestion_carriere.exception.formation_exception.InvalidFormationException;
import com.hiba.gestion_carriere.exception.formation_exception.NotFoundFormationException;
import com.hiba.gestion_carriere.exception.user_exception.NotFoundUserException;
import com.hiba.gestion_carriere.model.formation.Formation;
import com.hiba.gestion_carriere.model.formation.InscriptionFormation;
import com.hiba.gestion_carriere.model.formation.StatutFormation;
import com.hiba.gestion_carriere.model.gestion_utilisateur.Utilisateur;
import com.hiba.gestion_carriere.repository.formation_rep.FormationRepository;
import com.hiba.gestion_carriere.repository.formation_rep.InscriptionFormationRepository;
import com.hiba.gestion_carriere.repository.utilisateur_rep.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class InscriptionFormationService {

    @Autowired
    private InscriptionFormationRepository inscriptionFormationRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private FormationRepository formationRepository;

    // Ajouter une inscription
    @Transactional
    public InscriptionFormation inscrireUtilisateur(Long utilisateurId, Long formationId) {
        if (utilisateurId == null || formationId == null || utilisateurId <= 0 || formationId <= 0) {
            throw new InvalidFormationException("IDs utilisateur et formation invalides !");
        }

        Utilisateur utilisateur = utilisateurRepository.findById(utilisateurId)
                .orElseThrow(() -> new NotFoundUserException());

        Formation formation = formationRepository.findById(formationId)
                .orElseThrow(() -> new NotFoundFormationException("Formation non trouvée avec ID : " + formationId));

        // Vérifier si l'utilisateur est déjà inscrit à cette formation
        if (inscriptionFormationRepository.existsByUtilisateurAndFormation(utilisateur,formation)) {
            throw new InvalidFormationException("L'utilisateur est déjà inscrit à cette formation !");
        }

        InscriptionFormation inscription = new InscriptionFormation(utilisateur, formation, LocalDate.now(), StatutFormation.en_cours);
        return inscriptionFormationRepository.save(inscription);
    }

    //  Récupérer toutes les inscriptions
    public List<InscriptionFormation> getAllInscriptions() {
        return inscriptionFormationRepository.findAll();
    }

    //  Récupérer les inscriptions d'un utilisateur
    public List<InscriptionFormation> getInscriptionsByUtilisateur(Long utilisateurId) {
        if (utilisateurId == null || utilisateurId <= 0) {
            throw new InvalidFormationException("ID utilisateur invalide !");
        }
        return inscriptionFormationRepository.findByUtilisateurIdUser(utilisateurId);
    }

    //  Récupérer les inscriptions pour une formation
    public List<InscriptionFormation> getInscriptionsByFormation(Long formationId) {
        if (formationId == null || formationId <= 0) {
            throw new InvalidFormationException("ID formation invalide !");
        }
        return inscriptionFormationRepository.findByFormationIdFormation(formationId);
    }

    //  Mettre à jour le statut d'une inscription
    @Transactional
    public InscriptionFormation updateStatut(Long inscriptionId, StatutFormation statut) {
        if (inscriptionId == null || inscriptionId <= 0) {
            throw new InvalidFormationException("ID d'inscription invalide !");
        }

        InscriptionFormation inscription = inscriptionFormationRepository.findById(inscriptionId)
                .orElseThrow(() -> new NotFoundFormationException("Inscription non trouvée avec ID : " + inscriptionId));

        inscription.setStatutFormation(statut);
        return inscriptionFormationRepository.save(inscription);
    }

    //  Supprimer une inscription
    @Transactional
    @Modifying
    public void deleteInscription(Long inscriptionId) {
        if (inscriptionId == null || inscriptionId <= 0) {
            throw new InvalidFormationException("ID d'inscription invalide !");
        }

        if (!inscriptionFormationRepository.existsById(inscriptionId)) {
            throw new NotFoundFormationException("Inscription non trouvée avec ID : " + inscriptionId);
        }

        inscriptionFormationRepository.deleteById(inscriptionId);
    }
}
