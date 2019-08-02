package com.ditrasystems.comspringboot.BonDeCommande;

import com.ditrasystems.comspringboot.ArticleBonCommande.ArticleBonCommande;
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

  @OneToMany(mappedBy = "bonDeCommande")
  private Collection<ArticleBonCommande> articleBonCommandes;

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

}
