package com.ditrasystems.comspringboot.BonDeLivraison.Models;

import com.ditrasystems.comspringboot.BonDeCommande.BonDeCommande;

import java.util.ArrayList;
import java.util.List;

public class BonDeLivraisonModel {

  private List<ArticleQuantityModel> articlesQuantity = new ArrayList<>();

  private List<BonDeCommande> bonDeCommandes = new ArrayList<>();

  private Long fournisseur ;

  private String code;


  public BonDeLivraisonModel() {
  }


  public List<ArticleQuantityModel> getArticlesQuantity() {
    return articlesQuantity;
  }

  public void setArticlesQuantity(List<ArticleQuantityModel> articlesQuantity) {
    this.articlesQuantity = articlesQuantity;
  }

  public List<BonDeCommande> getBonDeCommandes() {
    return bonDeCommandes;
  }

  public void setBonDeCommandes(List<BonDeCommande> bonDeCommandes) {
    this.bonDeCommandes = bonDeCommandes;
  }

  public Long getFournisseur() {
    return fournisseur;
  }

  public void setFournisseur(Long fournisseur) {
    this.fournisseur = fournisseur;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }
}
