package com.ditrasystems.comspringboot.Commande.Models;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level= AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CommandeArticleModel {

    String codeArticle;

    String designation;

    float quantityCommander;

    float quantiteStock;

    float quantityLivrer ;

    float PAchatHT;

    float PAchatTTC;

    float fodec;

    float tva;
}
