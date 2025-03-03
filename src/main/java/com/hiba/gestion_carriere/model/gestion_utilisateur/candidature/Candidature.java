package com.hiba.gestion_carriere.model.gestion_utilisateur.candidature;

import com.hiba.gestion_carriere.model.poste.ProfilDePoste;
import lombok.*;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import jakarta.persistence.UniqueConstraint;

import java.time.LocalDate;


@Setter
@Getter
@EqualsAndHashCode (of={"candidat","profilDePoste"})
@RequiredArgsConstructor
@Table(name = "candidature", uniqueConstraints = @UniqueConstraint(columnNames = {"candidat_id", "profil_id"}))

@Entity
@NoArgsConstructor

public class Candidature {
    @Id
    @GeneratedValue
    Long idCandidature;

    @ManyToOne
    @JoinColumn(name="candidat_id",nullable = false)
    @NonNull
    private Candidat candidat;

    @ManyToOne
    @JoinColumn(name="profil_id",nullable = false)
    @NonNull
    private ProfilDePoste profilDePoste ;

    @NonNull
    private LocalDate dateCandidature ;



}