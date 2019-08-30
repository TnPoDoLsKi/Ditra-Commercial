package com.ditrasystems.comspringboot.Articles;

import com.ditrasystems.comspringboot.Articles.Models.ArticleModel;
import com.ditrasystems.comspringboot.Utils.View;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class ArticleController {

  @Autowired
  ArticleServices articleServices;

  @PostMapping("/article")
  public ResponseEntity<?> create(@RequestBody ArticleModel articleModel){
    return articleServices.create(articleModel);
  }

  @GetMapping("/articles")
  public ResponseEntity<?> getAll( String type){
    return articleServices.getAll(type);
  }

  @GetMapping("/article/{code}")
  public ResponseEntity<?> getByCode(@PathVariable String code){
    return articleServices.getByCode(code);
  }


  @PutMapping("/article/{code}")
  public ResponseEntity<?> edit(@PathVariable String code,  Long familleId,  String fournisseurCode ,  String codeA,  String designation,  String type,  String codeABarre,  Float PAchatHT,  Float remise,  Float tva,  Float fodec,  Float stock ,  Float quantiteVendu,  Float stockMin,  Float prixVenteHTMin ){
    return  articleServices.edit( code , familleId , fournisseurCode,  codeA, designation, type, codeABarre, PAchatHT,  remise, tva, fodec, stock ,  quantiteVendu,  stockMin, prixVenteHTMin );
  }

  @DeleteMapping("/article/{code}")
  public ResponseEntity<?> delete(@PathVariable String code){
    return articleServices.delete(code);
  }




}
