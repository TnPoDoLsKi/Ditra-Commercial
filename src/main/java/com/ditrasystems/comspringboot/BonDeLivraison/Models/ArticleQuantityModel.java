package com.ditrasystems.comspringboot.BonDeLivraison.Models;


public class ArticleQuantityModel {

  private  Long article;

  private  Float quantity;

  private  Float prix;

  private Long bonDeCommande;

  public ArticleQuantityModel() {
  }

  public Long getArticle() {
    return article;
  }

  public void setArticle(Long article) {
    this.article = article;
  }

  public Float getQuantity() {
    return quantity;
  }

  public void setQuantity(Float quantity) {
    this.quantity = quantity;
  }

  public Long getBonDeCommande() {
    return bonDeCommande;
  }

  public void setBonDeCommande(Long bonDeCommande) {
    this.bonDeCommande = bonDeCommande;
  }

  public Float getPrix() {
    return prix;
  }

  public void setPrix(Float prix) {
    this.prix = prix;
  }
}
