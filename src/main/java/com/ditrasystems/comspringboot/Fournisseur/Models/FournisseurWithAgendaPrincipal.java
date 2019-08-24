package com.ditrasystems.comspringboot.Fournisseur.Models;

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

}
