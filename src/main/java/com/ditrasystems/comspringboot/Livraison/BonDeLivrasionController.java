package com.ditrasystems.comspringboot.Livraison;


import com.ditrasystems.comspringboot.ArticleLivraison.ArticleBonLivraison;
import com.ditrasystems.comspringboot.Livraison.Models.BonDeLivraisonModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class BonDeLivrasionController {

  @Autowired
  BonDeLivraisonServices bonDeLivraisonServices;

  @PostMapping("/bonDeLivrasion")
  public ResponseEntity<?> create( @RequestBody BonDeLivraisonModel bonDeLivraisonModel){
    return bonDeLivraisonServices.create(bonDeLivraisonModel);
  }

  @PutMapping("/bonDeLivraison/{id}")
  public ResponseEntity<?> edit(@PathVariable Long id , String code , Date date){
    return bonDeLivraisonServices.edit(id,code,date);
  }

  @PutMapping("/bonDeLivrasion/article/{id}")
  public ResponseEntity<?> editArticle(@PathVariable Long id , @RequestBody List<ArticleBonLivraison> articleBonLivraisons){
    return bonDeLivraisonServices.editArticle(id,  articleBonLivraisons);
  }

  @DeleteMapping ("/bonDeLivrasion/article/{id}")
  public ResponseEntity<?> deleteArticle(@PathVariable Long id , @RequestBody List<ArticleBonLivraison> articleBonLivraisons){
    return bonDeLivraisonServices.deleteArticle(id,  articleBonLivraisons);
  }

 @DeleteMapping("/bonDeLivrasion/{id}")
  public ResponseEntity<?> delete(@PathVariable Long id){
    return bonDeLivraisonServices.delete(id);
  }


  @GetMapping("/bonDeLivrasions")
  public ResponseEntity<?> getAll(){
    return bonDeLivraisonServices.getAll();
  }

  @GetMapping("/bonDeLivrasion/{id}")
  public ResponseEntity<?> getById(@PathVariable Long id){
    return bonDeLivraisonServices.getById(id);
  }
 }
