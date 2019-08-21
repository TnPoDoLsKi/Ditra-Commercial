package com.ditrasystems.comspringboot.Agenda;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface AgendaRepository extends JpaRepository<Agenda ,Long> {
    ArrayList<Agenda> findByFournisseurId(long fournisseurId);
}
