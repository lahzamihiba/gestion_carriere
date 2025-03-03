package com.hiba.gestion_carriere.model.autre.evaluation.evaluation_service;
/*
import com.hiba.gestion_carriere.model.formation.Formation;
import com.hiba.gestion_carriere.model.evaluation.ObjectifProfessionnel;
import com.hiba.gestion_carriere.model.autre.evaluation.Statut;
import com.hiba.gestion_carriere.model.test.Test;
import com.hiba.gestion_carriere.model.competence.Competence;
import com.hiba.gestion_carriere.repository.ObjectifProfessionnelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ObjectifProService {
    @Autowired
    private ObjectifProfessionnelRepository objRep;

    //find all
    public List<ObjectifProfessionnel> findAll() {
        return  objRep.findAll();
    }
//find by id
    public ObjectifProfessionnel findById(Long id) {
        Optional<ObjectifProfessionnel> obj = objRep.findById(id);
        return obj.get();
    }

    //ajouter objectif
public ObjectifProfessionnel addObjectif(LocalDate dateL,LocalDate dateDebut, String description, Set<Competence>competences, Set<Test>tests, Set<Formation>formations) {
        if(dateL==null || dateL.isBefore(LocalDate.now())||
        dateL.isBefore(dateDebut)||dateDebut.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("La date limite doit être > au date systéme");
        }
        if(description==null || competences==null || tests==null || formations==null) {
            return null;
        }
        for(Test test : tests) {
            if(test.getDateTest().toLocalDate().isAfter(dateL)||
            test.getDateTest().toLocalDate().isBefore(dateDebut)) {
                throw new IllegalArgumentException("vérifier date");            }
        }


        ObjectifProfessionnel obj = new ObjectifProfessionnel(description,dateDebut,dateL);
        obj.setCompetences(competences);
        obj.setTests(tests);
        obj.setFormations(formations);
       return objRep.save(obj);

}

//MAJ statut
public ObjectifProfessionnel MAJStatut(Long id, Statut statut){
        ObjectifProfessionnel obj=findById(id);
        obj.setStatut(statut);
        return objRep.save(obj);
}

//update objectif
    public ObjectifProfessionnel updateObjectif(ObjectifProfessionnel obj){
        if(obj==null) {
            return null;
        }
        if(obj.getCompetences()==null||obj.getDateLimite().isBefore(LocalDate.now())||
        obj.getDateDebut().isBefore(LocalDate.now())||obj.getFormations()==null) {
            return null;
        }
        //if(obj.getStatut()!=Statut.EN_ATTENTE) {}

        ObjectifProfessionnel objectifUpdate=findById(obj.getIdObjPro());
        objectifUpdate.setDateDebut(obj.getDateDebut());
        objectifUpdate.setDateLimite(obj.getDateLimite());
        objectifUpdate.setStatut(obj.getStatut());
        objectifUpdate.setCompetences(obj.getCompetences());
        objectifUpdate.setFormations(obj.getFormations());

        return objRep.save(objectifUpdate);
    }

    public void deleteObjectif(ObjectifProfessionnel obj) {
        objRep.delete(obj);
    }

}


 */