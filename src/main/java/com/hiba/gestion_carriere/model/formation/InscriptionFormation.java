package com.hiba.gestion_carriere.model.formation;
import com.hiba.gestion_carriere.model.gestion_utilisateur.Utilisateur;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;


@Setter
@Getter
@NoArgsConstructor
@EqualsAndHashCode (of={"utilisateur","formation"})
@RequiredArgsConstructor
@Table(name = "inscription_formation", uniqueConstraints = @UniqueConstraint(columnNames = {"utilisateur_id", "formation_id"}))

@Entity
public class InscriptionFormation {
    @Id
    @GeneratedValue
    Long idInscription;

    @ManyToOne
    @JoinColumn(name="utilisateur_id",nullable = false)
    @NonNull
    private Utilisateur utilisateur;

    @ManyToOne
    @JoinColumn(name="formation_id",nullable = false)
    @NonNull
    private Formation formation ;

    @NonNull
    private LocalDate dateInscription ;

    @NonNull
    @Enumerated
    private StatutFormation statutFormation;


}
