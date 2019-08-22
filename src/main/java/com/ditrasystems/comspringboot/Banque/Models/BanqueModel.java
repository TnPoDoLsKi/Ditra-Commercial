package com.ditrasystems.comspringboot.Banque.Models;

public class BanqueModel {

    private String nom;

    private String rip;

    private String agence;

    public BanqueModel() {}

    public BanqueModel(String nom, String rip, String agence) {
        this.nom = nom;
        this.rip = rip;
        this.agence = agence;
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
}
