package com.hiba.gestion_carriere.model.poste;
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
public class Departement {
    @Id
    @GeneratedValue
    private Long id;
    @NonNull
    private String nom;
    @OneToMany(mappedBy = "departement")
    private Set<Poste> postes = new HashSet<>();
}
