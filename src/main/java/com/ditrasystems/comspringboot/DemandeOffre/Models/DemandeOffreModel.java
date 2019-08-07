package com.ditrasystems.comspringboot.DemandeOffre.Models;

import java.util.ArrayList;
import java.util.List;

public class DemandeOffreModel {

  private List<ArticleQuantityModel> articlesQuantity = new ArrayList<>();

  private Long fournisseur ;

  private String code;


  public DemandeOffreModel() {
  }


  public List<ArticleQuantityModel> getArticlesQuantity() {
    return articlesQuantity;
  }

  public void setArticlesQuantity(List<ArticleQuantityModel> articlesQuantity) {
    this.articlesQuantity = articlesQuantity;
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
