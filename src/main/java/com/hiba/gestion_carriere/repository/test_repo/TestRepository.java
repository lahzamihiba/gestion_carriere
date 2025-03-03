package com.hiba.gestion_carriere.repository.test_repo;

import com.hiba.gestion_carriere.model.test.Test;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TestRepository extends JpaRepository<Test, Long> {
    public List<Test> findByDateTest(LocalDateTime dateTest);
}
