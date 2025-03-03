package com.hiba.gestion_carriere.controller.test_controller;

import com.hiba.gestion_carriere.model.test.QuestionOptionDefinition;
import com.hiba.gestion_carriere.service.test_service.QuestionOptionDefinitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/questionOption")
@CrossOrigin("*")
public class QuestionOptionDefinitionController {

    @Autowired
    private QuestionOptionDefinitionService questionOptionDefinitionService;

    // Ajouter une option à une question
    @PostMapping("/ajouter")
    public ResponseEntity<QuestionOptionDefinition> addOptionToQuestion(
            @RequestBody QuestionOptionDefinition option) {

        QuestionOptionDefinition savedOption = questionOptionDefinitionService.addOptionToQuestion(option);
        return new ResponseEntity<>(savedOption, HttpStatus.CREATED);
    }

    // Récupérer toutes les options
    @GetMapping("/getAll")
    public ResponseEntity<List<QuestionOptionDefinition>> getAllOptions() {
        List<QuestionOptionDefinition> options = questionOptionDefinitionService.getAllOptions();
        return new ResponseEntity<>(options, HttpStatus.OK);
    }

    // Récupérer les options d'une question donnée
    @GetMapping("/getByQuestion/{questionId}")
    public ResponseEntity<List<QuestionOptionDefinition>> getOptionsByQuestion(@PathVariable Long questionId) {
        List<QuestionOptionDefinition> options = questionOptionDefinitionService.getOptionsByQuestion(questionId);
        return new ResponseEntity<>(options, HttpStatus.OK);
    }

    // Mettre à jour une option
    @PutMapping("/update")
    public ResponseEntity<QuestionOptionDefinition> updateOption(
            @RequestBody QuestionOptionDefinition updatedOption) {

        QuestionOptionDefinition updated = questionOptionDefinitionService.updateOption(updatedOption);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    // Supprimer une option
    @DeleteMapping("/delete/{optionId}")
    public ResponseEntity<Void> deleteOption(@PathVariable Long optionId) {
        questionOptionDefinitionService.deleteOption(optionId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
