package com.hiba.gestion_carriere.service.test_service;

import com.hiba.gestion_carriere.dto.TestAnswerDTO;
import com.hiba.gestion_carriere.exception.test_exception.InvalidTestException;
import com.hiba.gestion_carriere.exception.test_exception.NotFoundTestException;
import com.hiba.gestion_carriere.model.test.QuestionDefinition;
import com.hiba.gestion_carriere.model.test.QuestionOptionDefinition;
import com.hiba.gestion_carriere.model.test.Test;
import com.hiba.gestion_carriere.model.test.TestAnswer;
import com.hiba.gestion_carriere.repository.test_repo.QuestionDefinitionRepository;
import com.hiba.gestion_carriere.repository.test_repo.QuestionOptionDefinitionRepository;
import com.hiba.gestion_carriere.repository.test_repo.TestAnswerRepository;
import com.hiba.gestion_carriere.repository.test_repo.TestRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TestAnswerService {
    @Autowired
    private TestAnswerRepository testAnswerRepository;

    @Autowired
    private TestRepository testRepository;

    @Autowired
    private QuestionDefinitionRepository questionDefinitionRepository;

    @Autowired
    private QuestionOptionDefinitionRepository questionOptionRepository;

    @Transactional
    public TestAnswer saveAnswer(TestAnswer testAnswer) {
        Test test1 = testRepository.findById(testAnswer.getTest().getId())
                .orElseThrow(() -> new NotFoundTestException("Test non trouvé"));

        QuestionDefinition question1 = questionDefinitionRepository.findById(testAnswer.getQuestion().getIdQuestion())
                .orElseThrow(() -> new NotFoundTestException("Question non trouvée"));

        List<QuestionOptionDefinition> selectedOpt = new ArrayList<>();//liste des options

        //trouver les options par id
        for (Long optionId : testAnswer.getOptionsQ()) {
            QuestionOptionDefinition option = questionOptionRepository.findById(optionId)
                    .orElseThrow(() -> new RuntimeException("Option non trouvée"));
            selectedOpt.add(option);
        }

        return testAnswerRepository.save(testAnswer);
    }


    @Transactional
    @Modifying
    public TestAnswer updateAnswer(TestAnswer testAnswer) {
        Optional<TestAnswer> ta=testAnswerRepository.findById(testAnswer.getIdAnswer());

        if(!ta.isPresent()) {
            throw new NotFoundTestException("test answer not found!!");
        }
        TestAnswer old = ta.get();

        Test test = testRepository.findById(testAnswer.getTest().getId())
                .orElseThrow(() -> new NotFoundTestException("Test non trouvé"));

        QuestionDefinition question = questionDefinitionRepository.findById(testAnswer.getQuestion().getIdQuestion())
                .orElseThrow(() -> new NotFoundTestException("Question non trouvée"));

        for (Long optionId : testAnswer.getOptionsQ()) {

            QuestionOptionDefinition option = questionOptionRepository.findById(optionId)
                    .orElseThrow(() -> new RuntimeException("Option non trouvée"));

       }

        old.setQuestion(question);
        old.setOptionsQ(testAnswer.getOptionsQ());
        old.setTest(test);

        return testAnswerRepository.save(old);
    }

    @Transactional
    @Modifying
    public void deleteAnswer(Long answerId) {
        TestAnswer test = testAnswerRepository.findById(answerId).orElseThrow(() -> new InvalidTestException("ID invalide pour testAnswer"));
         testAnswerRepository.delete(test);
    }

    public TestAnswer findAnswer(Long answerId) {
        return testAnswerRepository.findById(answerId).orElseThrow(() -> new InvalidTestException("ID invalide pour test"));
    }

    public List<TestAnswer> findAll() {
        return testAnswerRepository.findAll();
    }


public List<TestAnswer> findAllByTestId(Long testId) {
        if(testId == null) {
            throw new InvalidTestException("testId invalide pour test");
        }
        return testAnswerRepository.findTestAnswerByTestId(testId);
}
}


