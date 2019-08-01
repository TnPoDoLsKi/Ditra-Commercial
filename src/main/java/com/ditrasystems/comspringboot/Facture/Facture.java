package com.ditrasystems.comspringboot.Facture;

import com.ditrasystems.comspringboot.Articles.Article;
import com.ditrasystems.comspringboot.Avoir.Avoir;
import com.ditrasystems.comspringboot.BonDeCommande.BonDeCommande;
import com.ditrasystems.comspringboot.BonDeLivraison.BonDeLivrasion;
import com.ditrasystems.comspringboot.Fornisseur.Fornisseur;
import com.ditrasystems.comspringboot.Paiement.Paiement;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class Facture {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String type;

  @ManyToMany
  @JoinTable(name = "article_Facture",
      joinColumns = { @JoinColumn(name = "articleId") },
      inverseJoinColumns = { @JoinColumn(name = "FactureId") })
  private Collection<Article> articles =new ArrayList<>();

  @OneToOne(mappedBy = "facture", cascade = CascadeType.ALL)
  private Avoir avoir;

  @ManyToMany(mappedBy = "factures")
  private Collection<Paiement> paiements;

  @OneToMany(mappedBy = "facture")
  private Collection<BonDeLivrasion> bonDeLivrasions  = new ArrayList<>();

  @ManyToOne
  private Fornisseur fornisseur;

  public Facture() {
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
