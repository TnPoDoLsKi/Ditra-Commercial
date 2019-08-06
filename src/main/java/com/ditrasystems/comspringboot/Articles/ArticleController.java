package com.ditrasystems.comspringboot.Articles;

import com.ditrasystems.comspringboot.Articles.Models.ArticleModel;
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

  @PutMapping("/article/{id}")
  public ResponseEntity<?> edit(@PathVariable Long id,  Long familleId,  Long fournisseurId ,  String code,  String designation,  String type,  String codeABarre,  Float prixHT,  Float remise,  Float tva,  Float fodec,  Float stock ,  Float quantiteVendu,  Float stockMin,  Float prixVenteHTMin ){
    return  articleServices.edit( id , familleId , fournisseurId,  code, designation, type, codeABarre, prixHT,  remise, tva, fodec, stock ,  quantiteVendu,  stockMin, prixVenteHTMin );
  }

  @DeleteMapping("/article/{id}")
  public ResponseEntity<?> delete(@PathVariable Long id){
    return articleServices.delete(id);
  }

  @PutMapping("/article/addMatierePremier/{id}")
  public ResponseEntity<?> addMatierePremier(@PathVariable Long id, @RequestParam  Long matieresPremiers , @RequestParam Float quantity ){
    return articleServices.addMatierePremier(id,matieresPremiers,quantity);
  }

  @GetMapping("/articles")
  public ResponseEntity<?> getAll( String type){
    return articleServices.getAll(type);
  }

  @GetMapping("/article/{id}")
  public ResponseEntity<?> getById(@PathVariable Long id){
    return articleServices.getById(id);
  }

  @GetMapping("/article/constructions/{id}")
  public ResponseEntity<?> getConstructions(@PathVariable Long id){
    return articleServices.getConstructions(id);
  }



}
