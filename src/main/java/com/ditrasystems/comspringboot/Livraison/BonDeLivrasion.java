package com.ditrasystems.comspringboot.Livraison;

import com.ditrasystems.comspringboot.ArticleLivraison.ArticleBonLivraison;
import com.ditrasystems.comspringboot.Commande.BonDeCommande;
import com.ditrasystems.comspringboot.Retour.BonDeRetour;
import com.ditrasystems.comspringboot.Facture.Facture;
import com.ditrasystems.comspringboot.Fournisseur.Fournisseur;
import com.ditrasystems.comspringboot.Paiement.Paiement;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Entity
@SQLDelete(sql=" UPDATE bon_de_livrasion SET deleted =true WHERE id = ?")
@Where(clause = "deleted = false")
public class BonDeLivrasion {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private String code;

  private Date date;

  private boolean deleted;

  @OneToMany(mappedBy = "bonDeLivrasion" , cascade = CascadeType.ALL)
  private Collection<ArticleBonLivraison> articleBonLivraisons;


  @ManyToMany(mappedBy = "bonDeLivrasions")
  private Collection<Paiement> paiements;

  @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
  @JoinTable(name = "bondecommande_bondelivrasion",
      joinColumns = { @JoinColumn(name = "bondelivraisonId") },
      inverseJoinColumns = { @JoinColumn(name = "bondecommandeId") })
  private Collection<BonDeCommande> bonDeCommandes =new ArrayList<>();



  @ManyToMany
  @JoinTable(name = "bondelivraison_facture",
      joinColumns = { @JoinColumn(name = "bondelivraisonId") },
      inverseJoinColumns = { @JoinColumn(name = "factureId") })
  private Collection<Facture> factures =new ArrayList<>();


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

  public boolean isDeleted() {
    return deleted;
  }

  public void setDeleted(boolean deleted) {
    this.deleted = deleted;
  }

  public Collection<ArticleBonLivraison> getArticleBonLivraisons() {
    return articleBonLivraisons;
  }

  public void setArticleBonLivraisons(Collection<ArticleBonLivraison> articleBonLivraisons) {
    this.articleBonLivraisons = articleBonLivraisons;
  }

  public Collection<Paiement> getPaiements() {
    return paiements;
  }

  public void setPaiements(Collection<Paiement> paiements) {
    this.paiements = paiements;
  }

  public Collection<BonDeCommande> getBonDeCommandes() {
    return bonDeCommandes;
  }

  public void setBonDeCommandes(Collection<BonDeCommande> bonDeCommandes) {
    this.bonDeCommandes = bonDeCommandes;
  }

  public Fournisseur getFournisseur() {
    return fournisseur;
  }

  public void setFournisseur(Fournisseur fournisseur) {
    this.fournisseur = fournisseur;
  }

  public Facture getFacture() {
    return facture;
  }

  public void setFacture(Facture facture) {
    this.facture = facture;
  }

  public BonDeRetour getBonDeRetour() {
    return bonDeRetour;
  }

  public void setBonDeRetour(BonDeRetour bonDeRetour) {
    this.bonDeRetour = bonDeRetour;
  }

  public void addBonDeCommande(BonDeCommande bonDeCommande){
    this.bonDeCommandes.add(bonDeCommande);
  }
}
