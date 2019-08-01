package com.ditrasystems.comspringboot.Banque;

import com.ditrasystems.comspringboot.Marge.Marge;
import com.ditrasystems.comspringboot.Paiement.Paiement;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class Banque {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String type;

  @OneToMany(mappedBy = "banque")
  private Collection<Paiement> paiements = new ArrayList<>();

  public Banque() {
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
