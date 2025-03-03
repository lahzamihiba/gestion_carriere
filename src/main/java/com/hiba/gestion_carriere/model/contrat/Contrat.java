package com.hiba.gestion_carriere.model.contrat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hiba.gestion_carriere.model.gestion_utilisateur.affectation_employee.Employee;
import com.hiba.gestion_carriere.model.gestion_utilisateur.Stagiaire;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;


@Entity
@RequiredArgsConstructor
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Contrat {
    @Id
    @GeneratedValue
    private Long idContrat;

    @NonNull
    private TypeContrat typeContrat;

    @NonNull
    private Boolean actif;
    @NonNull
    private LocalDate dateDebut;
    @NonNull
    private LocalDate dateFin;
    @NonNull
    private Double salaire;

    @OneToOne
    @JoinColumn(name = "employee_id", unique = true)  // associe un contrat unique à un employee
    @JsonIgnore
    private Employee employee;

    @OneToOne
    @JoinColumn(name = "stagiaire_id", nullable = true)  // nullable = true permet d'ajouter un stagiaire sans contrat
    @JsonIgnore
    private Stagiaire stagiaire;

    //  CONSTRUCTEUR AVEC VALIDATION
    public Contrat(@NonNull TypeContrat typeContrat, @NonNull Boolean actif, @NonNull LocalDate dateDebut,
                   @NonNull LocalDate dateFin, @NonNull Double salaire, Employee employee, Stagiaire stagiaire) {
        this.typeContrat = typeContrat;
        this.actif = actif;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.salaire = salaire;
        this.employee = employee;
        this.stagiaire = stagiaire;

//validation avant la creation de l'objet
//validate();
    }
/*
    @PrePersist
    @PreUpdate
    public void validate() {
        if (typeContrat == TypeContrat.STAGE) {
            if (stagiaire == null || employee != null) {
                throw new IllegalStateException("Un contrat de type STAGE doit avoir un stagiaire non un employé.");
            }
        } else {
            if (employee == null || stagiaire != null) {
                throw new IllegalStateException("Un contrat autre que STAGE doit avoir un employé et ne pas avoir de stagiaire.");
            }
        }
    }*///
}
