package com.ditrasystems.comspringboot.Facture;

import com.ditrasystems.comspringboot.ArticleFacture.ArticleFacture;
import com.ditrasystems.comspringboot.Avoir.Avoir;
import com.ditrasystems.comspringboot.Commande.Commande;
import com.ditrasystems.comspringboot.Livraison.BonDeLivrasion;

import com.ditrasystems.comspringboot.Fournisseur.Fournisseur;
import com.ditrasystems.comspringboot.Paiement.Paiement;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@SQLDelete(sql=" UPDATE facture SET deleted =true WHERE id = ?")
@Where(clause = "deleted = false")
public class Facture {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private String code;

  private boolean deleted;

  @OneToMany(mappedBy = "facture" , cascade = CascadeType.ALL)
  private Collection<ArticleFacture> articleFactures;

  @OneToOne(mappedBy = "facture", cascade = CascadeType.ALL)
  private Avoir avoir;

  @ManyToMany(mappedBy = "factures")
  private Collection<Paiement> paiements;


  @ManyToMany(mappedBy = "factures")
  private Collection<BonDeLivrasion> bonDeLivrasions = new ArrayList<>();


  @ManyToMany(mappedBy = "factures")
  private Collection<Commande> commandes = new ArrayList<>();

  @ManyToOne
  private Fournisseur fournisseur;

  public Facture() {
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }




}
