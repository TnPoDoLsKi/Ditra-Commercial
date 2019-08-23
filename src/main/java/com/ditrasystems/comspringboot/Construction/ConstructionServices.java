package com.ditrasystems.comspringboot.Construction;

import com.ditrasystems.comspringboot.Articles.Article;
import com.ditrasystems.comspringboot.Articles.ArticleRepository;
import com.ditrasystems.comspringboot.Construction.Construction;
import com.ditrasystems.comspringboot.Construction.Models.ConstructionModelGetALL;
import com.ditrasystems.comspringboot.Utils.ErrorResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class ConstructionServices {

  @Autowired
  ConstructionRepository constructionRepository;

  @Autowired
  ArticleRepository articleRepository;

  public ResponseEntity<?> create(String codePF, String codeMP,float quantity) {

    Article articlePF = articleRepository.findByCode(codePF);
    if (articlePF == null){
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),634,"articlePF n'existe pas");
      return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
    }

    if (!articlePF.getType().equals("PF")){
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),611,"Article n'est pas PF");
      return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
    }


    Article articleMP = articleRepository.findByCode(codeMP);
    if (articleMP == null){
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),635,"articleMP n'existe pas");
      return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
    }

    if (!articleMP.getType().equals("MP")){
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),612,"Article n'est pas MP");
      return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
    }

    Construction construction=new Construction();

    construction.setMatierePrimaire(articleMP);
    construction.setProduitFini(articlePF);
    construction.setQuantite(quantity);

    constructionRepository.save(construction);
    return new ResponseEntity<>(HttpStatus.OK);
  }

/*  public ResponseEntity<?> getAll(String codePF) {
    Article articlePF = articleRepository.findByCode(codePF);
    if (articlePF == null){
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),634,"articlePF n'existe pas");
      return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
    }

    ArrayList<Construction> construction = constructionRepository.findAllByProduitFini(articlePF.getId());

    if (construction.size() == 0){
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),614,"Construction n'existe pas");
      return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
    }
    ConstructionModelGetALL constructionModelGetALL = new ConstructionModelGetALL();
    for (Construction construction1 : construction){
      constructionModelGetALL.setCodeMP(construction1.getMatierePrimaire());
      constructionModelGetALL.setQuantite(construction1.getQuantite());


    }


    return new ResponseEntity<>(construction,HttpStatus.OK);
  }*/


/*
  public ResponseEntity<?> edit(String codePF, String codeMP, Float quantity) {

    Article articlePF = articleRepository.findByCode(codePF);
    if (articlePF == null){
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),634,"articlePF n'existe pas");
      return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
    }

    Article articleMP = articleRepository.findByCode(codeMP);
    if (articleMP == null){
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),635,"articleMP n'existe pas");
      return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
    }

    Construction construction = constructionRepository.findByMatierePrimaireAndProduitFini(articleMP.getId(),articlePF.getId());

    if (construction == null){
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),614,"Construction n'existe pas");
      return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
    }

    if (quantity !=null){
      construction.setQuantite(quantity);
    }

    construction.setMatierePrimaire(articleMP);

    constructionRepository.save(construction);
    return new ResponseEntity<>(HttpStatus.OK);
  }
*/

  public ResponseEntity<?> delete(String codePF, String codeMP) {
    Article articlePF = articleRepository.findByCode(codePF);
    if (articlePF == null){
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),634,"articlePF n'existe pas");
      return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
    }

    Article articleMP = articleRepository.findByCode(codeMP);
    if (articleMP == null){
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),635,"articleMP n'existe pas");
      return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
    }

    Construction construction = constructionRepository.findByMatierePrimaireAndProduitFini(articleMP.getId(),articlePF.getId());

    if (construction == null){
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),614,"Construction n'existe pas");
      return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
    }


    constructionRepository.delete(construction);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
