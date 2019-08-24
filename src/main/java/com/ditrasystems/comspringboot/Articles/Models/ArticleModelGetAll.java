package com.ditrasystems.comspringboot.Articles.Models;

import com.ditrasystems.comspringboot.Famille.Famille;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level= AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ArticleModelGetAll {

     String code;
     String designation;
     float stock;
     String type;
     float PAchatTTC;
     Famille famille;
     String codeFournisseur;
     String nomFournisseur;

}
