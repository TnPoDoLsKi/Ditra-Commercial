package com.ditrasystems.comspringboot.Articles.Models;

import com.ditrasystems.comspringboot.Articles.Article;
import com.ditrasystems.comspringboot.Marge.Marge;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@FieldDefaults(level= AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ArticleModel {

   Article article;

   List<MatierePremierQuantity> matierePremierQuantities=new ArrayList<>();

   List<Marge> marges = new ArrayList<>();


}
