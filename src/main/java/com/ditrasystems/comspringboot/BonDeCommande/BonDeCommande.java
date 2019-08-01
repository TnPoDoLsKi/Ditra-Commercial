package com.ditrasystems.comspringboot.BonDeCommande;

import com.ditrasystems.comspringboot.Articles.Article;
import com.ditrasystems.comspringboot.BonDeLivraison.BonDeLivrasion;
import com.ditrasystems.comspringboot.Fornisseur.Fornisseur;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class BonDeCommande {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @ManyToMany
  @JoinTable(name = "article_BonDeCommande",
      joinColumns = { @JoinColumn(name = "articleId") },
      inverseJoinColumns = { @JoinColumn(name = "BonDeCommandeId") })
  private Collection<Article> articles =new ArrayList<>();

  @ManyToOne
  Fornisseur fornisseur;

  @ManyToOne
  BonDeLivrasion bonDeLivrasion;



  public BonDeCommande() {
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public Collection<Article> getArticles() {
    return articles;
  }

  public void setArticles(Collection<Article> articles) {
    this.articles = articles;
  }
}
