package com.ditrasystems.comspringboot.Fornisseur;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FornisseurRepository extends JpaRepository<Fornisseur, Long> {

  Optional<Fornisseur> findByCode (String code);
}
