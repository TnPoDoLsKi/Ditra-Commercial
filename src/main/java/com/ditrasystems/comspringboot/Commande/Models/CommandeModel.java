package com.ditrasystems.comspringboot.Commande.Models;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;

@FieldDefaults(level= AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CommandeModel {

    CommandesModel commande ;

    ArrayList<CommandeArticleModel> commandeArticle = new ArrayList<>();

}
