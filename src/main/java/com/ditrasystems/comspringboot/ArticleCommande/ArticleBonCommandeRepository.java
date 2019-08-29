package com.ditrasystems.comspringboot.ArticleCommande;

import com.ditrasystems.comspringboot.Articles.Article;
import com.ditrasystems.comspringboot.Commande.Commande;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArticleBonCommandeRepository extends JpaRepository<ArticleCommande,Long> {

  Optional<ArticleCommande> findArticleCommandeByArticleAndCommande(Article article, Commande commande);

}
