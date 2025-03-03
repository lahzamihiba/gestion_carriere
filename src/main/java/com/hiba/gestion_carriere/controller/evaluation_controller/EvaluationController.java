package com.hiba.gestion_carriere.controller.evaluation_controller;

//import com.hiba.gestion_carriere.model.evaluation.CleEvaluation;
//import com.hiba.gestion_carriere.model.evaluation.Evaluation;
//import com.hiba.gestion_carriere.service.evaluation_service.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/*
@RestController // Retournera les données directement au format JSON ou XML
@RequestMapping("/evaluation")
@CrossOrigin("*") // Accessible depuis n'importe quelle origine de requête (#ports)
public class EvaluationController {
    @Autowired
    private EvaluationService evaluationService;

    // Récupérer toutes les évaluations
    @GetMapping("/getAll")
    public List<Evaluation> getAll() {
        return evaluationService.getAllEvaluation();
    }

    // Ajouter ou modifier une évaluation
    @PostMapping("/ajouter")
    public ResponseEntity<Evaluation> ajouterEvaluation(@RequestParam Long idUtilisateur, @RequestParam Long idTest,
                                                        @RequestParam String commentaire, @RequestParam Double note) {
        Evaluation evaluation = evaluationService.add(idUtilisateur, idTest, commentaire, note);
        return new ResponseEntity<>(evaluation, HttpStatus.CREATED); // Retourne l'évaluation ajoutée avec un code 201
    }

    // Supprimer une évaluation
    @DeleteMapping("/delete")
    public ResponseEntity<Void> delete(@RequestParam Long idUtilisateur, @RequestParam Long idTest) {
        CleEvaluation cle = new CleEvaluation(idUtilisateur, idTest);
        evaluationService.deleteEvaluation(cle);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Retourne un code 204 sans contenu
    }

    // Récupérer une évaluation par clé composite
    @GetMapping("/getByCle")
    public ResponseEntity<Evaluation> getEvaluation(@RequestParam Long idUtilisateur, @RequestParam Long idTest) {
        CleEvaluation cle = new CleEvaluation(idUtilisateur, idTest);
        Evaluation evaluation = evaluationService.getEvaluation(cle);
        if (evaluation == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Retourne 404 si l'évaluation n'existe pas
        }
        return new ResponseEntity<>(evaluation, HttpStatus.OK);
    }
}
*/