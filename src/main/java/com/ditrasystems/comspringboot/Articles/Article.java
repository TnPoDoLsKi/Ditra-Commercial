package com.ditrasystems.comspringboot.Articles;


import com.ditrasystems.comspringboot.BonDeCommande.BonDeCommande;
import com.ditrasystems.comspringboot.BonDeLivraison.BonDeLivrasion;
import com.ditrasystems.comspringboot.Construction.Construction;
import com.ditrasystems.comspringboot.DemandeOffre.DemandeOffre;
import com.ditrasystems.comspringboot.Devis.Devis;
import com.ditrasystems.comspringboot.Facture.Facture;
import com.ditrasystems.comspringboot.Famille.Famille;
import com.ditrasystems.comspringboot.Fornisseur.Fornisseur;
import com.ditrasystems.comspringboot.Marge.Marge;


import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class Article implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private String type;

  @ManyToMany(mappedBy = "articles")
  private Collection<Devis> devis;

  @ManyToMany(mappedBy = "articles")
  private Collection<BonDeCommande> bonDeCommandes;

  @ManyToMany(mappedBy = "articles")
  private Collection<BonDeLivrasion> bonDeLivrasions;

   @ManyToMany(mappedBy = "articles")
  private Collection<Facture> factures;

  @ManyToMany(mappedBy = "articles")
  private Collection<DemandeOffre> demandeOffres;



  @OneToMany(mappedBy = "article")
  private Collection<Marge> marges = new ArrayList<>();

  @OneToMany(mappedBy = "produitFini")
  private Collection<Construction> constructions = new ArrayList<>();

  @ManyToOne
  private Famille famille;

  @ManyToOne
  private Fornisseur fornisseur;

  public Article() {
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

  public Collection<Devis> getDevis() {
    return devis;
  }

  public void setDevis(Collection<Devis> devis) {
    this.devis = devis;
  }


}
