package com.ditrasystems.comspringboot.Famille;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FamilleRepository extends JpaRepository<Famille,Long> {

  Optional<Famille> findFamilleByNom(String name);
}
