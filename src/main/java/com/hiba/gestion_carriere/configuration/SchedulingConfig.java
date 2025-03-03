package com.hiba.gestion_carriere.configuration;

import com.hiba.gestion_carriere.model.test.StatutTest;
import com.hiba.gestion_carriere.model.test.Test;
import com.hiba.gestion_carriere.repository.test_repo.TestRepository;
//import com.hiba.gestion_carriere.service.test_service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.util.List;
/*
@Configuration
@EnableScheduling
public class SchedulingConfig {
    @Autowired
    private TestService testService;
    @Autowired
    TestRepository testRepository;

    public void archiveOldTests() {
        LocalDateTime now = LocalDateTime.now();
        List<Test> tests = testRepository.findAll();

        for (Test test : tests) {
            // Calculez la date de fin du test
            LocalDateTime dateFinTest = test.getDateTest().plusMinutes(test.getDuree());

            // Si le test est expiré, mettez à jour le statut
            if (dateFinTest.isBefore(now)) {
                test.setStatut(StatutTest.ARCHIVE);
                testRepository.save(test);
            }
        }
    }

    // Méthode exécutée automatiquement tous les jours à minuit
    @Scheduled(cron = "0 0 0 * * ?")  // Exécute tous les jours à minuit
    public void scheduleArchiveOldTests() {
        archiveOldTests();  // Appelle la méthode qui archive les tests expirés
    }
}
*/