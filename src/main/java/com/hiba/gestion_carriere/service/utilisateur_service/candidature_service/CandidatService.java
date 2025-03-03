package com.hiba.gestion_carriere.service.utilisateur_service.candidature_service;

import com.hiba.gestion_carriere.dto.EmployeeDTO;
import com.hiba.gestion_carriere.exception.candidat_exception.InvalidCandidatException;
import com.hiba.gestion_carriere.exception.candidat_exception.NotFoundCandidatException;
import com.hiba.gestion_carriere.model.contrat.Contrat;
import com.hiba.gestion_carriere.model.gestion_utilisateur.affectation_employee.Affectation;
import com.hiba.gestion_carriere.model.gestion_utilisateur.candidature.Candidat;
import com.hiba.gestion_carriere.model.gestion_utilisateur.affectation_employee.Employee;
import com.hiba.gestion_carriere.model.gestion_utilisateur.Stagiaire;
import com.hiba.gestion_carriere.repository.PosteRepository;
import com.hiba.gestion_carriere.repository.utilisateur_rep.affectation_empl_rep.AffectationRepository;
import com.hiba.gestion_carriere.repository.utilisateur_rep.candidature_rep.CandidatRepository;
import com.hiba.gestion_carriere.repository.utilisateur_rep.affectation_empl_rep.EmployeeRepository;
import com.hiba.gestion_carriere.service.utilisateur_service.StagiaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CandidatService {
    @Autowired
    private  CandidatRepository candidatRepository;
    @Autowired
    private  EmployeeRepository employeeRepository;
    @Autowired
    private StagiaireService stagiaireService;
    @Autowired
    private PosteRepository posteRepository;
    @Autowired
    private AffectationRepository affectationRepository;

    //getAll
    public List<Candidat> getAllCandidats() {
        return candidatRepository.findAll();
    }

    //by id
    public Candidat getCandidatById(Long id) {
        return candidatRepository.findById(id).orElseThrow(()
                ->new InvalidCandidatException("aucun candidat trouvé avec l'id" + id));
    }

    public Candidat saveCandidat(Candidat candidat) {
        if (candidat == null) {
            throw new InvalidCandidatException("Le candidat ne peut pas être null !");
        }

        if (candidat.getEmail() == null || candidat.getEmail().isEmpty() ) {
            throw new InvalidCandidatException("Email invalide !");
        }


        if (candidat.getNumTel() == null || candidat.getNumTel().isEmpty()) {
            throw new InvalidCandidatException("Numéro de téléphone invalide !");
        }
        return candidatRepository.save(candidat);
    }


    public Employee transformToEmployee(Long idCandidat,Employee employee,Long posteId) {
        if (employee == null  || employee.getCin()==null||idCandidat==null
                || employee.getCin().isEmpty()||employee.getNumCNSS() == null || employee.getNumCNSS().isEmpty()) {
            throw new InvalidCandidatException("Données invalides pour la transformation en employé");
        }
        //importer le candidat
        Optional<Candidat> candidatOpt = candidatRepository.findById(idCandidat);
        if (candidatOpt.isPresent()) {
            Candidat candidat = candidatRepository.findById(idCandidat).orElseThrow(()
                    ->new InvalidCandidatException("aucun candidat trouvé avec l'id" + idCandidat));

            //ajouter des donnes au employee(existe mais sans les donnes de user)
            employee.setKeyCloakId(candidat.getKeyCloakId());
            employee.setEnable(true);
            employee.setEmail(candidat.getEmail());
            //affecter l'employee au contrat
            employee.getContrat().setEmployee(employee);

            //modifier l'etat d'un candidat
            candidat.setCandidatEnable(false);
            candidatRepository.save(candidat);

            employeeRepository.save(employee);

            //affecter l'employer a un poste
            Affectation affectation = new Affectation();
            affectation.setEmployee(employee);
            affectation.setDateDebut(LocalDate.now());
            affectation.setDateFin(employee.getContrat().getDateFin());

            affectation.setPoste(posteRepository.findById(posteId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Poste non trouvé")));

            affectationRepository.save(affectation);

            return employee;
        }
        throw new InvalidCandidatException("aucun candidat trouvé avec l'id" + idCandidat);
    }


    public Stagiaire transformToStagiaire(Long idCandidat, Contrat contrat) {
        if (idCandidat == null || idCandidat <= 0){
            throw new InvalidCandidatException("Données invalides pour la transformation en stagiaire");
        }
        Optional<Candidat> candidatOpt = candidatRepository.findById(idCandidat);
        if (candidatOpt.isPresent()) {
            Candidat candidat = candidatOpt.get();
            Stagiaire stagiaire = (contrat != null)
                    ? new Stagiaire(candidat.getKeyCloakId(),true , candidat.getEmail(), contrat,true)
                    : new Stagiaire(candidat.getKeyCloakId(), true, candidat.getEmail(),true);

            stagiaireService.saveStagiaire(stagiaire); // Sauvegarder le stagiaire

            //modifier l'etat d'un candidat
            candidat.setCandidatEnable(false);
            candidatRepository.save(candidat);

            return stagiaire;
        }
        throw new NotFoundCandidatException("Candidat non trouvé "+idCandidat);
    }

    public void disableCandidat(Long id) {
        Candidat candidat = candidatRepository.findById(id).orElseThrow(()
                ->new InvalidCandidatException("aucun candidat trouvé avec l'id" + id));

        candidat.setCandidatEnable(false);
        candidat.setEnable(false);
     //   candidat.setKeyCloakId(null);
        candidatRepository.save(candidat);
    }

}
