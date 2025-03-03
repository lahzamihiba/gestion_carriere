package com.hiba.gestion_carriere.service.utilisateur_service.candidature_service;

import com.hiba.gestion_carriere.exception.Candidature_execption.InvalidCandidatureException;
import com.hiba.gestion_carriere.exception.Candidature_execption.NotFoundCandidatureException;
import com.hiba.gestion_carriere.model.gestion_utilisateur.candidature.Candidat;
import com.hiba.gestion_carriere.model.gestion_utilisateur.candidature.Candidature;
import com.hiba.gestion_carriere.repository.utilisateur_rep.candidature_rep.CandidatureRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CandidatureService {
    @Autowired
    private CandidatureRepository candidatureRep;


    public Candidature findCandidatureById(Long id)  {
        return candidatureRep.findById(id)
                .orElseThrow(() -> new NotFoundCandidatureException("candidature  non trouvé avec l'ID : " + id));

    }

    public List<Candidature> findAllCandidature() {
        return candidatureRep.findAll();
    }

    @Transactional
    public Candidature save(Candidature candidature) {
        if (candidature.getCandidat() == null || candidature.getProfilDePoste() == null) {
            throw new InvalidCandidatureException("vérifier votre candidature/poste ");
        }
        if (candidature.getDateCandidature() == null || candidature.getDateCandidature().isAfter(LocalDate.now())) {
            throw new InvalidCandidatureException("vérrifier date");
        }
        return candidatureRep.save(candidature);
    }

    @Modifying
    @Transactional
    public Candidature update(Candidature candidature) {
        if (candidature.getCandidat() == null || candidature.getProfilDePoste() == null) {
            throw new InvalidCandidatureException("vérifier votre candidature/poste ");
        }
        if (candidature.getDateCandidature() == null || candidature.getDateCandidature().isAfter(LocalDate.now())) {
            throw new InvalidCandidatureException("vérrifier date");
        }
        Candidature cand = candidatureRep.findById(candidature.getIdCandidature())
                .orElseThrow(() -> new NotFoundCandidatureException("candidature  non trouvé avec l'ID : " + candidature.getIdCandidature()));

        cand.setDateCandidature(candidature.getDateCandidature());
        cand.setProfilDePoste(candidature.getProfilDePoste());
        cand.setCandidat(candidature.getCandidat());
        return candidatureRep.save(cand);

    }

    @Modifying
    @Transactional
    public void delete(Long id) {
        Candidature cand = candidatureRep.findById(id)
                .orElseThrow(() -> new NotFoundCandidatureException("candidature  non trouvé avec l'ID : " + id));
        candidatureRep.delete(cand);
    }
}