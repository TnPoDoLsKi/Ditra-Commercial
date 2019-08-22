package com.ditrasystems.comspringboot.Banque;

import com.ditrasystems.comspringboot.Fournisseur.Fournisseur;
import com.ditrasystems.comspringboot.Paiement.Paiement;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@SQLDelete(sql=" UPDATE banque SET deleted =true WHERE id = ?")
@Where(clause = "deleted = false")
public class Banque {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private String nom;

  private String rip;

  private String agence;

  @JsonIgnore
  private boolean deleted;

  @JsonIgnore
  @OneToMany(mappedBy = "banque")
  private Collection<Paiement> paiements = new ArrayList<>();

  @JsonIgnore
  @ManyToMany(mappedBy = "banques")
  private Collection<Fournisseur> fournisseurs = new ArrayList<>();

  public Banque() {
  }

  public Banque(String nom, String rip, String agence) {
    this.nom = nom;
    this.rip = rip;
    this.agence = agence;
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

  public String getRip() {
    return rip;
  }

  public void setRip(String rip) {
    this.rip = rip;
  }

  public String getAgence() {
    return agence;
  }

  public void setAgence(String agence) {
    this.agence = agence;
  }

  public boolean isDeleted() {
    return deleted;
  }

  public void setDeleted(boolean deleted) {
    this.deleted = deleted;
  }

  public Collection<Paiement> getPaiements() {
    return paiements;
  }

  public void setPaiements(Collection<Paiement> paiements) {
    this.paiements = paiements;
  }

  public Collection<Fournisseur> getFournisseurs() {
    return fournisseurs;
  }

  public void setFournisseurs(Collection<Fournisseur> fournisseurs) {
    this.fournisseurs = fournisseurs;
  }
}
