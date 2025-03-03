package com.hiba.gestion_carriere.service.utilisateur_service;

import com.hiba.gestion_carriere.dto.EmployeeDTO;
import com.hiba.gestion_carriere.exception.stagiaire_exception.InvalidStagiaireException;
import com.hiba.gestion_carriere.exception.stagiaire_exception.NotFoundStagiaireException;
import com.hiba.gestion_carriere.model.contrat.Contrat;
import com.hiba.gestion_carriere.model.contrat.TypeContrat;
import com.hiba.gestion_carriere.model.gestion_utilisateur.affectation_employee.Affectation;
import com.hiba.gestion_carriere.model.gestion_utilisateur.affectation_employee.Employee;
import com.hiba.gestion_carriere.model.gestion_utilisateur.Stagiaire;
import com.hiba.gestion_carriere.model.poste.Poste;
import com.hiba.gestion_carriere.repository.PosteRepository;
import com.hiba.gestion_carriere.repository.utilisateur_rep.affectation_empl_rep.AffectationRepository;
import com.hiba.gestion_carriere.repository.utilisateur_rep.affectation_empl_rep.EmployeeRepository;
import com.hiba.gestion_carriere.repository.utilisateur_rep.StagiaireRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class StagiaireService {
@Autowired
    private  StagiaireRepository stagiaireRepository;
    @Autowired
    private  EmployeeRepository employeeRepository;
    @Autowired
    private PosteRepository posteRepository;
    @Autowired
    private AffectationRepository affectationRepository;

    // Récupérer tous les stagiaires
    public List<Stagiaire> getAllStagiaires() {
        return stagiaireRepository.findAll();
    }

    // Récupérer un stagiaire par son ID
    public Stagiaire getStagiaireById(Long id) {
        if (id == null || id <= 0) {
            throw new InvalidStagiaireException("ID invalide pour le stagiaire");
        }
        return stagiaireRepository.findById(id).get();
    }

    // Sauvegarder un stagiaire avec ou sans contrat
    public Stagiaire saveStagiaire(Stagiaire stagiaire) {
        if (stagiaire == null) {
            throw new InvalidStagiaireException("Le stagiaire ne peut pas être nul");
        }
        if (stagiaire.getEmail() == null || stagiaire.getEmail().isEmpty()) {
            throw new InvalidStagiaireException("L'email est obligatoire pour le stagiaire");
        }
       stagiaire.getContrat().setStagiaire(stagiaire);
        return stagiaireRepository.save(stagiaire);
    }

    // Mettre à jour un stagiaire avec ou sans contrat
    public Stagiaire updateStagiaire(Long id, Stagiaire updatedStagiaire) {
        if (id == null || id <= 0) {
            throw new InvalidStagiaireException("ID invalide pour la mise à jour du stagiaire");
        }
        Optional<Stagiaire> existingStagiaireOpt = stagiaireRepository.findById(id);
        if (existingStagiaireOpt.isPresent()) {
            Stagiaire existingStagiaire = existingStagiaireOpt.get();

            existingStagiaire.setKeyCloakId(updatedStagiaire.getKeyCloakId());
            existingStagiaire.setEnable(updatedStagiaire.getEnable());
            existingStagiaire.setEmail(updatedStagiaire.getEmail());
            existingStagiaire.setContrat(updatedStagiaire.getContrat()); // Le contrat peut être nul

            return stagiaireRepository.save(existingStagiaire);
        }
        throw new NotFoundStagiaireException("Stagiaire non trouvé");
    }

    // Supprimer un stagiaire
    public void DisableStagiaire(Long id) {
        if (id == null || id <= 0) {
            throw new InvalidStagiaireException("ID invalide pour la suppression du stagiaire");
        }
        Optional<Stagiaire> stagiaireOpt = stagiaireRepository.findById(id);
        if (stagiaireOpt.isPresent()) {
            stagiaireOpt.get().setStagiaireEnable(false);
            stagiaireOpt.get().setEnable(false);
         //   stagiaireOpt.get().setKeyCloakId(null);

            stagiaireRepository.save(stagiaireOpt.get());

         } else {
            throw new NotFoundStagiaireException("Stagiaire non trouvé");
        }
    }
    @Transactional
    @Modifying
    public Employee transformToEmployee(Long idStagiaire,Employee employee,Long posteId) {
        // Vérification de la validité de l'ID
        if (idStagiaire == null || idStagiaire <= 0) {
            throw new IllegalArgumentException("ID invalide pour la transformation en employé");
        }

        // Vérification que le contrat n'est pas nul et de type valide
        if (employee.getContrat() == null) {
            throw new IllegalArgumentException("Contrat invalide : Le contrat doit être de type CDI, CDD, CVP ou Freelance");
        }

        // Recherche du stagiaire par son ID
        Optional<Stagiaire> stagiaireOpt = stagiaireRepository.findById(idStagiaire);
        if (stagiaireOpt.isPresent()) {
            Stagiaire stagiaire = stagiaireOpt.get();

            //mise a jour donne de l'employee ;ajouter user donnes
            employee.setEmail(stagiaire.getEmail());
            employee.setEnable(true);
            employee.setKeyCloakId(stagiaire.getKeyCloakId());
            employee.setEmployeeEnable(true);

            //affecter l'employer au contrat
            employee.getContrat().setEmployee(employee);

            // Sauvegarde de l'employé dans la base de données
            employeeRepository.save(employee);

            //changer l'estat de stagiaire pour activer l'employer
            stagiaire.setStagiaireEnable(false);
            stagiaireRepository.save(stagiaire);

            //affecter l'employee a un poste
            Affectation affectation = new Affectation();

            affectation.setDateFin(employee.getContrat().getDateFin());
            affectation.setEmployee(employee);
            affectation.setDateDebut(LocalDate.now());
            affectation.setPoste(posteRepository.findById(posteId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Poste non trouvé")));

            affectationRepository.save(affectation);

            return employee;
        }

        // Si le stagiaire n'est pas trouvé, une exception est levée
        throw new NotFoundStagiaireException("Stagiaire non trouvé");
    }

    private boolean isValidContractType(Contrat contrat) {
        TypeContrat contractType = contrat.getTypeContrat();
        return contractType != null && (contractType.equals("CDI") || contractType.equals("CDD") || contractType.equals("CVP") || contractType.equals("Freelance"));
    }


}
