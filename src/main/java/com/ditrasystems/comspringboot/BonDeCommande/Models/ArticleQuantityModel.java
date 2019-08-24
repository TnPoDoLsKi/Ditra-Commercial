package com.ditrasystems.comspringboot.BonDeCommande.Models;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level= AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ArticleQuantityModel {

  private  String codeArticle;
  private  Float quantity;
}
