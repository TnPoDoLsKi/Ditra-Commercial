package com.ditrasystems.comspringboot.Livraison.Models;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level= AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ArticleQuantityModel {

   String article;

   Float quantity;

   Float prix;

   String codeCommande;
}
