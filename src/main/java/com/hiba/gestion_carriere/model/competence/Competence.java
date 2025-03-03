package com.hiba.gestion_carriere.model.competence;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hiba.gestion_carriere.model.formation.Formation;
import com.hiba.gestion_carriere.model.test.QuestionDefinition;
import com.hiba.gestion_carriere.model.poste.ProfilDePoste;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@RequiredArgsConstructor
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Competence {
    @Id
    @GeneratedValue
    private Long idCompetence;

    @NonNull
    private String commentaire;
    @NonNull
    private String Type;
    @NonNull
    private String libelle;

    @OneToMany(mappedBy = "competence")
    private List<QuestionDefinition> questions=new ArrayList<>();

    @ManyToMany(mappedBy = "competences")
    @ToString.Exclude
    @JsonIgnore
    @NonNull
    private Set<Formation> formations=new HashSet<>();

    @ManyToMany(mappedBy = "competences")
    @ToString.Exclude
    @NonNull
    private Set<ProfilDePoste> profils=new HashSet<>();

}
