package com.hiba.gestion_carriere.model.autre.evaluation;

import com.hiba.gestion_carriere.model.test.QuestionDefinition;
import com.hiba.gestion_carriere.model.test.TestDefinition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionTestDefinitionRepository extends JpaRepository<QuestionTestDefinition, Long> {

    //boolean alreadyExists = questionTestDefinitionRepository.existsByTestAndQuestion(test, question);
    public Boolean existsByTestAndQuestion(TestDefinition test, QuestionDefinition question);
}
