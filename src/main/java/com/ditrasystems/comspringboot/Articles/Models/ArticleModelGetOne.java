package com.ditrasystems.comspringboot.Articles.Models;

import com.ditrasystems.comspringboot.Articles.Article;
import com.ditrasystems.comspringboot.Construction.Construction;
import com.ditrasystems.comspringboot.Marge.Marge;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.Collection;

@FieldDefaults(level= AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ArticleModelGetOne {

     Article article;

     Collection<Construction> constructions=new ArrayList<>();

     Collection<Marge> marges = new ArrayList<>();

}
