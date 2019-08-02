package com.ditrasystems.comspringboot.Facture;

import com.ditrasystems.comspringboot.ArticleFacture.ArticleFacture;
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

  private String code;

  @OneToMany(mappedBy = "facture")
  private Collection<ArticleFacture> articleFactures;

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




}
