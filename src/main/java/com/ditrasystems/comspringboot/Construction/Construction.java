package com.ditrasystems.comspringboot.Construction;

import com.ditrasystems.comspringboot.Articles.Article;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Getter
@Setter
@SQLDelete(sql=" UPDATE construction SET deleted =true WHERE id = ?")
@Where(clause = "deleted = false")
public class Construction {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private Float quantite;

  @JsonIgnore
  private boolean deleted;

  @ManyToOne
  @JsonIgnore
  private Article produitFini;

  @ManyToOne
  @JsonIgnore
  private Article matierePrimaire;

}
