package com.hiba.gestion_carriere.model.gestion_utilisateur.affectation_employee;

import com.hiba.gestion_carriere.model.poste.Poste;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.time.LocalDate;

@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Affectation {
    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    private LocalDate dateDebut;

    @NonNull
    private LocalDate dateFin;

    @ManyToOne
    @NonNull
    private Poste poste;

    @ManyToOne
    @NonNull
    private Employee employee;
}
