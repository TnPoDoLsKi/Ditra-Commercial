package com.ditrasystems.comspringboot.Paiement;

import com.ditrasystems.comspringboot.Articles.Article;
import com.ditrasystems.comspringboot.Banque.Banque;
import com.ditrasystems.comspringboot.BonDeLivraison.BonDeLivrasion;
import com.ditrasystems.comspringboot.Caisse.Caisse;
import com.ditrasystems.comspringboot.Facture.Facture;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class Paiement {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;


  @ManyToMany
  @JoinTable(name = "paiement_BonDeLivrasion",
      joinColumns = { @JoinColumn(name = "paiementId") },
      inverseJoinColumns = { @JoinColumn(name = "BonDeLivrasionId") })
  private Collection<BonDeLivrasion> bonDeLivrasions =new ArrayList<>();

  @ManyToMany
  @JoinTable(name = "paiement_facture",
      joinColumns = { @JoinColumn(name = "paiementId") },
      inverseJoinColumns = { @JoinColumn(name = "factureId") })
  private Collection<Facture> factures =new ArrayList<>();

  @ManyToOne
  Banque banque;

  @ManyToOne
  Caisse caisse;

  public Paiement() {
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }
}
