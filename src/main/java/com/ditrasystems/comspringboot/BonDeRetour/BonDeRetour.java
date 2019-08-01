package com.ditrasystems.comspringboot.BonDeRetour;

import com.ditrasystems.comspringboot.Avoir.Avoir;
import com.ditrasystems.comspringboot.BonDeLivraison.BonDeLivrasion;

import javax.persistence.*;

@Entity
public class BonDeRetour {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String type;

  @OneToOne
  BonDeLivrasion bonDeLivrasion;

  public BonDeRetour() {
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }
}
