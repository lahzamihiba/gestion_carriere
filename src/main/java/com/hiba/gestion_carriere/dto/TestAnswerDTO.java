package com.hiba.gestion_carriere.dto;

import com.hiba.gestion_carriere.model.test.QuestionDefinition;
import com.hiba.gestion_carriere.model.test.Test;
import com.hiba.gestion_carriere.model.test.TestAnswer;

import java.util.List;
import java.util.Set;

public record TestAnswerDTO(Test test, QuestionDefinition question, List<Long> selectedOptions) {
    public TestAnswer toTestAnswer() {
        TestAnswer testAnswer = new TestAnswer();
        testAnswer.setTest(test);
        testAnswer.setQuestion(question);
        testAnswer.setOptionsQ(selectedOptions);

        return testAnswer;
    }
}
