package com.ditrasystems.comspringboot.BonDeLivraison.Models;

import com.ditrasystems.comspringboot.BonDeCommande.BonDeCommande;
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

   List<BonDeCommande> bonDeCommandes = new ArrayList<>();

   String codeFournisseur ;

   String code;
}
