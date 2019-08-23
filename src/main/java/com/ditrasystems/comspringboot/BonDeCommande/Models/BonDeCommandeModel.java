package com.ditrasystems.comspringboot.BonDeCommande.Models;

import java.util.ArrayList;
import java.util.List;

public class BonDeCommandeModel {

  private List<ArticleQuantityModel> articlesQuantity = new ArrayList<>();

  private String codeFournisseur ;

  private String code;


  public BonDeCommandeModel() {
  }


  public List<ArticleQuantityModel> getArticlesQuantity() {
    return articlesQuantity;
  }

  public void setArticlesQuantity(List<ArticleQuantityModel> articlesQuantity) {
    this.articlesQuantity = articlesQuantity;
  }

  public String getCodeFournisseur() {
    return codeFournisseur;
  }

  public void setCodeFournisseur(String codeFournisseur) {
    this.codeFournisseur = codeFournisseur;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }


}
