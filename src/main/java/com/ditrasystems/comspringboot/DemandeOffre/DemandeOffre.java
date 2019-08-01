package com.ditrasystems.comspringboot.DemandeOffre;

import com.ditrasystems.comspringboot.Articles.Article;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class DemandeOffre {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String type;

  @ManyToMany
  @JoinTable(name = "article_DemandeOffre",
      joinColumns = { @JoinColumn(name = "articleId") },
      inverseJoinColumns = { @JoinColumn(name = "DemandeOffreId") })
  private Collection<Article> articles =new ArrayList<>();


  public DemandeOffre() {
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
