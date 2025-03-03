package com.hiba.gestion_carriere.controller.test_controller;

import com.hiba.gestion_carriere.dto.TestAnswerDTO;
import com.hiba.gestion_carriere.model.test.TestAnswer;
import com.hiba.gestion_carriere.service.test_service.TestAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/answers")
@CrossOrigin("*")
public class TestAnswerController {
    @Autowired
    private TestAnswerService testAnswerService;

    @GetMapping("/getByTest/{testId}")
    public ResponseEntity<List<TestAnswer>> getAnswersByTestId(@PathVariable Long testId) {
        List<TestAnswer> answers = testAnswerService.findAllByTestId(testId);
        return ResponseEntity.ok(answers);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<TestAnswer>> getAllAnswers() {
        List<TestAnswer> answers = testAnswerService.findAll();
        return ResponseEntity.ok(answers);
    }

    @GetMapping("/getByID/{id}")
    public ResponseEntity<TestAnswer> getAnswerById(@PathVariable Long id) {
        TestAnswer answer = testAnswerService.findAnswer(id);
        return ResponseEntity.ok(answer);
    }

    @PostMapping("/add")
    public ResponseEntity<TestAnswer> saveAnswer(@RequestBody TestAnswerDTO answerDTO) {

        TestAnswer savedAnswer = testAnswerService.saveAnswer(answerDTO.toTestAnswer());
        if (savedAnswer != null) {
            return ResponseEntity.ok(savedAnswer);
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/update")
    public ResponseEntity<TestAnswer> updateAnswer(@RequestBody TestAnswerDTO answerDTO) {
        TestAnswer updatedAnswer = testAnswerService.updateAnswer(answerDTO.toTestAnswer());
        if (updatedAnswer != null) {
            return ResponseEntity.ok(updatedAnswer);
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAnswer(@PathVariable Long id) {
        testAnswerService.deleteAnswer(id);
        return ResponseEntity.noContent().build();
    }



}
