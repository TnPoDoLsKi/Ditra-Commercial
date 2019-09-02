package com.ditrasystems.comspringboot.Articles;


import com.ditrasystems.comspringboot.ArticleCommande.ArticleCommande;
import com.ditrasystems.comspringboot.ArticleLivraison.ArticleBonLivraison;
import com.ditrasystems.comspringboot.ArticleFacture.ArticleFacture;
import com.ditrasystems.comspringboot.ArticleOffre.ArticleOffre;
import com.ditrasystems.comspringboot.Construction.Construction;
import com.ditrasystems.comspringboot.Famille.Famille;
import com.ditrasystems.comspringboot.Fournisseur.Fournisseur;
import com.ditrasystems.comspringboot.Marge.Marge;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;


import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Getter
@Setter
@SQLDelete(sql=" UPDATE article SET deleted =true WHERE id = ?")
@Where(clause = "deleted = false")
public class Article implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String code;

  @JsonIgnore
  private boolean deleted;

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
  private Collection<ArticleCommande> articleCommandes = new ArrayList<>();;

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


  public void  addConstruction(Construction construction){
    constructions.add(construction);
  }

  public void  addMarge(Marge marge){
    marges.add(marge);
  }

}
