package com.hiba.gestion_carriere.repository.test_repo;

import com.hiba.gestion_carriere.model.test.TestAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestAnswerRepository extends JpaRepository<TestAnswer , Long> {
    public List<TestAnswer>findTestAnswerByTestId(Long testId);

}
