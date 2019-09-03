package com.ditrasystems.comspringboot.Marge;

import com.ditrasystems.comspringboot.Articles.Article;
import com.ditrasystems.comspringboot.Articles.ArticleRepository;
import com.ditrasystems.comspringboot.Construction.Construction;
import com.ditrasystems.comspringboot.Utils.ErrorResponseModel;
import com.ditrasystems.comspringboot.Utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class MargeServices {
  @Autowired
  MargeRepository margeRepository;

  @Autowired
  ArticleRepository articleRepository;

  public ResponseEntity<?> create(String codeArticle, float quantity, float prix,float margeGagner) {

    Optional<Article> articleOptional = articleRepository.findByCode(codeArticle);
    Article article = articleOptional.get();

    if (articleOptional.isPresent()){
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),609,"Article n'existe pas");
      return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
    }

    Marge marge = new Marge();
    marge.setPVente(prix);
    marge.setQuantite(quantity);
    marge.setArticle(article);
    marge.setMargeGagner(margeGagner);

    margeRepository.save(marge);

    return new ResponseEntity<>(marge,HttpStatus.OK);
  }

  public ResponseEntity<?> getByArticle(String codeArticle) {
    Optional<Article> articleOptional = articleRepository.findByCode(codeArticle);
    Article article = articleOptional.get();

    if (articleOptional.isPresent()){
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),609,"Article n'existe pas");
      return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
    }

    ArrayList<Marge> marges = margeRepository.findByArticleCode(codeArticle);

    return new ResponseEntity<>(marges,HttpStatus.OK);
  }

  public ResponseEntity<?> edit(long id, Marge marge ){
    Optional<Marge> margeLocal = margeRepository.findById(id);

    if (!margeLocal.isPresent()){
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),615,"Marge n'existe pas");
      return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
    }

    marge = Utils.merge(margeLocal.get(),marge);

    margeRepository.save(marge);

    return new ResponseEntity<>(HttpStatus.OK);
  }

  public ResponseEntity<?> delete(long id) {

    Optional<Marge> marge = margeRepository.findById(id);

    if (!marge.isPresent()){
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),615,"Marge n'existe pas");
      return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
    }

    margeRepository.delete(marge.get());

    return new ResponseEntity<>(HttpStatus.OK);

  }

}
