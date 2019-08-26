package com.ditrasystems.comspringboot.ArticleCommande;

import com.ditrasystems.comspringboot.Articles.Article;
import com.ditrasystems.comspringboot.Commande.BonDeCommande;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArticleBonCommandeRepository extends JpaRepository<ArticleBonCommande,Long> {

  Optional<ArticleBonCommande> findArticleBonCommandeByArticleAndBonDeCommande(Article article, BonDeCommande bonDeCommande);
}
