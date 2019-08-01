package com.ditrasystems.comspringboot.Famille;

import com.ditrasystems.comspringboot.Articles.Article;
import com.ditrasystems.comspringboot.Marge.Marge;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class Famille {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String type;

  @OneToMany(mappedBy = "famille")
  private Collection<Article> articles = new ArrayList<>();


  public Famille() {
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

  public Collection<Article> getArticles() {
    return articles;
  }

  public void setArticles(Collection<Article> articles) {
    this.articles = articles;
  }
}
