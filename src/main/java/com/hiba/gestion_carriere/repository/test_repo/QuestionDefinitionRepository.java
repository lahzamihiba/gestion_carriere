package com.hiba.gestion_carriere.repository.test_repo;

import com.hiba.gestion_carriere.model.test.QuestionDefinition;
import com.hiba.gestion_carriere.model.test.TypeQ;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface QuestionDefinitionRepository extends JpaRepository<QuestionDefinition, Long>  {
    public Set<QuestionDefinition> findQuestionDefinitionByType(TypeQ type);
}
