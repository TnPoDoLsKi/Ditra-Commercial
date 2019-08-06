package com.ditrasystems.comspringboot.Marge;

import com.ditrasystems.comspringboot.Articles.Article;
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

  private boolean deleted;

  private float quantite;

  private float prix;


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

  public float getPrix() {
    return prix;
  }

  public void setPrix(float prix) {
    this.prix = prix;
  }
}
