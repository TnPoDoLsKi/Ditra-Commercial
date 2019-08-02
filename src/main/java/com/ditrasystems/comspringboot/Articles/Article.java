package com.ditrasystems.comspringboot.Articles;


import com.ditrasystems.comspringboot.ArticleBonCommande.ArticleBonCommande;
import com.ditrasystems.comspringboot.ArticleBonLivraison.ArticleBonLivraison;
import com.ditrasystems.comspringboot.ArticleFacture.ArticleFacture;
import com.ditrasystems.comspringboot.ArticleOffre.ArticleOffre;
import com.ditrasystems.comspringboot.Construction.Construction;
import com.ditrasystems.comspringboot.Famille.Famille;
import com.ditrasystems.comspringboot.Fornisseur.Fornisseur;
import com.ditrasystems.comspringboot.Marge.Marge;


import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class Article implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  
  private String code;
  
  private String designation;
  
  private String nom;

  private String type;

  private String codeABarre;

  private float prixHT;

  private float remise;

  private float tva;

  private float fodec;

  private float stock;

  private float quantiteVendu;

  private float stockMin;
      
  private float prixVenteHTMin;


  @OneToMany(mappedBy = "article")
  private Collection<ArticleBonCommande> articleBonCommandes;

  @OneToMany(mappedBy = "article")
  private Collection<ArticleBonLivraison> articleBonLivraisons;

   @OneToMany(mappedBy = "article")
  private Collection<ArticleFacture> articleFactures;

  @OneToMany(mappedBy = "article")
  private Collection<ArticleOffre> articleOffres;



  @OneToMany(mappedBy = "article")
  private Collection<Marge> marges = new ArrayList<>();

  @OneToMany(mappedBy = "produitFini")
  private Collection<Construction> constructions = new ArrayList<>();


  @ManyToOne
  private Famille famille;

  @ManyToOne
  private Fornisseur fornisseur;


  public Article() {
  }


}
