package com.ditrasystems.comspringboot.Avoir;

import com.ditrasystems.comspringboot.Facture.Facture;

import javax.persistence.*;

@Entity
public class Avoir {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private String type;

  @OneToOne
  private Facture facture;

  public Avoir() {
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
