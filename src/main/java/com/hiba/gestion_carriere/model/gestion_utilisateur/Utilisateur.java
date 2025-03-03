package com.hiba.gestion_carriere.model.gestion_utilisateur;

import com.hiba.gestion_carriere.model.test.Test;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // Evite les erreurs liées à TABLE_PER_CLASS
    private Long idUser;

    @NonNull
    private String keyCloakId;

    @NonNull
    private Boolean enable;

    @NonNull
    private String email;


    @OneToMany
    private Set<Test> tests = new HashSet<>();

    /*
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "utilisateur_competence",
            joinColumns = @JoinColumn(name = "utilisateur_id"),
            inverseJoinColumns = @JoinColumn(name = "competence_id")
    )
    private Set<Competence> competences = new HashSet<>();


    /*
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "utilisateur_formation",
            joinColumns = @JoinColumn(name = "utilisateur_id"),
            inverseJoinColumns = @JoinColumn(name = "formation_id")
    )
    private Set<Formation> formations = new HashSet<>();
*/

}
