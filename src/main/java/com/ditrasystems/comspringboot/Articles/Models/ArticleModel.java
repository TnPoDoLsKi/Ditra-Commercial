package com.ditrasystems.comspringboot.Articles.Models;

import com.ditrasystems.comspringboot.Articles.Article;
import com.ditrasystems.comspringboot.Marge.Marge;

import java.util.ArrayList;
import java.util.List;

public class ArticleModel {

  private Article article;

  private List<MatierePremierQuantity> matierePremierQuantities=new ArrayList<>();

  private List<Marge> marges = new ArrayList<>();

  public ArticleModel() {
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

  public List<Marge> getMarges() {
    return marges;
  }

  public void setMarges(List<Marge> marges) {
    this.marges = marges;
  }
}
