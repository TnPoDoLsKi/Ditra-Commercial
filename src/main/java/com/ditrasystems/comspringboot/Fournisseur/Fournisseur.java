package com.ditrasystems.comspringboot.Fournisseur;

import com.ditrasystems.comspringboot.Agenda.Agenda;
import com.ditrasystems.comspringboot.Banque.Banque;
import com.ditrasystems.comspringboot.Commande.Commande;
import com.ditrasystems.comspringboot.DemandeOffre.DemandeOffre;
import com.ditrasystems.comspringboot.Facture.Facture;
import com.ditrasystems.comspringboot.Livraison.BonDeLivrasion;
import com.ditrasystems.comspringboot.Articles.Article;
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
@Where(clause = "deleted = false")
@SQLDelete(sql=" UPDATE fournisseur SET deleted = true WHERE code = ?")
public class Fournisseur {

  @Id
  private String code;

  private String nom;

  private String activite;

  @JsonIgnore
  private boolean deleted;

  private String adresse;

  private String codePostal;

  private String ville;

  private String pays;

  private String codeTVA;

  private String matFiscale;

  private Float solde;

  private String email;

  private String website;

  private Float plafont_credit;

  private String observation;


  @JsonIgnore
  @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
  @JoinTable(name = "banque_fournisseur",
      joinColumns = { @JoinColumn(name = "fournisseurId") },
      inverseJoinColumns = { @JoinColumn(name = "banqueId") })
  private Collection<Banque> banques =new ArrayList<>();

  @JsonIgnore
  @OneToMany(mappedBy = "fournisseur",cascade = CascadeType.ALL)
  private Collection<Article> articles = new ArrayList<>();

  @JsonIgnore
  @OneToMany(mappedBy = "fournisseur",cascade = CascadeType.ALL)
  private Collection<BonDeLivrasion> bonDeLivrasions = new ArrayList<>();

  @JsonIgnore
  @OneToMany(mappedBy = "fournisseur",cascade = CascadeType.ALL)
  private Collection<Commande> commandes = new ArrayList<>();

  @JsonIgnore
  @OneToMany(mappedBy = "fournisseur",cascade = CascadeType.ALL)
  private Collection<Facture> factures = new ArrayList<>();

  @JsonIgnore
  @OneToMany(mappedBy = "fournisseur",cascade = CascadeType.ALL)
  private Collection<Agenda> agendas= new ArrayList<>();

  @JsonIgnore
  @OneToMany(mappedBy = "fournisseur",cascade = CascadeType.ALL)
  private Collection<DemandeOffre> demandeOffres= new ArrayList<>();

  public void addAgenda(Agenda agenda) {
    agendas.add(agenda);
  }
}
