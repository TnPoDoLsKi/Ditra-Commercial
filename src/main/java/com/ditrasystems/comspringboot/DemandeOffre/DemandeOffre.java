package com.ditrasystems.comspringboot.DemandeOffre;

import com.ditrasystems.comspringboot.ArticleOffre.ArticleOffre;
import com.ditrasystems.comspringboot.Articles.Article;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Entity
public class DemandeOffre {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private Date date;

  private String code;

  @OneToMany(mappedBy = "demandeOffre")
  private Collection<ArticleOffre> articleOffres;


  public DemandeOffre() {
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }


}
