package com.hiba.gestion_carriere.model.gestion_utilisateur.candidature;

import com.hiba.gestion_carriere.model.gestion_utilisateur.Utilisateur;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Candidat extends Utilisateur {

    @NonNull
    private Boolean candidatEnable;

    @NonNull
    @Enumerated(EnumType.STRING)
    private StatutCandidature statut= StatutCandidature.en_attente;

    @NonNull
    @Column(nullable = false, length = 8)
    private String numTel;

}
