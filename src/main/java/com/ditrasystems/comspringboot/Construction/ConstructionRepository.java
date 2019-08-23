package com.ditrasystems.comspringboot.Construction;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface ConstructionRepository  extends JpaRepository<Construction,Long> {
    Construction findByMatierePrimaireAndProduitFini(long idMP,long idPF);
    ArrayList<Construction> findAllByProduitFini(long idPF);
}
