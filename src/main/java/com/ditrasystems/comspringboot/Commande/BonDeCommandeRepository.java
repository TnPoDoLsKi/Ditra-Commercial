package com.ditrasystems.comspringboot.Commande;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BonDeCommandeRepository extends JpaRepository<Commande,String> {
    Optional<Commande> findCommandeByCode(String code);

}
