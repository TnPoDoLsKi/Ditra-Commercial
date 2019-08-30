package com.ditrasystems.comspringboot.Articles.Models;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level= AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MatierePremierQuantity {

    String code;

    float quantity;

}
