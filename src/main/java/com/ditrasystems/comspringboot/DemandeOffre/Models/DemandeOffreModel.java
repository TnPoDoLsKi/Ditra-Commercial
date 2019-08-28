package com.ditrasystems.comspringboot.DemandeOffre.Models;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@FieldDefaults(level= AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DemandeOffreModel {

   List<ArticleQuantityModel> articlesQuantity = new ArrayList<>();

   String codeFournisseur ;

   String code;

   String date;
}
