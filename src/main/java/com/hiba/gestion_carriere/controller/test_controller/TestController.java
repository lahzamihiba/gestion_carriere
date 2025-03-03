package com.hiba.gestion_carriere.controller.test_controller;


import com.hiba.gestion_carriere.model.test.Test;
import com.hiba.gestion_carriere.service.test_service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //retournera les données directement au format JSON ou XML
@RequestMapping("/test")
@CrossOrigin("*")//accessible depuis n'importe quelle origine de requette(#ports)

public class TestController {
    @Autowired
    private TestService testService;

    @GetMapping("/getAll")
    public List<Test> getAllTests() {
        return testService.getAllTests();
    }

    @GetMapping("/getByID/{id}")
    public ResponseEntity<Test> getTest(@PathVariable Long id) {
        Test test = testService.getTestById(id);
        if (test == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(test, HttpStatus.OK);
    }

    @PostMapping("/add/{idtestDef}")
    public ResponseEntity<Test> add(@PathVariable Long idtestDef, @RequestBody Test test) {
        Test c=testService.saveTest(test, idtestDef);
        return new ResponseEntity<>(c, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Test> updateTest(@RequestBody Test test) {
        Test tst=testService.updateTest(test);
        if (tst == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(tst, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Test> deleteTest(@PathVariable Long id) {
        testService.deleteTest(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
