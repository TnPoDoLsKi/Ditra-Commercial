package com.ditrasystems.comspringboot.Fournisseur.Models;

import com.ditrasystems.comspringboot.Fournisseur.Fournisseur;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level= AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FournisseurWithAgendaPrincipal {

    String code;
    String nom;
    String activite;
    String ville;
    Float solde;
    String telephone;

    public FournisseurWithAgendaPrincipal(Fournisseur fournisseur, String telephone) {
        this.code = fournisseur.getCode();
        this.nom = fournisseur.getNom();
        this.activite = fournisseur.getActivite();
        this.ville = fournisseur.getVille();
        this.solde = fournisseur.getSolde();
        this.telephone = telephone;
    }

}
