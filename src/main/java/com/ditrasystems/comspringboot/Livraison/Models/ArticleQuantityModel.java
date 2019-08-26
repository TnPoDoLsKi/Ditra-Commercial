package com.ditrasystems.comspringboot.Livraison.Models;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level= AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ArticleQuantityModel {

   Long article;

   Float quantity;

   Float prix;

   Long bonDeCommande;
}
