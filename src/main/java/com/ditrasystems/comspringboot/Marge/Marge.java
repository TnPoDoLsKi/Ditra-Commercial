package com.ditrasystems.comspringboot.Marge;

import com.ditrasystems.comspringboot.Articles.Article;

import javax.persistence.*;

@Entity
public class Marge {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

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
}
