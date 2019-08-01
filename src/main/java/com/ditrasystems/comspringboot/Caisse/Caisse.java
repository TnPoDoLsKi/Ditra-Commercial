package com.ditrasystems.comspringboot.Caisse;

import com.ditrasystems.comspringboot.Marge.Marge;
import com.ditrasystems.comspringboot.Paiement.Paiement;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class Caisse {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String type;

  @OneToMany(mappedBy = "caisse")
  private Collection<Paiement> paiements = new ArrayList<>();
  public Caisse() {
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
