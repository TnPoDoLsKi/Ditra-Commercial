package com.ditrasystems.comspringboot.BonDeLivraison;

import com.ditrasystems.comspringboot.ArticleBonLivraison.ArticleBonLivraison;
import com.ditrasystems.comspringboot.BonDeCommande.BonDeCommande;
import com.ditrasystems.comspringboot.BonDeRetour.BonDeRetour;
import com.ditrasystems.comspringboot.Facture.Facture;
import com.ditrasystems.comspringboot.Fournisseur.Fournisseur;
import com.ditrasystems.comspringboot.Paiement.Paiement;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@SQLDelete(sql=" UPDATE bon_de_livrasion SET deleted =true WHERE id = ?")
@Where(clause = "deleted = false")
public class BonDeLivrasion {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String type;

  private boolean deleted;

  @OneToMany(mappedBy = "bonDeLivrasion")
  private Collection<ArticleBonLivraison> articleBonLivraisons;


  @ManyToMany(mappedBy = "bonDeLivrasions")
  private Collection<Paiement> paiements;

  @OneToMany(mappedBy = "bonDeLivrasion")
  private Collection<BonDeCommande> bonDeCommandes = new ArrayList<>();


  @ManyToOne
  Fournisseur fournisseur;

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
