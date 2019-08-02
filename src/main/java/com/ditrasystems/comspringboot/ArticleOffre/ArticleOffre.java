package com.ditrasystems.comspringboot.ArticleOffre;

import com.ditrasystems.comspringboot.Articles.Article;
import com.ditrasystems.comspringboot.BonDeCommande.BonDeCommande;
import com.ditrasystems.comspringboot.DemandeOffre.DemandeOffre;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ArticleOffre {
  @Id
  private long id;

  @ManyToOne
  @JoinColumn(name = "article_id")
  private Article article;

  @ManyToOne
  @JoinColumn(name = "demandeOffre_id")
  private DemandeOffre demandeOffre;


  private float prix;

  private float quantite;

  public ArticleOffre() {
  }
}
