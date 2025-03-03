package com.hiba.gestion_carriere.model.autre.evaluation;
/*
import com.hiba.gestion_carriere.model.test.QuestionDefinition;
import com.hiba.gestion_carriere.model.autre.evaluation.QuestionTestDefinition;
import com.hiba.gestion_carriere.model.test.TestDefinition;
import com.hiba.gestion_carriere.repository.test_repo.QuestionDefinitionRepository;
import com.hiba.gestion_carriere.model.autre.evaluation.QuestionTestDefinitionRepository;
import com.hiba.gestion_carriere.repository.test_repo.TestDefinitionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class QuestionTestDefinitionService {
@Autowired
    private  QuestionTestDefinitionRepository questionTestDefinitionRepository;
@Autowired
    private  TestDefinitionRepository testDefinitionRepository;
    @Autowired

    private  QuestionDefinitionRepository questionDefinitionRepository;

    public List<QuestionTestDefinition> getAll() {
        return questionTestDefinitionRepository.findAll();
    }

    public QuestionTestDefinition getById(Long id) {
        return questionTestDefinitionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Association non trouvée avec ID: " + id));
    }

    @Transactional
    public QuestionTestDefinition create(QuestionTestDefinition association) {
        if (association.getTest() == null || association.getQuestion() == null) {
            throw new IllegalArgumentException("Le test et la question sont obligatoires.");
        }

        // Vérifier si le test existe
        TestDefinition test = testDefinitionRepository.findById(association.getTest().getIdTest())
                .orElseThrow(() -> new EntityNotFoundException("TestDefinition non trouvé"));

        // Vérifier si la question existe
        QuestionDefinition question = questionDefinitionRepository.findById(association.getQuestion().getIdQuestion())
                .orElseThrow(() -> new EntityNotFoundException("QuestionDefinition non trouvée"));

        // Vérifier que la question n'est pas déjà associée au même test
        boolean alreadyExists = questionTestDefinitionRepository.existsByTestAndQuestion(test, question);
        if (alreadyExists) {
            throw new IllegalArgumentException("Cette question est déjà associée à ce test.");
        }

        // Vérifier l'ordre
        if (association.getOrdre() == null || association.getOrdre() <= 0) {
            throw new IllegalArgumentException("L'ordre doit être un entier positif.");
        }

        association.setTest(test);
        association.setQuestion(question);
        return questionTestDefinitionRepository.save(association);
    }

    @Transactional
    @Modifying
    public QuestionTestDefinition update( QuestionTestDefinition updatedAssociation) {
        QuestionTestDefinition existing = getById(updatedAssociation.getId());
        if(existing == null) {
            return null;
        }

        // Vérifier l'ordre
        if (updatedAssociation.getOrdre() == null || updatedAssociation.getOrdre() <= 0) {
            throw new IllegalArgumentException("L'ordre doit être un entier positif.");
        }

        existing.setOrdre(updatedAssociation.getOrdre());
        return questionTestDefinitionRepository.save(existing);
    }

    public void delete(Long id) {
        if (!questionTestDefinitionRepository.existsById(id)) {
            throw new EntityNotFoundException("Association non trouvée avec ID: " + id);
        }
        questionTestDefinitionRepository.deleteById(id);
    }
}

 */
