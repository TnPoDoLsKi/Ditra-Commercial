package com.ditrasystems.comspringboot.ArticleOffre;

import com.ditrasystems.comspringboot.Articles.Article;
import com.ditrasystems.comspringboot.DemandeOffre.DemandeOffre;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@SQLDelete(sql=" UPDATE article_offre SET deleted =true WHERE id = ?")
@Where(clause = "deleted = false")
public class ArticleOffre {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private float quantiteDemander;

  private float quantiteStock;

  private String designation;


  @JsonIgnore
  private boolean deleted;

  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "article_id")
  private Article article;

  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "demandeOffre_id")
  private DemandeOffre demandeOffre;

  public ArticleOffre() {
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

  public Article getArticle() {
    return article;
  }

  public void setArticle(Article article) {
    this.article = article;
  }

  public DemandeOffre getDemandeOffre() {
    return demandeOffre;
  }

  public void setDemandeOffre(DemandeOffre demandeOffre) {
    this.demandeOffre = demandeOffre;
  }

  public float getQuantiteDemander() {
    return quantiteDemander;
  }

  public void setQuantiteDemander(float quantiteDemander) {
    this.quantiteDemander = quantiteDemander;
  }

  public float getQuantiteStock() {
    return quantiteStock;
  }

  public void setQuantiteStock(float quantiteStock) {
    this.quantiteStock = quantiteStock;
  }

  public String getDesignation() {
    return designation;
  }

  public void setDesignation(String designation) {
    this.designation = designation;
  }
}
