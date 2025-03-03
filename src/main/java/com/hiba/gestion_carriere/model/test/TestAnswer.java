package com.hiba.gestion_carriere.model.test;
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
public class TestAnswer {
    @Id
    @GeneratedValue
    private Long idAnswer;

    @ElementCollection
    private List<Long>optionsQ=new ArrayList<>();

    @NonNull
    @ManyToOne
    @JoinColumn(name = "test_id", nullable = false)
    private Test test;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    private QuestionDefinition question;

    public TestAnswer(List<Long> optionsQ, @NonNull QuestionDefinition question, @NonNull Test test) {
        this.optionsQ = optionsQ;
        this.question = question;
        this.test = test;
    }
}
