package com.hiba.gestion_carriere.dto;

import com.hiba.gestion_carriere.model.formation.Formation;
import com.hiba.gestion_carriere.model.test.QuestionDefinition;
import com.hiba.gestion_carriere.model.test.TestDefinition;

import java.util.Set;

public record TestDefinitionDto(TestDefinition testDefinition, Set<Long>questionsIds, Formation formation) {

}
