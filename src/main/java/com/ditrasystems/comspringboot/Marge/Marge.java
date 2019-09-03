package com.ditrasystems.comspringboot.Marge;

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
@SQLDelete(sql=" UPDATE marge SET deleted =true WHERE id = ?")
@Where(clause = "deleted = false")
public class Marge {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @JsonIgnore
  private boolean deleted;

  private float quantite;

  private float PVente;

  private float margeGagner;


  @JsonIgnore
  @ManyToOne
  private Article article;

}
