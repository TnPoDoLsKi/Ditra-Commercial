package com.ditrasystems.comspringboot.DemandeOffre;

import com.ditrasystems.comspringboot.ArticleOffre.ArticleOffre;
import com.ditrasystems.comspringboot.Articles.Article;
import com.ditrasystems.comspringboot.Fournisseur.Fournisseur;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Entity
@SQLDelete(sql=" UPDATE demande_offre SET deleted =true WHERE id = ?")
@Where(clause = "deleted = false")
public class DemandeOffre {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private Date date;

  private boolean deleted;

  private String code;

  @OneToMany(mappedBy = "demandeOffre",cascade = CascadeType.ALL)
  private Collection<ArticleOffre> articleOffres;

  @ManyToOne
  private Fournisseur fournisseur;

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

  public boolean isDeleted() {
    return deleted;
  }

  public void setDeleted(boolean deleted) {
    this.deleted = deleted;
  }

  public Collection<ArticleOffre> getArticleOffres() {
    return articleOffres;
  }

  public void setArticleOffres(Collection<ArticleOffre> articleOffres) {
    this.articleOffres = articleOffres;
  }

  public Fournisseur getFournisseur() {
    return fournisseur;
  }

  public void setFournisseur(Fournisseur fournisseur) {
    this.fournisseur = fournisseur;
  }
}
