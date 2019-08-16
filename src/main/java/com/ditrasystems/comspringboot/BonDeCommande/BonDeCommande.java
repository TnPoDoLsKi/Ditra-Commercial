package com.ditrasystems.comspringboot.BonDeCommande;

import com.ditrasystems.comspringboot.ArticleBonCommande.ArticleBonCommande;
import com.ditrasystems.comspringboot.BonDeLivraison.BonDeLivrasion;
import com.ditrasystems.comspringboot.Facture.Facture;
import com.ditrasystems.comspringboot.Fournisseur.Fournisseur;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Entity
@SQLDelete(sql=" UPDATE bon_de_commande SET deleted =true WHERE id = ?")
@Where(clause = "deleted = false")
public class BonDeCommande {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;


  private boolean deleted;


  private String code;


  private Date date;


  @OneToMany(mappedBy = "bonDeCommande",cascade = CascadeType.ALL)
  private Collection<ArticleBonCommande> articleBonCommandes;


  @ManyToOne
  Fournisseur fournisseur;


  @ManyToMany(mappedBy = "bonDeCommandes")
  private Collection<BonDeLivrasion> bonDeLivrasions = new ArrayList<>();


  @ManyToMany
  @JoinTable(name = "bonDeCommandes_facture",
      joinColumns = { @JoinColumn(name = "bonDeCommandesId") },
      inverseJoinColumns = { @JoinColumn(name = "factureId") })
  private Collection<Facture> factures =new ArrayList<>();


  public BonDeCommande() {
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public boolean isDeleted() {
    return deleted;
  }

  public void setDeleted(boolean deleted) {
    this.deleted = deleted;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public Collection<ArticleBonCommande> getArticleBonCommandes() {
    return articleBonCommandes;
  }

  public void setArticleBonCommandes(Collection<ArticleBonCommande> articleBonCommandes) {
    this.articleBonCommandes = articleBonCommandes;
  }

  public Fournisseur getFournisseur() {
    return fournisseur;
  }

  public void setFournisseur(Fournisseur fournisseur) {
    this.fournisseur = fournisseur;
  }

  public Collection<BonDeLivrasion> getBonDeLivrasions() {
    return bonDeLivrasions;
  }

  public void setBonDeLivrasions(Collection<BonDeLivrasion> bonDeLivrasions) {
    this.bonDeLivrasions = bonDeLivrasions;
  }
}
