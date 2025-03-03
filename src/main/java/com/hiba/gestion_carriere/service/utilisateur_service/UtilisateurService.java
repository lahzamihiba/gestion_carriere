package com.hiba.gestion_carriere.service.utilisateur_service;

import com.hiba.gestion_carriere.exception.user_exception.InvalidUserException;
import com.hiba.gestion_carriere.exception.user_exception.NotFoundUserException;
import com.hiba.gestion_carriere.model.gestion_utilisateur.Utilisateur;
import com.hiba.gestion_carriere.repository.utilisateur_rep.UtilisateurRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UtilisateurService {
// Test test = testRepository.findById(id).orElseThrow(NotFoundTestException::new);
    @Autowired
    private UtilisateurRepository utilisateurRepository;

    // Ajouter un utilisateur
    @Transactional
    public Utilisateur save(Utilisateur utilisateur) {
        if (utilisateur == null) {
            throw new InvalidUserException("L'utilisateur ne peut pas être null !");
        }
        if (utilisateur.getEmail() == null || utilisateur.getEmail().isEmpty()) {
            throw new InvalidUserException("L'email est obligatoire !");
        }
        if (utilisateur.getKeyCloakId() == null || utilisateur.getKeyCloakId().isEmpty()) {
            throw new InvalidUserException("Le KeyCloak ID est obligatoire !");
        }

        return utilisateurRepository.save(utilisateur);
    }

    //  Récupérer un utilisateur par ID avec validation
    public Utilisateur findById(Long id) {
        if (id == null || id <= 0) {
            throw new InvalidUserException("ID invalide !");
        }
        Optional<Utilisateur> utilisateur = utilisateurRepository.findById(id);
        return utilisateur.orElseThrow(NotFoundUserException::new);//utilisateur n'existe pas
    }

    //  Récupérer tous les utilisateurs
    public List<Utilisateur> findAll() {
        return utilisateurRepository.findAll();
    }

    //  Mettre à jour un utilisateur
    @Transactional
    @Modifying
    public Utilisateur update(Utilisateur utilisateur) {
        if (utilisateur == null || utilisateur.getIdUser() == null) {
            throw new InvalidUserException("L'utilisateur à mettre à jour doit avoir un ID !");
        }

        Utilisateur existingUser = findById(utilisateur.getIdUser());
        if (existingUser == null) {
            throw new NotFoundUserException();
        }
        existingUser.setEmail(utilisateur.getEmail());
        existingUser.setEnable(utilisateur.getEnable());
        existingUser.setKeyCloakId(utilisateur.getKeyCloakId());
        existingUser.setTests(utilisateur.getTests());

        return save(existingUser);
    }

    //  Supprimer un utilisateur
    public void disable(Long id) {
        if (id == null || id <= 0) {
            throw new InvalidUserException("ID invalide !");
        }
        if (!utilisateurRepository.existsById(id)) {
            throw new NotFoundUserException();
        }
        Utilisateur utilisateur = findById(id);
        utilisateur.setEnable(false);
      //  utilisateur.setKeyCloakId(null);
        utilisateurRepository.save(utilisateur);

     }
}
