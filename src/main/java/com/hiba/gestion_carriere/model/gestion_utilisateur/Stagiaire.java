package com.hiba.gestion_carriere.model.gestion_utilisateur;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hiba.gestion_carriere.model.contrat.Contrat;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Stagiaire  extends Utilisateur{
    @OneToOne(mappedBy = "stagiaire", cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    private Contrat contrat;

    @NonNull
    private Boolean stagiaireEnable;

    public Stagiaire(@NonNull String keyCloakId, @NonNull Boolean enable, @NonNull String email,@NonNull Boolean stagiaireEnable) {
        super(keyCloakId, enable, email);
        this.stagiaireEnable = stagiaireEnable;
    }

    public Stagiaire(@NonNull String keyCloakId, @NonNull Boolean enable, @NonNull String email, Contrat contrat,@NonNull Boolean stagiaireEnable) {
        super(keyCloakId, enable, email);
        this.contrat = contrat;
        this.stagiaireEnable = stagiaireEnable;
    }
}
