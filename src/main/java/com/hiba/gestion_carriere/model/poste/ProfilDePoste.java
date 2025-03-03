package com.hiba.gestion_carriere.model.poste;

import com.hiba.gestion_carriere.model.competence.Competence;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class ProfilDePoste {
    @Id
    @GeneratedValue
    private Long idProf;
    @NonNull
    private String intitule;
    @NonNull
    private String commentaire;

    /*@ManyToOne
    private EmploiType emploiType;*/

    @ManyToMany(mappedBy = "profils")
    private Set<Tache> taches=new HashSet<>();

    @NonNull
    @ManyToOne(optional = false)
    private Poste poste;

    @ManyToMany
    @NonNull//unidirection
    @JoinTable(name = "competences requises_profil",joinColumns = @JoinColumn(name = "profil_id"),
            inverseJoinColumns = @JoinColumn(name = "competence_id"))
    private Set<Competence>competences=new HashSet<>();



}
