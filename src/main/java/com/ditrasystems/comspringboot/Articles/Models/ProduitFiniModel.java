package com.ditrasystems.comspringboot.Articles.Models;

import com.ditrasystems.comspringboot.Articles.Article;

import java.util.ArrayList;
import java.util.List;

public class ProduitFiniModel {

  private Article article;

  private List<MatierePremierQuantity> matierePremierQuantities=new ArrayList<>();

  public ProduitFiniModel() {
  }

  public Article getArticle() {
    return article;
  }

  public void setArticle(Article article) {
    this.article = article;
  }

  public List<MatierePremierQuantity> getMatierePremierQuantities() {
    return matierePremierQuantities;
  }

  public void setMatierePremierQuantities(List<MatierePremierQuantity> matierePremierQuantities) {
    this.matierePremierQuantities = matierePremierQuantities;
  }
}
