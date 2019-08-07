package com.ditrasystems.comspringboot.DemandeOffre.Models;

import java.util.List;

public class ArticleQuantityModel {

  private  Long article;
  private  Float quantity;

  public ArticleQuantityModel() {
  }

  public Long getarticle() {
    return article;
  }

  public void setarticle(Long article) {
    this.article = article;
  }

  public Float getQuantity() {
    return quantity;
  }

  public void setQuantity(Float quantity) {
    this.quantity = quantity;
  }
}
