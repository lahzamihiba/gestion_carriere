package com.hiba.gestion_carriere.service.test_service;

import com.hiba.gestion_carriere.exception.test_exception.InvalidTestException;
import com.hiba.gestion_carriere.model.test.Test;
import com.hiba.gestion_carriere.model.test.TestDefinition;
import com.hiba.gestion_carriere.repository.test_repo.TestRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TestService {
    private final com.hiba.gestion_carriere.repository.test_repo.TestRepository testRepository;
    private final TestDefinitionService testDefinitionService;

    public TestService(TestRepository testRepository, TestDefinitionService testDefinitionService) {
        this.testRepository = testRepository;
        this.testDefinitionService = testDefinitionService;
    }


    public List<Test> getAllTests() {
        return testRepository.findAll();
    }

    public Test getTestById(Long id) {
        Test test = testRepository.findById(id).orElseThrow(() -> new InvalidTestException("ID invalide pour test"));
        return test;
    }

    @Transactional
    public Test saveTest(Test test , Long idTestDef) {
        TestDefinition testdef = testDefinitionService.getTestDefinitionById(idTestDef);
        if(testdef == null) {
            throw new InvalidTestException("test definition invalide pour test");
        }
        if(test.getDateTest() == null||test.getDateTest().isBefore(LocalDateTime.now())) {
            throw new InvalidTestException("Date invalide pour test");
        }
        test.setDefinition(testdef);
        return testRepository.save(test);
    }

    @Transactional
    @Modifying
    public Test updateTest(Test test) {
        Test old = testRepository.findById(test.getId()).orElseThrow(() -> new InvalidTestException("ID invalide pour test"));
        if (test.getDateTest() == null||test.getDateTest().isBefore(LocalDateTime.now())) {
            throw new InvalidTestException("Date invalide pour test");
        }
        if(test.getDuree() == null){
            throw new InvalidTestException("Duree invalide pour test");
        }
        old.setDuree(test.getDuree());
        old.setDateTest(test.getDateTest());
        old.setStatut(test.getStatut());
        old.setAnswers(test.getAnswers());
        old.setDefinition(test.getDefinition());
        old.setUtilisateur(test.getUtilisateur());

        return testRepository.save(old);
    }

    @Transactional
    @Modifying
    public void deleteTest(Long id) {
        Test test = testRepository.findById(id).orElseThrow(() -> new InvalidTestException("ID invalide pour test"));
        testRepository.delete(test);
    }

}
