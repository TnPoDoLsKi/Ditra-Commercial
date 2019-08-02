package com.ditrasystems.comspringboot.BonDeLivraison;

import com.ditrasystems.comspringboot.ArticleBonLivraison.ArticleBonLivraison;
import com.ditrasystems.comspringboot.Articles.Article;
import com.ditrasystems.comspringboot.Avoir.Avoir;
import com.ditrasystems.comspringboot.BonDeCommande.BonDeCommande;
import com.ditrasystems.comspringboot.BonDeRetour.BonDeRetour;
import com.ditrasystems.comspringboot.Facture.Facture;
import com.ditrasystems.comspringboot.Fornisseur.Fornisseur;
import com.ditrasystems.comspringboot.Marge.Marge;
import com.ditrasystems.comspringboot.Paiement.Paiement;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class BonDeLivrasion {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String type;

  @OneToMany(mappedBy = "bonDeLivrasion")
  private Collection<ArticleBonLivraison> articleBonLivraisons;


  @ManyToMany(mappedBy = "bonDeLivrasions")
  private Collection<Paiement> paiements;

  @OneToMany(mappedBy = "bonDeLivrasion")
  private Collection<BonDeCommande> bonDeCommandes = new ArrayList<>();


  @ManyToOne
  Fornisseur fornisseur;

  @ManyToOne
  Facture facture;

  @OneToOne(mappedBy = "bonDeLivrasion", cascade = CascadeType.ALL)
  private BonDeRetour bonDeRetour;

  public BonDeLivrasion() {
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


}
