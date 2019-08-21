package com.ditrasystems.comspringboot.Fournisseur.Models;

public class FournisseurWithAgendaPrincipal {

    private String code;

    private String nom;

    private String activite;

    private String ville;

    private Float solde;

    private String telephone;

    public FournisseurWithAgendaPrincipal(String code, String nom, String activite, String ville, Float solde, String telephone) {
        this.code = code;
        this.nom = nom;
        this.activite = activite;
        this.ville = ville;
        this.solde = solde;
        this.telephone = telephone;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return nom;
    }

    public void setName(String name) {
        this.nom = name;
    }

    public String getActivite() {
        return activite;
    }

    public void setActivite(String activite) {
        this.activite = activite;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public Float getSolde() {
        return solde;
    }

    public void setSolde(Float solde) {
        this.solde = solde;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
