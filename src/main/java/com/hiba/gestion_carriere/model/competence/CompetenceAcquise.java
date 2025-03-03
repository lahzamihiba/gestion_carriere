package com.hiba.gestion_carriere.model.competence;
import com.hiba.gestion_carriere.model.gestion_utilisateur.Utilisateur;
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
@NoArgsConstructor
@EqualsAndHashCode (of={"utilisateur","competence"})
@RequiredArgsConstructor
@Table(name = "competence_acquise", uniqueConstraints = @UniqueConstraint(columnNames = {"utilisateur_id", "competence_id"}))

@Entity
public class CompetenceAcquise {
    @Id
    @GeneratedValue
    Long idCompA;

    @ManyToOne
    @JoinColumn(name="utilisateur_id",nullable = false)
    @NonNull
    private Utilisateur utilisateur;

    @ManyToOne
    @JoinColumn(name="competence_id",nullable = false)
    @NonNull
    private Competence competence ;

    @NonNull
    private LocalDate date ;

    @NonNull
    private String niveau;


}
