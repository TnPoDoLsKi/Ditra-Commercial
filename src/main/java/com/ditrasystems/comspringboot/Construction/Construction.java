package com.ditrasystems.comspringboot.Construction;

import com.ditrasystems.comspringboot.Articles.Article;

import javax.persistence.*;

@Entity
public class Construction {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private String type;

  @ManyToOne
  private Article produitFini;

  private Article matierePrimaire;

  public Construction() {
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
