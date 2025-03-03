package com.hiba.gestion_carriere.controller.test_controller;

import com.hiba.gestion_carriere.dto.TestDefinitionDto;
import com.hiba.gestion_carriere.model.formation.Formation;
import com.hiba.gestion_carriere.model.test.TestDefinition;
import com.hiba.gestion_carriere.service.test_service.TestDefinitionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/test_definition")
@CrossOrigin("*")
public class TestDefinitionController {
    private final TestDefinitionService testDefinitionService;

    public TestDefinitionController(TestDefinitionService testDefinitionService) {
        this.testDefinitionService = testDefinitionService;
    }


    // Ajouter une définition de test
    @PostMapping("/add")
    public ResponseEntity<TestDefinition> addTestDefinition(
            @RequestBody TestDefinitionDto testDefinitionDto) {
        try {
            TestDefinition createdTestDefinition = testDefinitionService.addTestDefinition
                    (testDefinitionDto.testDefinition(), testDefinitionDto.questionsIds(), testDefinitionDto.formation());
            return ResponseEntity.status(HttpStatus.CREATED).body(createdTestDefinition);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // Récupérer toutes les définitions de test
    @GetMapping("/getAll")
    public List<TestDefinition> getAllTestDefinitions() {
        return testDefinitionService.getAllTestDefinitions();
    }

    // Récupérer une définition de test par ID
    @GetMapping("/getById/{id}")
    public ResponseEntity<TestDefinition> getTestDefinitionById(@PathVariable Long id) {
        try {
            TestDefinition testDefinition = testDefinitionService.getTestDefinitionById(id);
            return ResponseEntity.ok(testDefinition);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Mettre à jour une définition de test
    @PutMapping("/update")
    public ResponseEntity<TestDefinition> updateTestDefinition(@RequestBody TestDefinition updatedTestDefinition) {
        try {
            TestDefinition updatedDefinition = testDefinitionService.updateTestDefinition(updatedTestDefinition);
            return ResponseEntity.ok(updatedDefinition);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    // Supprimer une définition de test
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTestDefinition(@PathVariable Long id) {
        try {
            testDefinitionService.deleteTestDefinition(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
