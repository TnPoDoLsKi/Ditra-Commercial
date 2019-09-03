package com.ditrasystems.comspringboot.Articles;

import com.ditrasystems.comspringboot.Articles.Models.ArticleModel;
import com.ditrasystems.comspringboot.Fournisseur.Fournisseur;
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
  public ResponseEntity<?> editByCode(@PathVariable String code, @RequestParam Article article){
    return  articleServices.editByCode( code , article);
  }

  @DeleteMapping("/article/{code}")
  public ResponseEntity<?> deleteByCode(@PathVariable String code){
    return articleServices.deleteByCode(code);
  }




}
