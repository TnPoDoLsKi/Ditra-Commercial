package com.ditrasystems.comspringboot.Banque;

import com.ditrasystems.comspringboot.Fournisseur.Fournisseur;
import com.ditrasystems.comspringboot.Paiement.Paiement;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@SQLDelete(sql=" UPDATE banque SET deleted =true WHERE id = ?")
@Where(clause = "deleted = false")
public class Banque {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String type;

  private boolean deleted;

  @OneToMany(mappedBy = "banque")
  private Collection<Paiement> paiements = new ArrayList<>();

  @ManyToMany(mappedBy = "banques")
  private Collection<Fournisseur> fournisseurs = new ArrayList<>();
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
