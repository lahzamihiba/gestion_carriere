package com.hiba.gestion_carriere.repository.test_repo;

import com.hiba.gestion_carriere.model.test.QuestionOptionDefinition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionOptionDefinitionRepository extends JpaRepository<QuestionOptionDefinition, Long> {
    public List<QuestionOptionDefinition> findByQuestionDefinitionIdQuestion(Long id);
}
