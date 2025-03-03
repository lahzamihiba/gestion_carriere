package com.hiba.gestion_carriere.service.contrat_service;

import com.hiba.gestion_carriere.model.contrat.Contrat;
import com.hiba.gestion_carriere.model.contrat.TypeContrat;
import com.hiba.gestion_carriere.repository.contrat_rep.ContratRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ContratService {

    @Autowired
    private ContratRepository contratRepository;

    //  Ajouter un contrat (avec validation des employés et stagiaires)
    @Transactional
    public Contrat save(Contrat contrat) {
        if (contrat == null) {
            throw new IllegalArgumentException("Le contrat ne peut pas être null !");
        }

        if (contrat.getDateDebut().isAfter(contrat.getDateFin())) {
            throw new IllegalArgumentException("La date de début doit être avant la date de fin !");
        }

        // Vérifier qu'un stagiaire et un employé ne sont pas affectés simultanément
        if (contrat.getTypeContrat() == TypeContrat.STAGE) {
            if (contrat.getStagiaire() == null || contrat.getEmployee() != null) {
                throw new IllegalStateException("Un contrat de type STAGE doit avoir un stagiaire, pas un employé.");
            }
        } else {
            if (contrat.getEmployee() == null || contrat.getStagiaire() != null) {
                throw new IllegalStateException("Un contrat autre que STAGE doit avoir un employé, pas un stagiaire.");
            }
        }

        return contratRepository.save(contrat);
    }

    //  Récupérer un contrat par ID
    public Contrat findById(Long id) {
        return contratRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Aucun contrat trouvé avec l'ID " + id));
    }

    // Récupérer tous les contrats
    public List<Contrat> findAll() {
        return contratRepository.findAll();
    }

    //  Mettre à jour un contrat
    @Transactional
    @Modifying
    public Contrat update(Contrat newContrat) {
        Contrat existingContrat = findById(newContrat.getIdContrat()); // Vérifie que le contrat existe

        // Mise à jour des champs
        existingContrat.setTypeContrat(newContrat.getTypeContrat());
        existingContrat.setActif(newContrat.getActif());
        existingContrat.setDateDebut(newContrat.getDateDebut());
        existingContrat.setDateFin(newContrat.getDateFin());
        existingContrat.setSalaire(newContrat.getSalaire());
        existingContrat.setEmployee(newContrat.getEmployee());
        existingContrat.setStagiaire(newContrat.getStagiaire());


        return contratRepository.save(existingContrat);
    }

    //  Supprimer un contrat
    @Transactional
    public void delete(Long id) {
        Contrat contrat = findById(id);
        contratRepository.delete(contrat);
    }
}
