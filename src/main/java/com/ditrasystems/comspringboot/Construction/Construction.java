package com.ditrasystems.comspringboot.Construction;

import com.ditrasystems.comspringboot.Articles.Article;

import javax.persistence.*;

@Entity
public class Construction {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private float quantite;

  @ManyToOne
  private Article produitFini;

  @ManyToOne
  private Article matierePrimaire;

  public Construction() {
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


}
