package com.ditrasystems.comspringboot.Marge;

import com.ditrasystems.comspringboot.Articles.Article;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@SQLDelete(sql=" UPDATE marge SET deleted =true WHERE id = ?")
@Where(clause = "deleted = false")
public class Marge {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @JsonIgnore
  private boolean deleted;

  private float quantite;

  private float PVente;

  private float margeGagner;


  @JsonIgnore
  @ManyToOne
  private Article article;


  public Marge() {
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public Article getArticle() {
    return article;
  }

  public void setArticle(Article article) {
    this.article = article;
  }

  public boolean isDeleted() {
    return deleted;
  }

  public void setDeleted(boolean deleted) {
    this.deleted = deleted;
  }

  public float getQuantite() {
    return quantite;
  }

  public void setQuantite(float quantite) {
    this.quantite = quantite;
  }

  public float getPVente() {
    return PVente;
  }

  public void setPVente(float PVente) {
    this.PVente = PVente;
  }

  public float getMargeGagner() {
    return margeGagner;
  }

  public void setMargeGagner(float margeGagner) {
    this.margeGagner = margeGagner;
  }
}
