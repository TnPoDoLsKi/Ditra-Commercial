package com.ditrasystems.comspringboot.ArticleFacture;

import com.ditrasystems.comspringboot.Articles.Article;
import com.ditrasystems.comspringboot.BonDeCommande.BonDeCommande;
import com.ditrasystems.comspringboot.Facture.Facture;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@SQLDelete(sql=" UPDATE article_facture SET deleted =true WHERE id = ?")
@Where(clause = "deleted = false")
public class ArticleFacture {


  @Id
  private long id;

  private boolean deleted;

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
