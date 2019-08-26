package com.ditrasystems.comspringboot.ArticleOffre;

import com.ditrasystems.comspringboot.Articles.Article;
import com.ditrasystems.comspringboot.DemandeOffre.DemandeOffre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArticleOffreRepository extends JpaRepository<ArticleOffre ,Long> {

  ArticleOffre findArticleOffreByArticleAndDemandeOffre(Article article, DemandeOffre demandeOffre);

}
