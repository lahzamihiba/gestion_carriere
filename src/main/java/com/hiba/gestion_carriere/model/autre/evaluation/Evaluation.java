package com.hiba.gestion_carriere.model.autre.evaluation;
/*
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hiba.gestion_carriere.model.gestion_test.Test;
import com.hiba.gestion_carriere.model.gestion_utilisateur.Utilisateur;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "cle")
@ToString
@RequiredArgsConstructor
@NoArgsConstructor
public class Evaluation {
    @EmbeddedId
    @NonNull
    private CleEvaluation cle;

    @ToString.Exclude
    @ManyToOne
    @MapsId("idUser")
    @JsonIgnore
    @JoinColumn(name = "user_id")
    @NonNull
    private Utilisateur User;

    @ToString.Exclude
    @ManyToOne
    @MapsId("idTest")
    @JsonIgnore
    @NonNull
    private Test test;

    @NonNull
    private Double note;

    @NonNull
    private String commentaire;




}
*/