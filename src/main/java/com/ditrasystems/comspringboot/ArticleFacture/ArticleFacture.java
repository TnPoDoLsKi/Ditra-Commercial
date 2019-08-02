package com.ditrasystems.comspringboot.ArticleFacture;

import com.ditrasystems.comspringboot.Articles.Article;
import com.ditrasystems.comspringboot.BonDeCommande.BonDeCommande;
import com.ditrasystems.comspringboot.Facture.Facture;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ArticleFacture {


  @Id
  private long id;

  @ManyToOne
  @JoinColumn(name = "article_id")
  private Article article;

  @ManyToOne
  @JoinColumn(name = "facture_id")
  private Facture facture;


  private float prix;

  private float quantite;

  public ArticleFacture() {
  }
}
