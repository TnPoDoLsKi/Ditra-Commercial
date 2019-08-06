package com.ditrasystems.comspringboot.Articles.Models;

public class MatierePremierQuantity {

  private  long id;

  private  float quantity;

  public MatierePremierQuantity(long id, float quantity) {
    this.id = id;
    this.quantity = quantity;
  }

  public MatierePremierQuantity() {
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public float getQuantity() {
    return quantity;
  }

  public void setQuantity(float quantity) {
    this.quantity = quantity;
  }
}
