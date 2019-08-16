package com.ditrasystems.comspringboot.BonDeCommande;

import com.ditrasystems.comspringboot.Articles.Article;
import com.ditrasystems.comspringboot.BonDeCommande.Models.ArticleQuantityModel;
import com.ditrasystems.comspringboot.BonDeCommande.Models.BonDeCommandeModel;
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

  @PostMapping("/bonCommande")
  public ResponseEntity<?> create(@RequestBody BonDeCommandeModel bonDeCommandeModel){
    return  bonDeCommandeServices.create(bonDeCommandeModel);
  }

  @PutMapping("/bonCommande/{id}")
  public ResponseEntity<?> edit(@PathVariable Long id , String code , Date date){
    return bonDeCommandeServices.edit(id,code,date);
  }

  @PutMapping("/bonCommande/article/{id}")
  public ResponseEntity<?> editArticle(@PathVariable Long id , @RequestBody List<ArticleQuantityModel> articleQuantityModels){
    return bonDeCommandeServices.editArticle(id,  articleQuantityModels);
  }

  @DeleteMapping("/bonCommande/article/{id}")
  public ResponseEntity<?> deleteArticle(@PathVariable Long id , List<Article> articles){
    return bonDeCommandeServices.deleteArticle(id,articles);
  }


  @DeleteMapping("/bonCommande/{id}")
  public ResponseEntity<?> delete(@PathVariable Long id){
    return bonDeCommandeServices.delete(id);
  }

  @GetMapping("/bonCommandes")
  public ResponseEntity<?> getAll(){
    return bonDeCommandeServices.getAll();
  }

  @GetMapping("/bonCommande/{id}")
  public ResponseEntity<?> getById(@PathVariable Long id){
    return bonDeCommandeServices.getById(id);
  }
}
