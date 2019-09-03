package com.ditrasystems.comspringboot.Commande;

import com.ditrasystems.comspringboot.ArticleCommande.ArticleCommande;
import com.ditrasystems.comspringboot.Livraison.BonDeLivrasion;
import com.ditrasystems.comspringboot.Facture.Facture;
import com.ditrasystems.comspringboot.Fournisseur.Fournisseur;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Entity
@Getter
@Setter
@SQLDelete(sql=" UPDATE commande SET deleted =true WHERE id = ?")
@Where(clause = "deleted = false")
public class Commande {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String code;

  private Date date;

  private String etat;

  @JsonIgnore
  private boolean deleted;

  @JsonIgnore
  @OneToMany(mappedBy = "commande",cascade = CascadeType.ALL)
  private Collection<ArticleCommande> articleCommandes =new ArrayList<>();

  @JsonIgnore
  @ManyToOne
  Fournisseur fournisseur;

  @JsonIgnore
  @ManyToMany(mappedBy = "commandes")
  private Collection<BonDeLivrasion> bonDeLivrasions = new ArrayList<>();


  @JsonIgnore
  @ManyToMany
  @JoinTable(name = "bonDeCommandes_facture",
      joinColumns = { @JoinColumn(name = "bonDeCommandesId") },
      inverseJoinColumns = { @JoinColumn(name = "factureId") })
  private Collection<Facture> factures =new ArrayList<>();

}
