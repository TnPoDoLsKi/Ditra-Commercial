package com.ditrasystems.comspringboot.Avoir;

import com.ditrasystems.comspringboot.Facture.Facture;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Avoir {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private Date date;

  private String code;

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

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public Facture getFacture() {
    return facture;
  }

  public void setFacture(Facture facture) {
    this.facture = facture;
  }
}
