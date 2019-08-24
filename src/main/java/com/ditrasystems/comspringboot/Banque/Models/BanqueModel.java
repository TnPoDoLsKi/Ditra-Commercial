package com.ditrasystems.comspringboot.Banque.Models;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level= AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BanqueModel {

     String nom;

     String rip;

     String agence;
}
