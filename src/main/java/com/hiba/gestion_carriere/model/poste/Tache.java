package com.hiba.gestion_carriere.model.poste;

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
public class Tache {
    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    private String intitule;

    @NonNull
    private String description;

    @ManyToMany
    private Set<ProfilDePoste> profils=new HashSet<>();
}
