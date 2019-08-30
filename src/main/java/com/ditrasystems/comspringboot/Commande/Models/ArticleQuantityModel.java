package com.ditrasystems.comspringboot.Commande.Models;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level= AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ArticleQuantityModel {

    String codeArticle;

    Float quantityCommander;

    Float quantityLivrer ;

    Float PAchatHT;

    Float fodec;

    Float tva;




}
