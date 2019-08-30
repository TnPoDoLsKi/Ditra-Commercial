package com.ditrasystems.comspringboot.DemandeOffre;

import com.ditrasystems.comspringboot.ArticleOffre.ArticleOffre;
import com.ditrasystems.comspringboot.Fournisseur.Fournisseur;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Entity
@SQLDelete(sql=" UPDATE demande_offre SET deleted =true WHERE code = ?")
@Where(clause = "deleted = false")
public class DemandeOffre {

  @Id
  private String code;

  @JsonFormat(pattern = "dd/MM/yyyy")
  private Date date;

  @JsonIgnore
  private boolean deleted;

  @JsonIgnore
  @OneToMany(mappedBy = "demandeOffre",cascade = CascadeType.ALL)
  private Collection<ArticleOffre> articleOffres = new ArrayList<>();

  @ManyToOne
  private Fournisseur fournisseur;

  public DemandeOffre() {
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
