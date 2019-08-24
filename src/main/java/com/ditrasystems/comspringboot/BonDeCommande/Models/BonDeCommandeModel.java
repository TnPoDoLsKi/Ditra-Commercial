package com.ditrasystems.comspringboot.BonDeCommande.Models;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@FieldDefaults(level= AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BonDeCommandeModel {

   List<ArticleQuantityModel> articlesQuantity = new ArrayList<>();

   String codeFournisseur ;

   String code;
}
