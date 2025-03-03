package com.hiba.gestion_carriere.model.gestion_utilisateur.affectation_employee;


import com.hiba.gestion_carriere.model.contrat.Contrat;
import com.hiba.gestion_carriere.model.gestion_utilisateur.Utilisateur;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(of = "cin")
public class Employee extends Utilisateur {

    @NonNull
    private Boolean employeeEnable;

    @NonNull
    @Column(unique = true)
    private String cin;

    @NonNull
    private LocalDate dateNaissance;

    @NonNull
    private String numCNSS;

    @OneToMany(mappedBy = "employee")
    private Set<Affectation> affectations=new HashSet<>();

    @OneToOne(mappedBy = "employee", cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    private Contrat contrat;

    public Employee(@NonNull String keyCloakId, @NonNull Boolean enable, @NonNull String email,@NonNull  String cin, Contrat contrat,@NonNull LocalDate dateNaissance,@NonNull String numCNSS,@NonNull Boolean employeeEnable) {
        super(keyCloakId, enable, email);
        this.contrat = contrat;
        this.dateNaissance = dateNaissance;
        this.numCNSS = numCNSS;
        this.cin = cin;
        this.employeeEnable = employeeEnable;
    }
}
