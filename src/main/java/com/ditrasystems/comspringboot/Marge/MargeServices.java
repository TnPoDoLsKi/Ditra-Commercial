package com.ditrasystems.comspringboot.Marge;

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
public class MargeServices {
  @Autowired
  MargeRepository margeRepository;

  @Autowired
  ArticleRepository articleRepository;

  public ResponseEntity<?> edit(Long id, Float quantity, Float prix) {
    Optional<Marge> marge = margeRepository.findById(id);

    if (!marge.isPresent()){
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),615,"Marge n'existe pas");
      return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
    }



    if (quantity !=null){
      marge.get().setQuantite(quantity);
    }

    if (prix !=null){
      marge.get().setPrix(prix);
    }


    margeRepository.save(marge.get());

    return new ResponseEntity<>(HttpStatus.OK);
  }

  public ResponseEntity<?> delete(Long id) {

    Optional<Marge> marge = margeRepository.findById(id);

    if (!marge.isPresent()){
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),615,"Marge n'existe pas");
      return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
    }

    margeRepository.delete(marge.get());

    return new ResponseEntity<>(HttpStatus.OK);

  }

  public ResponseEntity<?> create(Long articleId, Float quantity, Float prix) {
    Optional<Article> article = articleRepository.findById(articleId);

    if (!article.isPresent()){
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),609,"Article n'existe pas");
      return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
    }

    Marge marge = new Marge();
    marge.setPrix(prix);
    marge.setQuantite(quantity);
    marge.setArticle(article.get());

    margeRepository.save(marge);

    return new ResponseEntity<>(HttpStatus.OK);
  }
}
