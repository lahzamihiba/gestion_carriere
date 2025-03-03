package com.hiba.gestion_carriere.model.poste;

import com.hiba.gestion_carriere.model.autre.evaluation.EmploiType;
import com.hiba.gestion_carriere.model.gestion_utilisateur.affectation_employee.Affectation;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@RequiredArgsConstructor
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Poste {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @NonNull
    private Departement departement;


    @OneToMany(mappedBy = "poste")
    private Set<Affectation> affectations = new HashSet<>();

    @NonNull
    @OneToMany(mappedBy = "poste")
    private Set<ProfilDePoste>profilDePostes = new HashSet<>();
}
