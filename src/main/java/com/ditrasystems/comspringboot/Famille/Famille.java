package com.ditrasystems.comspringboot.Famille;

import com.ditrasystems.comspringboot.Articles.Article;
import com.ditrasystems.comspringboot.Marge.Marge;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Getter
@Setter
@SQLDelete(sql=" UPDATE famille SET deleted =true WHERE id = ?")
@Where(clause = "deleted = false")
public class Famille {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @JsonIgnore
  private boolean deleted;

  private String nom;

  @JsonIgnore
  @OneToMany(mappedBy = "famille")
  private Collection<Article> articles = new ArrayList<>();

}
