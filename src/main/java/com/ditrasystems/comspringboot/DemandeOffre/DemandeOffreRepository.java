package com.ditrasystems.comspringboot.DemandeOffre;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DemandeOffreRepository extends JpaRepository<DemandeOffre,String> {
    Optional<DemandeOffre> findByCode(String code);
}
