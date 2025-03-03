package com.hiba.gestion_carriere.model.formation;

import com.hiba.gestion_carriere.model.test.TestDefinition;
import com.hiba.gestion_carriere.model.competence.Competence;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@RequiredArgsConstructor
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Formation {
    private @Id @GeneratedValue Long idFormation;
    @NonNull
    private String titre;
    @NonNull
    private LocalDateTime dateD;
    @NonNull
    private LocalDateTime dateF;
    @NonNull
    private String description;


    @OneToMany(mappedBy = "formation",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<TestDefinition> testDefinitions = new HashSet<>();

    @NonNull
    @ManyToMany
    @JoinTable(
            name = "formation_competence",
            joinColumns = @JoinColumn(name = "formation_id"),
            inverseJoinColumns = @JoinColumn(name = "competence_id")
    )
    private Set<Competence> competences=new HashSet<>();


}
