package com.ditrasystems.comspringboot.DemandeOffre.Models;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level= AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ArticleOffreModel {

     String codeArticle;

     float quantiteDemander;

     float quantiteStock;

     String designation;
}
