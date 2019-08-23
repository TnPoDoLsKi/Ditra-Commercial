package com.ditrasystems.comspringboot.Construction.Models;

public class ConstructionModelGetALL {

    private String codeMP;

    private String designation;

    private float quantite;

    private float PAchatTTC;

    public ConstructionModelGetALL(String codeMP, String designation, float quantite, float PAchatTTC) {
        this.codeMP = codeMP;
        this.designation = designation;
        this.quantite = quantite;
        this.PAchatTTC = PAchatTTC;
    }
    public ConstructionModelGetALL() {}

    public String getCodeMP() {
        return codeMP;
    }

    public void setCodeMP(String codeMP) {
        this.codeMP = codeMP;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public float getQuantite() {
        return quantite;
    }

    public void setQuantite(float quantite) {
        this.quantite = quantite;
    }

    public float getPAchatTTC() {
        return PAchatTTC;
    }

    public void setPAchatTTC(float PAchatTTC) {
        this.PAchatTTC = PAchatTTC;
    }
}
