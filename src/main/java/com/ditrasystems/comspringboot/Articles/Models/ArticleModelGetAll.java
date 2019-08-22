package com.ditrasystems.comspringboot.Articles.Models;

import com.ditrasystems.comspringboot.Famille.Famille;

public class ArticleModelGetAll {

    private long id;
    private String code;
    private String designation;
    private float stock;
    private String type;
    private float PAchatTTC;
    private Famille famille;

    public ArticleModelGetAll() {}

    public ArticleModelGetAll(long id, String code, String designation, float stock, String type, float PAchatTTC, Famille famille) {
        this.id = id;
        this.code = code;
        this.designation = designation;
        this.stock = stock;
        this.type = type;
        this.PAchatTTC = PAchatTTC;
        this.famille = famille;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
}
