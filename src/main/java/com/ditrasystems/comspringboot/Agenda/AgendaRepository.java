package com.ditrasystems.comspringboot.Agenda;

import com.ditrasystems.comspringboot.Fournisseur.Fournisseur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.Optional;

public interface AgendaRepository extends JpaRepository<Agenda ,Long> {
    ArrayList<Agenda> findByFournisseur(Fournisseur fournisseur);
    Optional<Agenda> findByFournisseurAndPrincipaleAndIdNot(Fournisseur fournisseur, Boolean principale, Long id);
}
