package com.ditrasystems.comspringboot.ArticleBonLivraison;

import com.ditrasystems.comspringboot.Articles.Article;
import com.ditrasystems.comspringboot.BonDeCommande.BonDeCommande;
import com.ditrasystems.comspringboot.BonDeLivraison.BonDeLivrasion;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@SQLDelete(sql=" UPDATE article_bon_livraison SET deleted =true WHERE id = ?")
@Where(clause = "deleted = false")
public class ArticleBonLivraison {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
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

  private Long bonDeCommande;

  public ArticleBonLivraison() {
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

  public BonDeLivrasion getBonDeLivrasion() {
    return bonDeLivrasion;
  }

  public void setBonDeLivrasion(BonDeLivrasion bonDeLivrasion) {
    this.bonDeLivrasion = bonDeLivrasion;
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

  public Long getBonDeCommande() {
    return bonDeCommande;
  }

  public void setBonDeCommande(Long bonDeCommande) {
    this.bonDeCommande = bonDeCommande;
  }
}
