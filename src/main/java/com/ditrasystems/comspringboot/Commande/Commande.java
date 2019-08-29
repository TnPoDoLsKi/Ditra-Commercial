package com.ditrasystems.comspringboot.Commande;

import com.ditrasystems.comspringboot.ArticleCommande.ArticleCommande;
import com.ditrasystems.comspringboot.Livraison.BonDeLivrasion;
import com.ditrasystems.comspringboot.Facture.Facture;
import com.ditrasystems.comspringboot.Fournisseur.Fournisseur;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Entity
@SQLDelete(sql=" UPDATE commande SET deleted =true WHERE code = ?")
@Where(clause = "deleted = false")
public class Commande {

  @Id
  private String code;

  private Date date;

  private String etat;

  @JsonIgnore
  private boolean deleted;

  @JsonIgnore
  @OneToMany(mappedBy = "commande",cascade = CascadeType.ALL)
  private Collection<ArticleCommande> articleCommandes =new ArrayList<>();

  @JsonIgnore
  @ManyToOne
  Fournisseur fournisseur;

  @JsonIgnore
  @ManyToMany(mappedBy = "commandes")
  private Collection<BonDeLivrasion> bonDeLivrasions = new ArrayList<>();


  @JsonIgnore
  @ManyToMany
  @JoinTable(name = "bonDeCommandes_facture",
      joinColumns = { @JoinColumn(name = "bonDeCommandesId") },
      inverseJoinColumns = { @JoinColumn(name = "factureId") })
  private Collection<Facture> factures =new ArrayList<>();


  public Commande() {
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

  public Collection<ArticleCommande> getArticleCommandes() {
    return articleCommandes;
  }

  public void setArticleCommandes(Collection<ArticleCommande> articleCommandes) {
    this.articleCommandes = articleCommandes;
  }

  public Collection<Facture> getFactures() {
    return factures;
  }

  public void setFactures(Collection<Facture> factures) {
    this.factures = factures;
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

  public String getEtat() {
    return etat;
  }

  public void setEtat(String etat) {
    this.etat = etat;
  }
}
