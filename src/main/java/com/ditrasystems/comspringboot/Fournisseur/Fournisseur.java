package com.ditrasystems.comspringboot.Fournisseur;

import com.ditrasystems.comspringboot.Articles.Article;
import com.ditrasystems.comspringboot.Banque.Banque;
import com.ditrasystems.comspringboot.BonDeCommande.BonDeCommande;
import com.ditrasystems.comspringboot.BonDeLivraison.BonDeLivrasion;
import com.ditrasystems.comspringboot.Facture.Facture;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@SQLDelete(sql=" UPDATE fornisseur SET deleted =true WHERE id = ?")
@Where(clause = "deleted = false")
public class Fournisseur {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private String code;

  private String activite;

  private boolean deleted;

  private String tel1;

  private String tel2;

  private String adresse;

  private String codePostal;

  private String ville;

  private String pays;

  private String codeTVA;

  private String matFiscale;

  private String cin;

  private Float solde;

  private String email;

  private String website;


  @ManyToMany
  @JoinTable(name = "banque_fornisseur",
      joinColumns = { @JoinColumn(name = "fornisseurId") },
      inverseJoinColumns = { @JoinColumn(name = "banqueId") })
  private Collection<Banque> banques =new ArrayList<>();


  @OneToMany(mappedBy = "fornisseur")
  private Collection<Article> articles = new ArrayList<>();

  @OneToMany(mappedBy = "fornisseur")
  private Collection<BonDeLivrasion> bonDeLivrasions = new ArrayList<>();

  @OneToMany(mappedBy = "fornisseur")
  private Collection<BonDeCommande> bonDeCommandes  = new ArrayList<>();

  @OneToMany(mappedBy = "fornisseur")
  private Collection<Facture> factures = new ArrayList<>();

  public Fournisseur() {
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

  public String getActivite() {
    return activite;
  }

  public void setActivite(String activite) {
    this.activite = activite;
  }

  public String getTel1() {
    return tel1;
  }

  public void setTel1(String tel1) {
    this.tel1 = tel1;
  }

  public String getTel2() {
    return tel2;
  }

  public void setTel2(String tel2) {
    this.tel2 = tel2;
  }

  public String getAdresse() {
    return adresse;
  }

  public void setAdresse(String adresse) {
    this.adresse = adresse;
  }

  public String getCodePostal() {
    return codePostal;
  }

  public void setCodePostal(String codePostal) {
    this.codePostal = codePostal;
  }

  public String getVille() {
    return ville;
  }

  public void setVille(String ville) {
    this.ville = ville;
  }

  public String getPays() {
    return pays;
  }

  public void setPays(String pays) {
    this.pays = pays;
  }

  public String getCodeTVA() {
    return codeTVA;
  }

  public void setCodeTVA(String codeTVA) {
    this.codeTVA = codeTVA;
  }

  public String getMatFiscale() {
    return matFiscale;
  }

  public void setMatFiscale(String matFiscale) {
    this.matFiscale = matFiscale;
  }

  public String getCin() {
    return cin;
  }

  public void setCin(String cin) {
    this.cin = cin;
  }

  public Float getSolde() {
    return solde;
  }

  public void setSolde(Float solde) {
    this.solde = solde;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getWebsite() {
    return website;
  }

  public void setWebsite(String website) {
    this.website = website;
  }

  public Collection<Banque> getBanques() {
    return banques;
  }

  public void setBanques(Collection<Banque> banques) {
    this.banques = banques;
  }

  public Collection<BonDeLivrasion> getBonDeLivrasions() {
    return bonDeLivrasions;
  }

  public void setBonDeLivrasions(Collection<BonDeLivrasion> bonDeLivrasions) {
    this.bonDeLivrasions = bonDeLivrasions;
  }

  public Collection<BonDeCommande> getBonDeCommandes() {
    return bonDeCommandes;
  }

  public void setBonDeCommandes(Collection<BonDeCommande> bonDeCommandes) {
    this.bonDeCommandes = bonDeCommandes;
  }

  public Collection<Facture> getFactures() {
    return factures;
  }

  public void setFactures(Collection<Facture> factures) {
    this.factures = factures;
  }

  public Collection<Article> getArticles() {
    return articles;
  }

  public void setArticles(Collection<Article> articles) {
    this.articles = articles;
  }
}
