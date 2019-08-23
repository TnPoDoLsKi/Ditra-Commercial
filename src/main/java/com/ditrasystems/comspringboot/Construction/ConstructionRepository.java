package com.ditrasystems.comspringboot.Construction;

import com.ditrasystems.comspringboot.Articles.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface ConstructionRepository  extends JpaRepository<Construction,Long> {
    Construction findByMatierePrimaireAndProduitFini(Article codeMP,Article codePF);
   ArrayList<Construction> findAllByProduitFini(Article codePF);

}
