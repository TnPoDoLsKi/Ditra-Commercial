package com.ditrasystems.comspringboot.Caisse;

import com.ditrasystems.comspringboot.Marge.Marge;
import com.ditrasystems.comspringboot.Paiement.Paiement;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@SQLDelete(sql=" UPDATE caisse SET deleted =true WHERE id = ?")
@Where(clause = "deleted = false")
public class Caisse {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private boolean deleted;

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


}
