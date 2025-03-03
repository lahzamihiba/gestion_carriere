package com.hiba.gestion_carriere.model.autre.evaluation;

import com.hiba.gestion_carriere.model.test.QuestionDefinition;
import com.hiba.gestion_carriere.model.test.TestDefinition;
import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@EqualsAndHashCode(of={"test","question"})
@RequiredArgsConstructor
@Table(name = "question_test_definition", uniqueConstraints = @UniqueConstraint(columnNames = {"test_id", "question_id"}))

@Entity
public class QuestionTestDefinition {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name="test_id",nullable = false)
    @NonNull
    private TestDefinition test;

    @ManyToOne
    @JoinColumn(name="question_id",nullable = false)
    @NonNull
    private QuestionDefinition question ;

    @NonNull
    private Integer ordre;


}
