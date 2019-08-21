package com.ditrasystems.comspringboot.Agenda;

import com.ditrasystems.comspringboot.Fournisseur.Fournisseur;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@SQLDelete(sql=" UPDATE agenda SET deleted =true WHERE id = ?")
@Where(clause = "deleted = false")
public class Agenda {


  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private String nom;

  private String profession;

  private String telephone_1;

  private String telephone_2;

  private String cin;

  @JsonIgnore
  private boolean deleted;

  private String email;

  private Boolean principale;

  @JsonIgnore
  @ManyToOne
  Fournisseur fournisseur;

  public Agenda() {
  }

  public Agenda(String nom, String profession, String tel1, String tel2, String cin, String email, Boolean principale, Fournisseur fournisseur) {
    this.nom = nom;
    this.profession = profession;
    this.telephone_1 = tel1;
    this.telephone_2 = tel2;
    this.cin = cin;
    this.email = email;
    this.principale = principale;
    this.fournisseur = fournisseur;
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

  public String getprofession() {
    return profession;
  }

  public void setprofession(String profession) {
    this.profession = profession;
  }

  public String getTel1() {
    return telephone_1;
  }

  public void setTel1(String tel1) {
    this.telephone_1 = tel1;
  }

  public String getTel2() {
    return telephone_2;
  }

  public void setTel2(String tel2) {
    this.telephone_2 = tel2;
  }

  public String getCin() {
    return cin;
  }

  public void setCin(String cin) {
    this.cin = cin;
  }

  public boolean isDeleted() {
    return deleted;
  }

  public void setDeleted(boolean deleted) {
    this.deleted = deleted;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Fournisseur getFournisseur() {
    return fournisseur;
  }

  public void setFournisseur(Fournisseur fournisseur) {
    this.fournisseur = fournisseur;
  }

  public String getProfession() {
    return profession;
  }

  public void setProfession(String profession) {
    this.profession = profession;
  }

  public Boolean getPrincipale() {
    return principale;
  }

  public void setPrincipale(Boolean principale) {
    this.principale = principale;
  }
}
