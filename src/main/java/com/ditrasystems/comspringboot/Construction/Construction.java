package com.ditrasystems.comspringboot.Construction;

import com.ditrasystems.comspringboot.Articles.Article;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@SQLDelete(sql=" UPDATE construction SET deleted =true WHERE id = ?")
@Where(clause = "deleted = false")
public class Construction {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @JsonIgnore
  private boolean deleted;

  private float quantite;

  @ManyToOne
  @JsonIgnore
  private Article produitFini;

  @ManyToOne
  @JsonIgnore
  private Article matierePrimaire;

  public Construction() {
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public boolean isDeleted() {
    return deleted;
  }

  public void setDeleted(boolean deleted) {
    this.deleted = deleted;
  }

  public float getQuantite() {
    return quantite;
  }

  public void setQuantite(float quantite) {
    this.quantite = quantite;
  }

  public Article getProduitFini() {
    return produitFini;
  }

  public void setProduitFini(Article produitFini) {
    this.produitFini = produitFini;
  }

  public Article getMatierePrimaire() {
    return matierePrimaire;
  }

  public void setMatierePrimaire(Article matierePrimaire) {
    this.matierePrimaire = matierePrimaire;
  }
}
