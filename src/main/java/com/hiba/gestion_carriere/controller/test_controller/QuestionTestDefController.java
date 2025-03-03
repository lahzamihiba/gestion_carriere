package com.hiba.gestion_carriere.controller.test_controller;
/*
import com.hiba.gestion_carriere.model.autre.evaluation.QuestionTestDefinition;
import com.hiba.gestion_carriere.service.test_service.QuestionTestDefinitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question-test-definitions")
@CrossOrigin("*")
public class QuestionTestDefController {
@Autowired
    private  QuestionTestDefinitionService service;

    @GetMapping("/getAll")
    public ResponseEntity<List<QuestionTestDefinition>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/getByID/{id}")
    public ResponseEntity<QuestionTestDefinition> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<QuestionTestDefinition> create( @RequestBody QuestionTestDefinition association) {
        return ResponseEntity.ok(service.create(association));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<QuestionTestDefinition> update(@PathVariable Long id,  @RequestBody QuestionTestDefinition updatedAssociation) {
        return ResponseEntity.ok(service.update(updatedAssociation));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

 */
