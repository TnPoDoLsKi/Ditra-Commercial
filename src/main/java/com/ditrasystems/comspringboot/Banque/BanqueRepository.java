package com.ditrasystems.comspringboot.Banque;


import com.ditrasystems.comspringboot.Fournisseur.Fournisseur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface BanqueRepository extends JpaRepository<Banque,Long> {

    ArrayList<Banque> findByFournisseurs(Fournisseur fournisseur);

}
