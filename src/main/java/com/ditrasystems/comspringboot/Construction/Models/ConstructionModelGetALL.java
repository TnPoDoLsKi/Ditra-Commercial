package com.ditrasystems.comspringboot.Construction.Models;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level= AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ConstructionModelGetALL {

     String codeMP;

     String designation;

     float quantite;

     float PAchatTTC;
}
