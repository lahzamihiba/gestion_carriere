package com.hiba.gestion_carriere.service.formation_service;

import com.hiba.gestion_carriere.dto.FormationDTO;
import com.hiba.gestion_carriere.exception.formation_exception.InvalidFormationException;
import com.hiba.gestion_carriere.exception.formation_exception.NotFoundFormationException;
import com.hiba.gestion_carriere.exception.test_exception.NotFoundTestException;
import com.hiba.gestion_carriere.model.competence.Competence;
import com.hiba.gestion_carriere.model.formation.Formation;
import com.hiba.gestion_carriere.model.test.Test;
import com.hiba.gestion_carriere.model.test.TestDefinition;
import com.hiba.gestion_carriere.repository.competence_rep.CompetenceRepository;
import com.hiba.gestion_carriere.repository.formation_rep.FormationRepository;
import com.hiba.gestion_carriere.service.competence_service.CompetenceService;
import com.hiba.gestion_carriere.service.test_service.TestService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class FormationService {
    @Autowired
    private FormationRepository formRep;
    @Autowired
    private TestService testService;
    @Autowired
    private CompetenceRepository compRep;
    @Autowired
    private FormationRepository formationRepository;

    /// ///
@Transactional
public Formation save(Formation formation,Set<Long> cmpIds) {
    if (formation.getIdFormation() == null) {
        throw new InvalidFormationException("La formation ne peut pas être null !");
    }

    if (formation.getDateD() == null || formation.getDateF() == null
            ||formation.getDateD().isBefore(LocalDateTime.now())||formation.getDateD().isAfter(formation.getDateF())
            ||formation.getDateF().isBefore(LocalDateTime.now()) ) {
        throw new InvalidFormationException("Vérifier dates !");
    }


   Set<Competence>competences = new HashSet<>();
   for(Long competenceID : cmpIds ) {
       if(competenceID!=null)
           competences.add(compRep.findById(competenceID).get());
   }

    formation.setCompetences(competences);
    return formRep.save(formation);
}



    @Transactional
    @Modifying
    public Formation update(Formation formation,Set<Long> cmpIds) {
        if (formation == null) {
            throw new InvalidFormationException("La formation ne peut pas être null !");
        }
        Formation f = formationRepository.findById(formation.getIdFormation())
                .orElseThrow(() -> new NotFoundFormationException("Formation avec ID " + formation.getIdFormation() + " non trouvée"));

        if (formation.getDateD() == null || formation.getDateF() == null
                ||formation.getDateD().isBefore(LocalDateTime.now())||formation.getDateD().isAfter(formation.getDateF())
                ||formation.getDateF().isBefore(LocalDateTime.now()) ) {
            throw new InvalidFormationException("Vérifier dates !");
        }

        f.setDateD(formation.getDateD());
        f.setDateF(formation.getDateF());
        f.setDescription(formation.getDescription());
        f.setTitre(formation.getTitre());

        Set<Competence>competences = new HashSet<>();
        for(Long competenceID : cmpIds) {
            if(competenceID!=null)
                competences.add(compRep.findById(competenceID).get());
        }

        formation.setCompetences(competences);


        return formRep.save(f);
    }


    public List<Formation> findAll() {
        return formRep.findAll();
    }

    public Formation findById(Long id) {
        return formRep.findById(id).get();
    }

    public void delete(Long id) {
        formRep.deleteById(id);
    }

    public List<Formation> findByDateD(LocalDateTime date) {
        if(date==null) return null;
        return formRep.findByDateD(date);
    }



    /// /////////Ajouter une compétence à une formation
    public Formation addCompetenceToFormation(Long formationId, Long competenceId) {
        Formation formation = findById(formationId);
        Competence competence = compRep.findById(competenceId)
                .orElseThrow(() -> new NotFoundFormationException("Compétence avec ID " + competenceId + " non trouvée"));

        formation.getCompetences().add(competence);
        return formationRepository.save(formation);
    }

    //////Supprimer une compétence d'une formation
    public Formation removeCompetenceFromFormation(Long formationId, Long competenceId) {
        Formation formation = findById(formationId);
        Competence competence = compRep.findById(competenceId)
                .orElseThrow(() -> new NotFoundFormationException("Compétence avec ID " + competenceId + " non trouvée"));

        formation.getCompetences().remove(competence);
        return formationRepository.save(formation);
    }
}


