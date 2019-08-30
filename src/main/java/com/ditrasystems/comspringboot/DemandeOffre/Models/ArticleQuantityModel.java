package com.ditrasystems.comspringboot.DemandeOffre.Models;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level= AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ArticleQuantityModel {
// codeArticle, quantite, PAchatHT, fodec, TVA, PAchatTTC
    String codeArticle;
    String designation;
    Float quantiteDemander;

}
