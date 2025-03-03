package com.hiba.gestion_carriere.controller.test_controller;

import com.hiba.gestion_carriere.dto.QuestionDTO;
import com.hiba.gestion_carriere.model.test.QuestionDefinition;
import com.hiba.gestion_carriere.service.test_service.QuestionDefinitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/questionDefinition")
@CrossOrigin("*")
public class QuestionDefinitionController {

    @Autowired
    private QuestionDefinitionService questionDefinitionService;

    // Ajouter une question avec ses options et une compétence associée
    @PostMapping("/add")
    public ResponseEntity<QuestionDefinition> addQuestionDefinition(
            @RequestBody QuestionDTO questionDTO) {

        QuestionDefinition savedQuestion = questionDefinitionService.addQuestionDefinition(questionDTO.questionDefinition(),questionDTO.optionIds());
        return new ResponseEntity<>(savedQuestion, HttpStatus.CREATED);
    }

    // Récupérer toutes les questions
    @GetMapping("/getAll")
    public ResponseEntity<List<QuestionDefinition>> getAllQuestions() {
        List<QuestionDefinition> questions = questionDefinitionService.getAllQuestions();
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    // Récupérer une question par ID
    @GetMapping("/get/{id}")
    public ResponseEntity<QuestionDefinition> getQuestionById(@PathVariable Long id) {
        QuestionDefinition question = questionDefinitionService.getQuestionById(id);
        if(question == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(question, HttpStatus.OK);
    }

    // Mettre à jour une question
    @PutMapping("/update")
    public ResponseEntity<QuestionDefinition> updateQuestionDefinition(
            @RequestBody QuestionDTO questionDTO) {

        QuestionDefinition updated = questionDefinitionService.updateQuestionDefinition(questionDTO.questionDefinition(),questionDTO.optionIds());
        if(updated == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    // Supprimer une question
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteQuestionDefinition(@PathVariable Long id) {
        questionDefinitionService.deleteQuestionDefinition(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
