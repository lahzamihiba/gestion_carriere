package com.hiba.gestion_carriere.model.test;

import com.hiba.gestion_carriere.model.gestion_utilisateur.Utilisateur;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@RequiredArgsConstructor
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Test {
     @Id @GeneratedValue
     private Long id;

     @NonNull
     private Integer duree;

     @NonNull
     private LocalDateTime dateTest;

    @Enumerated(EnumType.STRING)
    private StatutTest statut = StatutTest.ATTENTE;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "id_test_definition",nullable = false)
    private TestDefinition definition;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "id_user",nullable = false)
    private Utilisateur utilisateur;

    @OneToMany(mappedBy = "test", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TestAnswer> answers;


}

  /*  @OneToOne(mappedBy = "test")
    private Formation formation;*/

 /*   @ManyToOne
    private ObjectifProfessionnel objPro;
*/
   /* @OneToMany(mappedBy = "test")
    private Set<TestDefinition> definitions=new HashSet<>() ;
*/
