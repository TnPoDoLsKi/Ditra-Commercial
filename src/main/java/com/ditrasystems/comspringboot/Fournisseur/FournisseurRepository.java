package com.ditrasystems.comspringboot.Fournisseur;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FournisseurRepository extends JpaRepository<Fournisseur, Long> {

  Optional<Fournisseur> findFournisseurByCode (String code);
}
