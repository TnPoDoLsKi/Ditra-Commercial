package com.ditrasystems.comspringboot.DemandeOffre.Models;
import com.ditrasystems.comspringboot.DemandeOffre.DemandeOffre;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;

@FieldDefaults(level= AccessLevel.PRIVATE)
@Getter
@Setter
public class GetByIdModel {

    DemandeOffre Offre;

    ArrayList<ArticleOffreModel> articles= new ArrayList<>();

    public GetByIdModel() {
    }
}
