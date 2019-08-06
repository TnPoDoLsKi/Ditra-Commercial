package com.ditrasystems.comspringboot.ArticleBonCommande;

import com.ditrasystems.comspringboot.Articles.Article;
import com.ditrasystems.comspringboot.BonDeCommande.BonDeCommande;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
@SQLDelete(sql=" UPDATE article_bon_commande SET deleted =true WHERE id = ?")
@Where(clause = "deleted = false")
public class ArticleBonCommande {


  @Id
  private long id;

  private boolean deleted;

  @ManyToOne
  @JoinColumn(name = "article_id")
  private Article article;

  @ManyToOne
  @JoinColumn(name = "bonDeCommande_id")
  private BonDeCommande bonDeCommande;


  private float prix;

  private float quantite;

  public ArticleBonCommande() {
  }


}
