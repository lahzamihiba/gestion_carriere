package com.hiba.gestion_carriere.model.test;


import jakarta.persistence.*;
import lombok.*;

@Entity
@RequiredArgsConstructor
@Getter
@Setter
@ToString
@NoArgsConstructor
public class QuestionOptionDefinition {
    @Id
    @GeneratedValue
    private Long idOption;

    @NonNull
    private String description;

    @NonNull
    private Boolean isTrue;

    @NonNull
    private Integer ordre;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "id_question", nullable = false)
    private QuestionDefinition questionDefinition;

}
