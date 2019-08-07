package com.ditrasystems.comspringboot.ArticleBonCommande;

import com.ditrasystems.comspringboot.Articles.Article;
import com.ditrasystems.comspringboot.BonDeCommande.BonDeCommande;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@SQLDelete(sql=" UPDATE article_bon_commande SET deleted =true WHERE id = ?")
@Where(clause = "deleted = false")
public class ArticleBonCommande {


  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private float quantiteCommander;

  private float quantiteLivrer;

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

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public float getQuantiteCommander() {
    return quantiteCommander;
  }

  public void setQuantiteCommander(float quantiteCommander) {
    this.quantiteCommander = quantiteCommander;
  }

  public float getQuantiteLivrer() {
    return quantiteLivrer;
  }

  public void setQuantiteLivrer(float quantiteLivrer) {
    this.quantiteLivrer = quantiteLivrer;
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

  public BonDeCommande getBonDeCommande() {
    return bonDeCommande;
  }

  public void setBonDeCommande(BonDeCommande bonDeCommande) {
    this.bonDeCommande = bonDeCommande;
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
