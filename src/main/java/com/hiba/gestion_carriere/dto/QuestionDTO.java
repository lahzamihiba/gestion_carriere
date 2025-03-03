package com.hiba.gestion_carriere.dto;

import com.hiba.gestion_carriere.model.test.QuestionDefinition;

import java.util.Set;

public record QuestionDTO( QuestionDefinition questionDefinition, Set<Long> optionIds) {

}
