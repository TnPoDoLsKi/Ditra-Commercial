package com.ditrasystems.comspringboot.Famille;

import com.ditrasystems.comspringboot.Articles.Article;
import com.ditrasystems.comspringboot.Marge.Marge;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@SQLDelete(sql=" UPDATE famille SET deleted =true WHERE id = ?")
@Where(clause = "deleted = false")
public class Famille {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private boolean deleted;

  private String nom;

  @OneToMany(mappedBy = "famille")
  private Collection<Article> articles = new ArrayList<>();


  public Famille() {
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getNom() {
    return nom;
  }

  public void setNom(String nom) {
    this.nom = nom;
  }

  public Collection<Article> getArticles() {
    return articles;
  }

  public void setArticles(Collection<Article> articles) {
    this.articles = articles;
  }
}
