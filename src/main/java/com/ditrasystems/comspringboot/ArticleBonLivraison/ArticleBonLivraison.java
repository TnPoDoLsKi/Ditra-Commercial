package com.ditrasystems.comspringboot.ArticleBonLivraison;

import com.ditrasystems.comspringboot.Articles.Article;
import com.ditrasystems.comspringboot.BonDeCommande.BonDeCommande;
import com.ditrasystems.comspringboot.BonDeLivraison.BonDeLivrasion;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@SQLDelete(sql=" UPDATE article_bon_livraison SET deleted =true WHERE id = ?")
@Where(clause = "deleted = false")
public class ArticleBonLivraison {

  @Id
  private long id;

  private boolean deleted;
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
