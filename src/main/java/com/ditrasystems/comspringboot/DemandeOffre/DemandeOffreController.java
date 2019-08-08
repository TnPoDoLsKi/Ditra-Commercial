package com.ditrasystems.comspringboot.DemandeOffre;

import com.ditrasystems.comspringboot.Articles.Article;
import com.ditrasystems.comspringboot.DemandeOffre.Models.ArticleQuantityModel;
import com.ditrasystems.comspringboot.DemandeOffre.Models.DemandeOffreModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/v1")
public class DemandeOffreController {

  @Autowired
  DemandeOffreServices demandeOffreServices;

  @PostMapping("/demandeOffre")
  public ResponseEntity<?> create(@RequestBody DemandeOffreModel demandeOffreModel){
    return  demandeOffreServices.create(demandeOffreModel);
  }

  @PutMapping("/demandeOffre/{id}")
  public ResponseEntity<?> edit(@PathVariable Long id , String code , Date date){
    return demandeOffreServices.edit(id,code,date);
  }

  @PutMapping("/demandeOffre/article/{id}")
  public ResponseEntity<?> editArticle(@PathVariable Long id , @RequestBody List<ArticleQuantityModel> articleQuantityModels){
    return demandeOffreServices.editArticle(id,  articleQuantityModels);
  }

  @DeleteMapping("/demandeOffre/article/{id}")
  public ResponseEntity<?> deleteArticle(@PathVariable Long id , List<Article> articles){
    return demandeOffreServices.deleteArticle(id,articles);
  }


  @DeleteMapping("/demandeOffre/{id}")
  public ResponseEntity<?> delete(@PathVariable Long id){
    return demandeOffreServices.delete(id);
  }

}
