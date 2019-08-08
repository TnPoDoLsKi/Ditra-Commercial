package com.ditrasystems.comspringboot.BonDeCommande;

import com.ditrasystems.comspringboot.ArticleBonCommande.ArticleBonCommande;
import com.ditrasystems.comspringboot.BonDeLivraison.BonDeLivrasion;
import com.ditrasystems.comspringboot.Fournisseur.Fournisseur;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@SQLDelete(sql=" UPDATE bon_de_commande SET deleted =true WHERE id = ?")
@Where(clause = "deleted = false")
public class BonDeCommande {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private String code;

  private Date date;

  private boolean deleted;

  @OneToMany(mappedBy = "bonDeCommande")
  private Collection<ArticleBonCommande> articleBonCommandes;

  @ManyToOne
  Fournisseur fournisseur;

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
