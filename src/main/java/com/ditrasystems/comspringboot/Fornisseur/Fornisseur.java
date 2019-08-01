package com.ditrasystems.comspringboot.Fornisseur;

import com.ditrasystems.comspringboot.Articles.Article;
import com.ditrasystems.comspringboot.BonDeCommande.BonDeCommande;
import com.ditrasystems.comspringboot.BonDeLivraison.BonDeLivrasion;
import com.ditrasystems.comspringboot.Construction.Construction;
import com.ditrasystems.comspringboot.Facture.Facture;
import com.ditrasystems.comspringboot.Marge.Marge;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class Fornisseur {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private String type;

  @OneToMany(mappedBy = "fornisseur")
  private Collection<Article> articles = new ArrayList<>();


  @OneToMany(mappedBy = "fornisseur")
  private Collection<BonDeLivrasion> bonDeLivrasions = new ArrayList<>();

  @OneToMany(mappedBy = "fornisseur")
  private Collection<BonDeCommande> bonDeCommandes  = new ArrayList<>();

  @OneToMany(mappedBy = "fornisseur")
  private Collection<Facture> factures = new ArrayList<>();

  public Fornisseur() {
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
