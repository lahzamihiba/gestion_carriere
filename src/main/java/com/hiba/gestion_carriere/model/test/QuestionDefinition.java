package com.hiba.gestion_carriere.model.test;

import com.hiba.gestion_carriere.model.competence.Competence;
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
public class QuestionDefinition {
    @Id
    @GeneratedValue
    private Long idQuestion;

    @NonNull
    @Enumerated(EnumType.STRING)
    private TypeQ type;

    @NonNull
    private String valeur;

    @ManyToOne
    @JoinColumn(name = "competence_id", nullable = false)
    private Competence competence;


    @OneToMany (mappedBy = "questionDefinition",cascade = CascadeType.ALL)
    private List<QuestionOptionDefinition> optionsQuestion=new ArrayList<>();

    @OneToMany(mappedBy = "question",cascade = CascadeType.ALL)
    private List<TestAnswer> answersQuestion=new ArrayList<>();

    @ManyToMany(mappedBy = "questions")
    private Set<TestDefinition> definitions=new HashSet<>();
}
