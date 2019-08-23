package com.ditrasystems.comspringboot.DemandeOffre.Models;

import java.util.ArrayList;
import java.util.List;

public class DemandeOffreModel {

  private List<ArticleQuantityModel> articlesQuantity = new ArrayList<>();

  private String codeFournisseur ;

  private String code;


  public DemandeOffreModel() {
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
