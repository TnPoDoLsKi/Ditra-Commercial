package com.ditrasystems.comspringboot.Articles.Models;

import com.ditrasystems.comspringboot.Famille.Famille;

public class ArticleModelGetAll {

    private String code;
    private String designation;
    private float stock;
    private String type;
    private float PAchatTTC;
    private Famille famille;
    private String codeFournisseur;
    private String nomFournisseur;

    public ArticleModelGetAll() {}

    public ArticleModelGetAll(String code, String designation, float stock, String type, float PAchatTTC, Famille famille, String codeFournisseur,String nomFournisseur) {
        this.code = code;
        this.designation = designation;
        this.stock = stock;
        this.type = type;
        this.PAchatTTC = PAchatTTC;
        this.famille = famille;
        this.codeFournisseur = codeFournisseur;
        this.nomFournisseur = nomFournisseur;
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

    public float getStock() {
        return stock;
    }

    public void setStock(float stock) {
        this.stock = stock;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getPAchatTTC() {
        return PAchatTTC;
    }

    public void setPAchatTTC(float PAchatTTC) {
        this.PAchatTTC = PAchatTTC;
    }

    public Famille getFamille() {
        return famille;
    }

    public void setFamille(Famille famille) {
        this.famille = famille;
    }

    public String getCodeFournisseur() {
        return codeFournisseur;
    }

    public void setCodeFournisseur(String codeFournisseur) {
        this.codeFournisseur = codeFournisseur;
    }

    public String getNomFournisseur() {
        return nomFournisseur;
    }

    public void setNomFournisseur(String nomFournisseur) {
        this.nomFournisseur = nomFournisseur;
    }
}
