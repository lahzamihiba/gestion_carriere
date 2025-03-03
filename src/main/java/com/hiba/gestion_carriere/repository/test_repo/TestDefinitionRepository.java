package com.hiba.gestion_carriere.repository.test_repo;

//import com.hiba.gestion_carriere.model.evaluation.Evaluation;
import com.hiba.gestion_carriere.model.test.TestDefinition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestDefinitionRepository  extends JpaRepository<TestDefinition, Long> {

    //public List<TestDefinition> findByTestId(Long idTest);
}
