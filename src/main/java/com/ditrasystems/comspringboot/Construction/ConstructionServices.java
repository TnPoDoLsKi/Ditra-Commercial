package com.ditrasystems.comspringboot.Construction;

import com.ditrasystems.comspringboot.Articles.Article;
import com.ditrasystems.comspringboot.Articles.ArticleRepository;
import com.ditrasystems.comspringboot.Construction.Construction;
import com.ditrasystems.comspringboot.Utils.ErrorResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ConstructionServices {

  @Autowired
  ConstructionRepository constructionRepository;

  @Autowired
  ArticleRepository articleRepository;

  public ResponseEntity<?> edit(Long id, Long matiereId, Float quantity) {
    Optional<Construction> construction = constructionRepository.findById(id);

    if (!construction.isPresent()){
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),614,"Construction n'existe pas");
      return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
    }

    Optional<Article> article = articleRepository.findById(matiereId);

    if (!article.isPresent()){
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),609,"Article n'existe pas");
      return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
    }

    if (quantity !=null){
      construction.get().setQuantite(quantity);
    }

    construction.get().setMatierePrimaire(article.get());

    constructionRepository.save(construction.get());
    return new ResponseEntity<>(HttpStatus.OK);
  }

  public ResponseEntity<?> delete(Long id) {
    Optional<Construction> construction = constructionRepository.findById(id);

    if (!construction.isPresent()){
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),614,"Construction n'existe pas");
      return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
    }


    constructionRepository.delete(construction.get());
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
