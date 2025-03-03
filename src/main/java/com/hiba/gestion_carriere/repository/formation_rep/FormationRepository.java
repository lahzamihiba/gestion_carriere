package com.hiba.gestion_carriere.repository.formation_rep;

import com.hiba.gestion_carriere.model.formation.Formation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface FormationRepository extends JpaRepository<Formation, Long> {
    public List<Formation> findByDateD(LocalDateTime date);
}
