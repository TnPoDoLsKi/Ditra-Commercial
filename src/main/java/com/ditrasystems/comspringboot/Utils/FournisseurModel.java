package com.ditrasystems.comspringboot.Utils;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level= AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FournisseurModel {

     String code;

     String nom;

     String activite;

}
