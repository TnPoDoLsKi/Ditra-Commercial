package com.ditrasystems.comspringboot.Commande;

import com.ditrasystems.comspringboot.Articles.Article;
import com.ditrasystems.comspringboot.Commande.Models.ArticleQuantityModel;
import com.ditrasystems.comspringboot.Commande.Models.BonDeCommandeModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("api/v1")
public class BonDeCommandeController {

  @Autowired
  BonDeCommandeServices bonDeCommandeServices;



  @PostMapping("/commande")
  public ResponseEntity<?> create(@RequestBody BonDeCommandeModel bonDeCommandeModel){
    return  bonDeCommandeServices.create(bonDeCommandeModel);
  }

  @PostMapping("/commande/{code}/article")
  public ResponseEntity<?> addArticle(@PathVariable String code, @RequestBody  ArticleQuantityModel articleQuantityModel){
    return bonDeCommandeServices.addArticle(code, articleQuantityModel);
  }


  @GetMapping("/commandes")
  public ResponseEntity<?> getAll(){
    return bonDeCommandeServices.getAll();
  }


  @GetMapping("/commande/{code}")
  public ResponseEntity<?> getByCode(@PathVariable String code){
    return bonDeCommandeServices.getByCode(code);
  }


  @PutMapping("/commande/{code}")
  public ResponseEntity<?> edit(@PathVariable String code, String codeUpdate, String fournisseurCode,String date, String etat){
    return bonDeCommandeServices.edit(code,codeUpdate,fournisseurCode,date,etat);
  }


  @DeleteMapping("/commande/{codeCommande}/article/{codeArticle}")
  public ResponseEntity<?> deleteArticle(@PathVariable String codeCommande,@PathVariable String codeArticle ){
    return bonDeCommandeServices.deleteArticle(codeCommande,codeArticle);
  }


  @DeleteMapping("/commande/{code}")
  public ResponseEntity<?> delete(@PathVariable String code){
    return bonDeCommandeServices.delete(code);
  }

}