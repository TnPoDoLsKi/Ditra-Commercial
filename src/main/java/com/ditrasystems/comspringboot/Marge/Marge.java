package com.ditrasystems.comspringboot.Marge;

import com.ditrasystems.comspringboot.Articles.Article;

import javax.persistence.*;

@Entity
public class Marge {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String type;

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

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Article getArticle() {
    return article;
  }

  public void setArticle(Article article) {
    this.article = article;
  }
}
