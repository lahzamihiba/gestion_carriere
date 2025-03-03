package com.hiba.gestion_carriere.dto;

import com.hiba.gestion_carriere.model.competence.Competence;
import com.hiba.gestion_carriere.model.formation.Formation;
import com.hiba.gestion_carriere.model.test.TestDefinition;

import java.util.Set;

public record FormationDTO(Formation formation, Set<Long> competences) {
    public Formation toFormation() {
        return formation;
    }
}
