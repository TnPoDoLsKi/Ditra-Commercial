package com.ditrasystems.comspringboot.DemandeOffre.Models;
import com.ditrasystems.comspringboot.DemandeOffre.DemandeOffre;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.Date;

@FieldDefaults(level= AccessLevel.PRIVATE)
@Getter
@Setter
public class GetByIdModel {
    //    //{ id, code, fournisseur, date, articles : [{ codeArticle, designation, quantiteStock, quantiteDemander }] }
    long id;

/*    String code;

    Date date;

    String codeFournisseur;

    String designation ;*/

    DemandeOffre demandeOffre;

    ArrayList<ArticleOffreModel> articleOffreModels = new ArrayList<>();

    public GetByIdModel() {
    }
}
