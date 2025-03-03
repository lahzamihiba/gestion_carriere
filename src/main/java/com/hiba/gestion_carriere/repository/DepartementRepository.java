package com.hiba.gestion_carriere.repository;

import com.hiba.gestion_carriere.model.poste.Departement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartementRepository extends JpaRepository<Departement, Long> {
}
