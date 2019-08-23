package com.ditrasystems.comspringboot.Articles;


import com.ditrasystems.comspringboot.ArticleBonCommande.ArticleBonCommande;
import com.ditrasystems.comspringboot.ArticleBonLivraison.ArticleBonLivraison;
import com.ditrasystems.comspringboot.ArticleFacture.ArticleFacture;
import com.ditrasystems.comspringboot.ArticleOffre.ArticleOffre;
import com.ditrasystems.comspringboot.Construction.Construction;
import com.ditrasystems.comspringboot.Famille.Famille;
import com.ditrasystems.comspringboot.Fournisseur.Fournisseur;
import com.ditrasystems.comspringboot.Marge.Marge;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;


import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@SQLDelete(sql=" UPDATE article SET deleted =true WHERE id = ?")
@Where(clause = "deleted = false")
public class Article implements Serializable {

  @Id
 // @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @JsonIgnore
  private boolean deleted;

  private String code;

  private String designation;

  private String type;

  private String codeABarre;

  private float PAchatHT;

  private float remise;

  private float tva;

  private float fodec;

  private float stock;

  private float quantiteVendu;

  private float stockMin;
      
  private float prixVenteHTMin;

  private float PRevient;

  private float PVenteMin;

  private String image;

  @JsonIgnore
  @OneToMany(mappedBy = "article",cascade = CascadeType.ALL)
  private Collection<ArticleBonCommande> articleBonCommandes= new ArrayList<>();;

  @JsonIgnore
  @OneToMany(mappedBy = "article")
  private Collection<ArticleBonLivraison> articleBonLivraisons= new ArrayList<>();;

  @JsonIgnore
  @OneToMany(mappedBy = "article")
  private Collection<ArticleFacture> articleFactures;

  @JsonIgnore
  @OneToMany(mappedBy = "article" ,cascade = CascadeType.ALL)
  private Collection<ArticleOffre> articleOffres= new ArrayList<>();;


  @JsonIgnore
  @OneToMany(mappedBy = "article" , cascade = CascadeType.ALL )
  private Collection<Marge> marges = new ArrayList<>();

  @JsonIgnore
  @OneToMany(mappedBy = "produitFini",cascade = CascadeType.ALL)
  private Collection<Construction> constructions = new ArrayList<>();


  @JsonIgnore
  @ManyToOne
  private Famille famille;

  @JsonIgnore
  @ManyToOne
  private Fournisseur fournisseur;


  public Article() {
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

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getDesignation() {
    return designation;
  }

  public void setDesignation(String designation) {
    this.designation = designation;
  }


  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getCodeABarre() {
    return codeABarre;
  }

  public void setCodeABarre(String codeABarre) {
    this.codeABarre = codeABarre;
  }

  public float getPAchatHT() {
    return PAchatHT;
  }

  public void setPAchatHT(float PAchatHT) {
    this.PAchatHT = PAchatHT;
  }

  public float getRemise() {
    return remise;
  }

  public void setRemise(float remise) {
    this.remise = remise;
  }

  public float getTva() {
    return tva;
  }

  public void setTva(float tva) {
    this.tva = tva;
  }

  public float getFodec() {
    return fodec;
  }

  public void setFodec(float fodec) {
    this.fodec = fodec;
  }

  public float getStock() {
    return stock;
  }

  public void setStock(float stock) {
    this.stock = stock;
  }

  public float getQuantiteVendu() {
    return quantiteVendu;
  }

  public void setQuantiteVendu(float quantiteVendu) {
    this.quantiteVendu = quantiteVendu;
  }

  public float getStockMin() {
    return stockMin;
  }

  public void setStockMin(float stockMin) {
    this.stockMin = stockMin;
  }

  public float getPrixVenteHTMin() {
    return prixVenteHTMin;
  }

  public void setPrixVenteHTMin(float prixVenteHTMin) {
    this.prixVenteHTMin = prixVenteHTMin;
  }

  public Collection<ArticleBonCommande> getArticleBonCommandes() {
    return articleBonCommandes;
  }

  public void setArticleBonCommandes(Collection<ArticleBonCommande> articleBonCommandes) {
    this.articleBonCommandes = articleBonCommandes;
  }

  public Collection<ArticleBonLivraison> getArticleBonLivraisons() {
    return articleBonLivraisons;
  }

  public void setArticleBonLivraisons(Collection<ArticleBonLivraison> articleBonLivraisons) {
    this.articleBonLivraisons = articleBonLivraisons;
  }

  public Collection<ArticleFacture> getArticleFactures() {
    return articleFactures;
  }

  public void setArticleFactures(Collection<ArticleFacture> articleFactures) {
    this.articleFactures = articleFactures;
  }

  public Collection<ArticleOffre> getArticleOffres() {
    return articleOffres;
  }

  public void setArticleOffres(Collection<ArticleOffre> articleOffres) {
    this.articleOffres = articleOffres;
  }

  public Collection<Marge> getMarges() {
    return marges;
  }

  public void setMarges(Collection<Marge> marges) {
    this.marges = marges;
  }

  public Collection<Construction> getConstructions() {
    return  constructions;
  }

  public void setConstructions(Collection<Construction> constructions) {
    this.constructions = constructions;
  }

  public Famille getFamille() {
    return famille;
  }

  public void setFamille(Famille famille) {
    this.famille = famille;
  }

  public Fournisseur getFournisseur() {
    return fournisseur;
  }

  public void setFournisseur(Fournisseur fournisseur) {
    this.fournisseur = fournisseur;
  }

  public void  addConstruction(Construction construction){
    constructions.add(construction);
  }

  public void  addMarge(Marge marge){
    marges.add(marge);
  }

  public float getPRevient() {
    return PRevient;
  }

  public void setPRevient(float PRevient) {
    this.PRevient = PRevient;
  }

  public float getPVenteMin() {
    return PVenteMin;
  }

  public void setPVenteMin(float PVenteMin) {
    this.PVenteMin = PVenteMin;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }
}
