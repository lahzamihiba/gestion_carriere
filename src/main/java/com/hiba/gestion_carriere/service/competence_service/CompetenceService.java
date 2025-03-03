package com.hiba.gestion_carriere.service.competence_service;

import com.hiba.gestion_carriere.exception.competence_exception.InvalidCompetenceException;
import com.hiba.gestion_carriere.exception.stagiaire_exception.InvalidStagiaireException;
import com.hiba.gestion_carriere.model.competence.Competence;
import com.hiba.gestion_carriere.repository.competence_rep.CompetenceRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompetenceService {
@Autowired
    private CompetenceRepository competenceRepository;

    public List<Competence> findAll() {

        return competenceRepository.findAll();
    }

    public Competence findById(Long id) {
        if (id == null || id <= 0) {
            throw new InvalidCompetenceException("ID invalide pour competence");
        }
        return competenceRepository.findById(id).get();
    }

    @Transactional
    public Competence addCompetence(Competence competence) {

        return competenceRepository.save(competence);
    }

    @Transactional
    @Modifying
    public Competence updateCompetence(Competence competence) {
        Competence old = competenceRepository.findById(competence.getIdCompetence())
                .orElseThrow(() -> new InvalidCompetenceException("ID invalide pour competence"));
        if(old != null){
            old.setCommentaire(competence.getCommentaire());
            old.setLibelle(competence.getLibelle());
            old.setType(competence.getType());
            return competenceRepository.save(old);
        }
        return  competence;
    }
@Transactional
@Modifying
    public  void deleteCompetence(Long id) {
        Competence old = competenceRepository.findById(id).orElseThrow(() -> new InvalidCompetenceException("ID invalide pour competence"));
        competenceRepository.delete(old);
    }
}
