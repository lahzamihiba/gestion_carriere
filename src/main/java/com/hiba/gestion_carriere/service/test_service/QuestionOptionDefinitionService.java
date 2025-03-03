package com.hiba.gestion_carriere.service.test_service;

import com.hiba.gestion_carriere.model.test.QuestionDefinition;
import com.hiba.gestion_carriere.model.test.QuestionOptionDefinition;
import com.hiba.gestion_carriere.repository.test_repo.QuestionDefinitionRepository;
import com.hiba.gestion_carriere.repository.test_repo.QuestionOptionDefinitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class QuestionOptionDefinitionService {

    @Autowired
    private QuestionOptionDefinitionRepository questionOptionDefinitionRepository;

    @Autowired
    private QuestionDefinitionRepository questionDefinitionRepository;

    // Ajouter une option à une question existante
    @Transactional
    public QuestionOptionDefinition addOptionToQuestion(QuestionOptionDefinition option) {
        // Vérifier si la question existe
        QuestionDefinition question = questionDefinitionRepository.findById(option.getQuestionDefinition().getIdQuestion())
                .orElseThrow(() -> new RuntimeException("Question non trouvée avec l'ID : " + option.getQuestionDefinition().getIdQuestion()));

        // Sauvegarder l'option
        return questionOptionDefinitionRepository.save(option);
    }

    // Récupérer toutes les options
    public List<QuestionOptionDefinition> getAllOptions() {
        return questionOptionDefinitionRepository.findAll();
    }

    // Récupérer les options d'une question spécifique
    public List<QuestionOptionDefinition> getOptionsByQuestion(Long questionId) {
        return questionOptionDefinitionRepository.findByQuestionDefinitionIdQuestion(questionId);
    }

    // Mettre à jour une option
    @Transactional
    public QuestionOptionDefinition updateOption(QuestionOptionDefinition updatedOption) {
        QuestionOptionDefinition existingOption = questionOptionDefinitionRepository.findById(updatedOption.getIdOption())
                .orElseThrow(() -> new RuntimeException("Option non trouvée avec l'ID : " + updatedOption.getIdOption()));

        // Mise à jour des champs
        existingOption.setDescription(updatedOption.getDescription());
        existingOption.setIsTrue(updatedOption.getIsTrue());
        existingOption.setOrdre(updatedOption.getOrdre());
        existingOption.setQuestionDefinition(updatedOption.getQuestionDefinition());

        return questionOptionDefinitionRepository.save(existingOption);
    }

    // Supprimer une option
    @Transactional
    public void deleteOption(Long optionId) {
        if (!questionOptionDefinitionRepository.existsById(optionId)) {
            throw new RuntimeException("L'option avec l'ID " + optionId + " n'existe pas !");
        }
        questionOptionDefinitionRepository.deleteById(optionId);
    }
}
