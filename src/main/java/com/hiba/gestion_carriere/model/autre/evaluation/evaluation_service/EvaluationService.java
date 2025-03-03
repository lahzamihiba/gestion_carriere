package com.hiba.gestion_carriere.model.autre.evaluation.evaluation_service;
/*
import com.hiba.gestion_carriere.model.evaluation.CleEvaluation;
import com.hiba.gestion_carriere.model.evaluation.Evaluation;
import com.hiba.gestion_carriere.model.gestion_test.Test;
import com.hiba.gestion_carriere.model.gestion_utilisateur.Utilisateur;
import com.hiba.gestion_carriere.repository.EvaluationRepository;
import com.hiba.gestion_carriere.repository.test_repo.TestRepository;
import com.hiba.gestion_carriere.repository.utilisateur_rep.UtilisateurRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EvaluationService {
    @Autowired
    private UtilisateurRepository userRep;

    @Autowired
    private TestRepository testRep;

    private  EvaluationRepository evaluationRepository;

    public EvaluationService(EvaluationRepository evaluationRepository) {
        this.evaluationRepository = evaluationRepository;
    }

    public Evaluation add(Long idUtilisateur, Long idTest, String commentaire, Double note) {
        Utilisateur utilisateur = userRep.findById(idUtilisateur)
                .orElseThrow(() -> new IllegalArgumentException("Utilisateur non trouvé"));
        Test test = testRep.findById(idTest)
                .orElseThrow(() -> new IllegalArgumentException("Test non trouvé"));

        CleEvaluation cle = new CleEvaluation(idTest, idUtilisateur);

        Optional<Evaluation> optionalEvaluation = evaluationRepository.findByCle(cle);
        if(optionalEvaluation.isPresent()){
            throw new IllegalArgumentException("Test non trouvé");
        }
        Evaluation evaluation = new Evaluation(cle, utilisateur, test, note, commentaire);

        return evaluationRepository.save(evaluation);
    }

    public Evaluation modify(Long idUtilisateur, Long idTest, String commentaire, Double note) {
        Utilisateur utilisateur = userRep.findById(idUtilisateur)
                .orElseThrow(() -> new IllegalArgumentException("Utilisateur non trouvé"));
        Test test = testRep.findById(idTest)
                .orElseThrow(() -> new IllegalArgumentException("Test non trouvé"));

        CleEvaluation cle = new CleEvaluation(idTest, idUtilisateur);
        Evaluation evaluation = evaluationRepository.findByCle(cle).orElseThrow(() -> new IllegalArgumentException("Test non trouvé"));
            evaluation.setNote(note);
            evaluation.setCommentaire(commentaire);
        return evaluationRepository.save(evaluation);
    }

   @Transactional
   @Modifying
    public void deleteEvaluation(CleEvaluation cle) {
        Evaluation evaluation = evaluationRepository.findByCle(cle)
                .orElseThrow(() -> new IllegalArgumentException("Évaluation non trouvée"));
        evaluationRepository.delete(evaluation);
    }


    public List<Evaluation> getAllEvaluation() {
        return evaluationRepository.findAll();
    }


    public Evaluation getEvaluation(CleEvaluation cle) {
        return evaluationRepository.findByCle(cle)
                .orElseThrow(() -> new IllegalArgumentException("Évaluation non trouvée"));
    }
}
*/