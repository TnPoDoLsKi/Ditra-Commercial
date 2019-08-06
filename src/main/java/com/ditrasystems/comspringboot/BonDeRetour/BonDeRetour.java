package com.ditrasystems.comspringboot.BonDeRetour;

import com.ditrasystems.comspringboot.Avoir.Avoir;
import com.ditrasystems.comspringboot.BonDeLivraison.BonDeLivrasion;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@SQLDelete(sql=" UPDATE bon_de_retour SET deleted =true WHERE id = ?")
@Where(clause = "deleted = false")
public class BonDeRetour {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String type;

  private boolean deleted;


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
