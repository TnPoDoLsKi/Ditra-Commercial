package com.ditrasystems.comspringboot.ArticleBonLivraison;

import com.ditrasystems.comspringboot.Articles.Article;
import com.ditrasystems.comspringboot.BonDeCommande.BonDeCommande;
import com.ditrasystems.comspringboot.BonDeLivraison.BonDeLivrasion;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ArticleBonLivraison {

  @Id
  private long id;

  @ManyToOne
  @JoinColumn(name = "article_id")
  private Article article;

  @ManyToOne
  @JoinColumn(name = "bonDeLivrasion_id")
  private BonDeLivrasion bonDeLivrasion;


  private float prix;

  private float quantite;

  public ArticleBonLivraison() {
  }
}
