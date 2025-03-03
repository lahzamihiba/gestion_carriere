package com.hiba.gestion_carriere.service.test_service;

import com.hiba.gestion_carriere.dto.QuestionDTO;
import com.hiba.gestion_carriere.exception.test_exception.InvalidTestException;
import com.hiba.gestion_carriere.exception.test_exception.NotFoundTestException;
import com.hiba.gestion_carriere.model.test.QuestionDefinition;
import com.hiba.gestion_carriere.model.test.QuestionOptionDefinition;
import com.hiba.gestion_carriere.model.competence.Competence;
import com.hiba.gestion_carriere.repository.test_repo.QuestionDefinitionRepository;
import com.hiba.gestion_carriere.repository.competence_rep.CompetenceRepository;
import com.hiba.gestion_carriere.repository.test_repo.QuestionOptionDefinitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class QuestionDefinitionService {

    @Autowired
    private QuestionDefinitionRepository questionDefinitionRepository;

    @Autowired
    private CompetenceRepository competenceRepository;

    @Autowired
    private QuestionOptionDefinitionRepository questionOptionDefinitionRepository;

    // Ajouter une question avec ses options et une compétence associée
    public QuestionDefinition addQuestionDefinition(QuestionDefinition questionDefinition, Set<Long> optionIds){
        // Vérifier que la compétence existe
        Competence competence = competenceRepository.findById(questionDefinition.getCompetence().getIdCompetence())
                .orElseThrow(() -> new RuntimeException("Compétence non trouvée avec l'ID : " + questionDefinition.getCompetence().getIdCompetence()));


        // Associer les options si elles existent selons les ids
        if (optionIds != null && !optionIds.isEmpty()) {
            List<QuestionOptionDefinition> options = questionOptionDefinitionRepository.findAllById(optionIds);

            if (options.isEmpty()) {
                throw new NotFoundTestException("Aucune option valide trouvée !");
            }

            questionDefinition.setOptionsQuestion(options);
        }

        return questionDefinitionRepository.save(questionDefinition);
    }

    // Récupérer toutes les questions
    public List<QuestionDefinition> getAllQuestions() {
        return questionDefinitionRepository.findAll();
    }

    // Récupérer une question par ID
    public QuestionDefinition getQuestionById(Long id) {
        return questionDefinitionRepository.findById(id).orElseThrow(()->new InvalidTestException("qustion n'existe pas"));
    }

    // Mettre à jour une question
    public QuestionDefinition updateQuestionDefinition( QuestionDefinition questionDefinition, Set<Long> optionIds) {
        QuestionDefinition old = questionDefinitionRepository.findById(questionDefinition.getIdQuestion())
                .orElseThrow(() -> new RuntimeException("Question non trouvée avec l'ID : " + questionDefinition.getIdQuestion()));


        // Mise à jour des champs
        old.setValeur(questionDefinition.getValeur());
        old.setType(questionDefinition.getType());
        old.setCompetence(questionDefinition.getCompetence());

        // Mise à jour des options si fournies
        if (optionIds != null && !optionIds.isEmpty()) {
            List<QuestionOptionDefinition> options = questionOptionDefinitionRepository.findAllById(optionIds);//retourne une liste des options
            if (options.isEmpty()) {
                throw new InvalidTestException("Aucune option valide trouvée !");
            }
            old.setOptionsQuestion(options);
        }

        return questionDefinitionRepository.save(old);
    }

    // Supprimer une question
    public void deleteQuestionDefinition(Long id) {
        if (!questionDefinitionRepository.existsById(id)) {
            throw new NotFoundTestException("La question avec l'ID " + id + " n'existe pas !");
        }
        questionDefinitionRepository.deleteById(id);
    }
}
