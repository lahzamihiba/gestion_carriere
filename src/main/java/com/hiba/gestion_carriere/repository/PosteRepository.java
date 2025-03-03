package com.hiba.gestion_carriere.repository;

import com.hiba.gestion_carriere.model.poste.Poste;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PosteRepository extends JpaRepository<Poste, Long> {
}
