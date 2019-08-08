package com.ditrasystems.comspringboot.ArticleOffre;

import com.ditrasystems.comspringboot.Articles.Article;
import com.ditrasystems.comspringboot.BonDeCommande.BonDeCommande;
import com.ditrasystems.comspringboot.DemandeOffre.DemandeOffre;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@SQLDelete(sql=" UPDATE article_offre SET deleted =true WHERE id = ?")
@Where(clause = "deleted = false")
public class ArticleOffre {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
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

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public boolean isDeleted() {
    return deleted;
  }

  public void setDeleted(boolean deleted) {
    this.deleted = deleted;
  }

  public Article getArticle() {
    return article;
  }

  public void setArticle(Article article) {
    this.article = article;
  }

  public DemandeOffre getDemandeOffre() {
    return demandeOffre;
  }

  public void setDemandeOffre(DemandeOffre demandeOffre) {
    this.demandeOffre = demandeOffre;
  }

  public float getPrix() {
    return prix;
  }

  public void setPrix(float prix) {
    this.prix = prix;
  }

  public float getQuantite() {
    return quantite;
  }

  public void setQuantite(float quantite) {
    this.quantite = quantite;
  }
}
