package com.ditrasystems.comspringboot.Commande.Models;

import com.ditrasystems.comspringboot.Utils.FournisseurModel;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@FieldDefaults(level= AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CommandesModel {

    String code;
    FournisseurModel fournisseurModel;
    Date date ;
    String etat;
    float totalHT;
    float totalTTC;

}
