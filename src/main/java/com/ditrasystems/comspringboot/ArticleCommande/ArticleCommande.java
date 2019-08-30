package com.ditrasystems.comspringboot.ArticleCommande;

import com.ditrasystems.comspringboot.Articles.Article;
import com.ditrasystems.comspringboot.Commande.Commande;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@SQLDelete(sql=" UPDATE article_commande SET deleted =true WHERE id = ?")
@Where(clause = "deleted = false")
public class ArticleCommande {

//bc_article , bl_article, facture_article  =>  nzidouhom : qt_stock, P.A HT, P.A TTC, TVA
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private float quantiteCommander;

  private float quantiteStock;

  private float quantiteLivrer;

  private float quantiteRestante;

  private float PAchatHT;

  private float PAchatTTC;

  private float tva;




  @JsonIgnore
  private boolean deleted;

  @ManyToOne
  private Article article;

  @ManyToOne
  private Commande commande;

  public ArticleCommande() {
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

  public Commande getCommande() {
    return commande;
  }

  public void setCommande(Commande commande) {
    this.commande = commande;
  }

  public float getQuantiteStock() {
    return quantiteStock;
  }

  public void setQuantiteStock(float quantiteStock) {
    this.quantiteStock = quantiteStock;
  }

  public float getQuantiteRestante() {
    return quantiteRestante;
  }

  public void setQuantiteRestante(float quantiteRestante) {
    this.quantiteRestante = quantiteRestante;
  }

  public float getPAchatHT() {
    return PAchatHT;
  }

  public void setPAchatHT(float PAchatHT) {
    this.PAchatHT = PAchatHT;
  }

  public float getPAchatTTC() {
    return PAchatTTC;
  }

  public void setPAchatTTC(float PAchatTTC) {
    this.PAchatTTC = PAchatTTC;
  }

  public float getTva() {
    return tva;
  }

  public void setTva(float tva) {
    this.tva = tva;
  }
}
