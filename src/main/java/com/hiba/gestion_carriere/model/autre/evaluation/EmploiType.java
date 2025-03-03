package com.hiba.gestion_carriere.model.autre.evaluation;

import com.hiba.gestion_carriere.model.poste.ProfilDePoste;
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
public class EmploiType {
    @Id
    @GeneratedValue
    private Long id;
    @NonNull
    private String description ;
    @NonNull
    private String libelle;

  /*  @OneToMany(cascade = CascadeType.ALL, mappedBy = "emploiType")
    private Set<ProfilDePoste> profils=new HashSet<>();

   */
}
