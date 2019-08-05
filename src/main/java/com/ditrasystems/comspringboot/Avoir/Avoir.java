package com.ditrasystems.comspringboot.Avoir;

import com.ditrasystems.comspringboot.Facture.Facture;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;

@Entity
@SQLDelete(sql=" UPDATE avoir SET deleted =true WHERE id = ?")
@Where(clause = "deleted = false")
public class Avoir {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private boolean deleted;

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
