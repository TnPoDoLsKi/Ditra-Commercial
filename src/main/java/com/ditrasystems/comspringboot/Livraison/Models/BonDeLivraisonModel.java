package com.ditrasystems.comspringboot.Livraison.Models;

import com.ditrasystems.comspringboot.Commande.Commande;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@FieldDefaults(level= AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BonDeLivraisonModel {

   List<ArticleQuantityModel> articlesQuantity = new ArrayList<>();

   List<Commande> commandes = new ArrayList<>();

   String codeFournisseur ;

   String code;
}
