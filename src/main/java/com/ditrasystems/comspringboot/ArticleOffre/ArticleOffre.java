package com.ditrasystems.comspringboot.ArticleOffre;

import com.ditrasystems.comspringboot.Articles.Article;
import com.ditrasystems.comspringboot.BonDeCommande.BonDeCommande;
import com.ditrasystems.comspringboot.DemandeOffre.DemandeOffre;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@SQLDelete(sql=" UPDATE article_offre SET deleted =true WHERE id = ?")
@Where(clause = "deleted = false")
public class ArticleOffre {
  @Id
  private long id;

  private boolean deleted;

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
