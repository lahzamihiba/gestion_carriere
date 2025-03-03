package com.hiba.gestion_carriere.service.test_service;

import com.hiba.gestion_carriere.dto.TestDefinitionDto;
import com.hiba.gestion_carriere.exception.test_exception.InvalidTestException;
import com.hiba.gestion_carriere.exception.test_exception.NotFoundTestException;
import com.hiba.gestion_carriere.model.formation.Formation;
import com.hiba.gestion_carriere.model.test.QuestionDefinition;
import com.hiba.gestion_carriere.model.test.TestDefinition;
import com.hiba.gestion_carriere.model.test.Test;
import com.hiba.gestion_carriere.model.competence.Competence;
import com.hiba.gestion_carriere.repository.test_repo.QuestionDefinitionRepository;
import com.hiba.gestion_carriere.repository.test_repo.TestDefinitionRepository;
import com.hiba.gestion_carriere.repository.test_repo.TestRepository;
import com.hiba.gestion_carriere.repository.competence_rep.CompetenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class TestDefinitionService {

    @Autowired
    private TestDefinitionRepository testDefinitionRepository;

    @Autowired
    private TestRepository testRepository;

    @Autowired
    private CompetenceRepository competenceRepository;
    @Autowired
    private QuestionDefinitionRepository questionDefinitionRepository;

    // Ajouter une définition de test
    public TestDefinition addTestDefinition(TestDefinition testDefinition, Set<Long>questionsIds, Formation formation) {
        // Vérifier que la définition de test a au moins une compétence
        if (questionsIds == null || questionsIds.isEmpty()) {
            throw new InvalidTestException("Une définition de test doit avoir au moins un question !");
        }
//cast
        Set<QuestionDefinition>questionDefinitions= (Set<QuestionDefinition>) questionDefinitionRepository.findAllById(questionsIds);

        testDefinition.setQuestions(questionDefinitions);

        if(formation != null) {
            testDefinition.setFormation(formation);
        }

                return testDefinitionRepository.save(testDefinition);
    }

    // Récupérer toutes les définitions de test
    public List<TestDefinition> getAllTestDefinitions() {
        return testDefinitionRepository.findAll();
    }

    // Récupérer une définition de test par ID
    public TestDefinition getTestDefinitionById(Long id) {
        return testDefinitionRepository.findById(id)
                .orElseThrow(() -> new InvalidTestException("TestDefinition non trouvé"));
    }

    // Mettre à jour une définition de test
    public TestDefinition updateTestDefinition(TestDefinition testDefinition) {
        TestDefinition existingDefinition = testDefinitionRepository.findById(testDefinition.getIdTestDef())
                .orElseThrow(() -> new RuntimeException("Définition de test non trouvée avec l'ID : " + testDefinition.getIdTestDef()));

        // Mise à jour des champs
        existingDefinition.setTitre(testDefinition.getTitre());
        existingDefinition.setDescription(testDefinition.getDescription());
        existingDefinition.setNiveau(testDefinition.getNiveau());

        if (testDefinition.getQuestions()!= null && !testDefinition.getQuestions().isEmpty()) {
            Set<QuestionDefinition>questions=testDefinition.getQuestions();

            existingDefinition.setQuestions(questions);
        }

        if(testDefinition.getFormation()!= null) {
            existingDefinition.setFormation(testDefinition.getFormation());
        }

        return testDefinitionRepository.save(existingDefinition);
    }

    // Supprimer une définition de test
    public void deleteTestDefinition(Long id) {
        if (!testDefinitionRepository.existsById(id)) {
            throw new NotFoundTestException("La définition de test avec l'ID " + id + " n'existe pas !");
        }
        testDefinitionRepository.deleteById(id);
    }


}
