package com.ditrasystems.comspringboot.Construction;

import com.ditrasystems.comspringboot.Articles.Article;
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

  private boolean deleted;

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
