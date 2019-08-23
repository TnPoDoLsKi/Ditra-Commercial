package com.ditrasystems.comspringboot.Articles.Models;

public class MatierePremierQuantity {

  private  String code;

  private  float quantity;

  public MatierePremierQuantity(String code, float quantity) {
    this.code = code;
    this.quantity = quantity;
  }

  public MatierePremierQuantity() {
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public float getQuantity() {
    return quantity;
  }

  public void setQuantity(float quantity) {
    this.quantity = quantity;
  }
}
