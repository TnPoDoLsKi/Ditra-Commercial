package com.ditrasystems.comspringboot.Articles;

import com.ditrasystems.comspringboot.Articles.Models.MatierePremierQuantity;
import com.ditrasystems.comspringboot.Articles.Models.ProduitFiniModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ArticleController {

  @Autowired
  ArticleServices articleServices;

  @PostMapping("/article")
  public ResponseEntity<?> create(@RequestBody ProduitFiniModel produitFiniModel){
    return articleServices.create(produitFiniModel);
  }

  @PutMapping("/article/{id}")
  public ResponseEntity<?> edit(@PathVariable long id, @RequestParam(required = false) long familleId, @RequestParam(required = false) long fournisseurId , @RequestParam(required = false) String code, @RequestParam(required = false) String designation, @RequestParam(required = false) String type, @RequestParam(required = false) String codeABarre, @RequestParam(required = false) float prixHT, @RequestParam(required = false) float remise, @RequestParam(required = false) float tva, @RequestParam(required = false) float fodec, @RequestParam(required = false) float stock , @RequestParam(required = false) float quantiteVendu, @RequestParam(required = false) float stockMin, @RequestParam(required = false) float prixVenteHTMin ){
    return  articleServices.edit( id , familleId , fournisseurId,  code, designation, type, codeABarre, prixHT,  remise, tva, fodec, stock ,  quantiteVendu,  stockMin, prixVenteHTMin );
  }

  @DeleteMapping("/article/{id}")
  public ResponseEntity<?> delete(@PathVariable long id){
    return articleServices.delete(id);
  }

  @PutMapping("/article/addMatierePremier/{id}")
  public ResponseEntity<?> addMatierePremier(@PathVariable long id, long matieresPremiers , float quantity ){
    return articleServices.addMatierePremier(id,matieresPremiers,quantity);
  }

  @GetMapping("/articles")
  public ResponseEntity<?> getAll(@RequestParam(required = false) String type){
    return articleServices.getAll(type);
  }

  @GetMapping("/article/{id}")
  public ResponseEntity<?> getById(@PathVariable long id){
    return articleServices.getById(id);
  }

  @GetMapping("/article/constructions/{id}")
  public ResponseEntity<?> getConstructions(@PathVariable long id){
    return articleServices.getConstructions(id);
  }



}
