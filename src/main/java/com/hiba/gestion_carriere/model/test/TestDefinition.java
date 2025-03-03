package com.hiba.gestion_carriere.model.test;
import com.hiba.gestion_carriere.model.formation.Formation;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@RequiredArgsConstructor
@Getter
@Setter
@ToString
@NoArgsConstructor
public class TestDefinition {
     @Id
     @GeneratedValue
     private Long idTestDef;

     @NonNull
     private String description;

     @NonNull
     @Enumerated(EnumType.STRING)
     private NiveauTest niveau;

     @NonNull
     private String titre;

   @NonNull
   @OneToMany(mappedBy = "definition")
     private Set<Test> tests=new HashSet<>();

    @ManyToOne(optional = true) // (0..1)
    @JoinColumn(name = "formation_id")
    private Formation formation;


    @ManyToMany
    @JoinTable(
            name = "test_question",
            joinColumns = @JoinColumn(name = "test_definition_id"),
            inverseJoinColumns = @JoinColumn(name = "question_definition_id")
    )
    private Set<QuestionDefinition>questions=new HashSet<>();

}
