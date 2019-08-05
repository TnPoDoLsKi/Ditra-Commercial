package com.ditrasystems.comspringboot.Famille;


import com.ditrasystems.comspringboot.Articles.Article;
import com.ditrasystems.comspringboot.Articles.ArticleRepository;
import com.ditrasystems.comspringboot.Utils.ErrorResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FamilleService {


  @Autowired
  FamilleRepository familleRepository;

  @Autowired
  ArticleRepository articleRepository;


  public ResponseEntity<?> create(String name) {
    Famille famille=new Famille();
    famille.setNom(name);

    famille = familleRepository.save(famille);

    return new ResponseEntity<>(famille, HttpStatus.CREATED);
  }

  public ResponseEntity<?> edit(long id, String name) {

    Optional<Famille> famille = familleRepository.findById(id);

    if (!famille.isPresent()){
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),606,"Famille dosen't exist");
      return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
    }

    famille.get().setNom(name);
    familleRepository.save(famille.get());

    return new ResponseEntity<>(HttpStatus.OK);
  }

  public ResponseEntity<?> delete(long id) {

      Optional<Famille> famille = familleRepository.findById(id);

      if (!famille.isPresent()){
        ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),606,"Famille dosen't exist");
        return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
      }

      List<Article> articles=new ArrayList<>();

     for (Article article:famille.get().getArticles()){
       article.setFamille(null);
       articles.add(article);
     }

     articleRepository.saveAll(articles);

     familleRepository.delete(famille.get());

    return new ResponseEntity<>(HttpStatus.OK);
  }

  public ResponseEntity<?> getById(long id) {

    Optional<Famille> famille = familleRepository.findById(id);

    if (!famille.isPresent()){
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),606,"Famille dosen't exist");
      return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
    }

    return new ResponseEntity<>(famille.get(),HttpStatus.OK);
  }

  public ResponseEntity<?> getAll() {
    return new ResponseEntity<>(familleRepository.findAll(),HttpStatus.OK);
  }

  public ResponseEntity<?> getByCode(String name) {

    Optional<Famille> famille = familleRepository.findFamilleByNom(name);

    if (!famille.isPresent()){
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),606,"Famille dosen't exist");
      return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
    }

    return new ResponseEntity<>(famille.get(),HttpStatus.OK);
  }
}
