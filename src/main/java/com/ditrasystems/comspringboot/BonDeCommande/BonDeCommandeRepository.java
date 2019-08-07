package com.ditrasystems.comspringboot.BonDeCommande;

import com.ditrasystems.comspringboot.DemandeOffre.DemandeOffre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BonDeCommandeRepository extends JpaRepository<DemandeOffre,Long> {
}
