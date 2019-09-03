package com.ditrasystems.comspringboot.Construction;

import com.ditrasystems.comspringboot.Articles.Article;
import com.ditrasystems.comspringboot.Articles.ArticleRepository;
import com.ditrasystems.comspringboot.Construction.Construction;
import com.ditrasystems.comspringboot.Construction.Models.ConstructionModelGetALL;
import com.ditrasystems.comspringboot.Utils.ErrorResponseModel;
import com.ditrasystems.comspringboot.Utils.Utils;
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

    Optional<Article> articlePFOptional = articleRepository.findByCode(codePF);
    Article articlePF = articlePFOptional.get();

    if (!articlePFOptional.isPresent()){
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),634,"articlePF n'existe pas");
      return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
    }

    if (!articlePF.getType().equals("PF")){
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),611,"Article n'est pas PF");
      return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
    }


    Optional<Article> articleMPOptional = articleRepository.findByCode(codeMP);
    Article articleMP = articlePFOptional.get();

    if (!articleMPOptional.isPresent()){
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

    return new ResponseEntity<>(constructionRepository.save(construction),HttpStatus.OK);
  }

  public ResponseEntity<?> getAll(String codePF) {

    ArrayList<ConstructionModelGetALL> constructionModelGetALLS = new ArrayList<>();

    Optional<Article> articlePFOptional = articleRepository.findByCode(codePF);
    Article articlePF = articlePFOptional.get();

    if (!articlePFOptional.isPresent()){
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),634,"articlePF n'existe pas");
      return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
    }

    ArrayList<Construction> construction = constructionRepository.findAllByProduitFini(articlePF);

    if (construction.size() == 0){
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),614,"Construction n'existe pas");
      return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
    }
    ConstructionModelGetALL constructionModelGetALL = new ConstructionModelGetALL();
    for (Construction construction1 : construction){

      Article article = construction1.getMatierePrimaire();
      float PAchatTTC = article.getPAchatHT() + ((article.getPAchatHT() * article.getTva()) / 100) + ((article.getPAchatHT() * article.getFodec()) / 100);

      constructionModelGetALL.setCodeMP(construction1.getMatierePrimaire().getCode());
      constructionModelGetALL.setQuantite(construction1.getQuantite());
      constructionModelGetALL.setDesignation(construction1.getMatierePrimaire().getDesignation());
      constructionModelGetALL.setPAchatTTC(PAchatTTC);

      constructionModelGetALLS.add(constructionModelGetALL);
    }

    return new ResponseEntity<>(constructionModelGetALLS,HttpStatus.OK);
  }


  public ResponseEntity<?> edit(String codePF, String codeMP,Construction construction) {

    Optional<Article> articlePFOptional = articleRepository.findByCode(codePF);
    Article articlePF = articlePFOptional.get();

    if (!articlePFOptional.isPresent()){
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),634,"articlePF n'existe pas");
      return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
    }

    Optional<Article> articleMPOptional = articleRepository.findByCode(codeMP);
    Article articleMP = articlePFOptional.get();

    if (articleMP == null){
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),635,"articleMP n'existe pas");
      return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
    }

    Construction constructionLocal = constructionRepository.findByMatierePrimaireAndProduitFini(articleMP,articlePF);

    if (constructionLocal == null){
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),614,"Construction n'existe pas");
      return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
    }

    if (construction.getMatierePrimaire().getCode() !=null){
      Optional<Article> articleMPUpdate = articleRepository.findByCode(construction.getMatierePrimaire().getCode());
      if (!articleMPUpdate.isPresent()){
        ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),635,"articleMP n'existe pas");
        return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
      }
    }

    construction = Utils.merge(constructionLocal,construction);

    constructionRepository.save(construction);

    return new ResponseEntity<>(HttpStatus.OK);
  }


  public ResponseEntity<?> delete(String codePF, String codeMP) {
    Optional<Article> articlePF = articleRepository.findByCode(codePF);
    if (!articlePF.isPresent()){
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),634,"articlePF n'existe pas");
      return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
    }

    Optional<Article> articleMP = articleRepository.findByCode(codeMP);
    if (!articleMP.isPresent()){
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),635,"articleMP n'existe pas");
      return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
    }

    Construction construction = constructionRepository.findByMatierePrimaireAndProduitFini(articleMP.get(),articlePF.get());

    if (construction == null){
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),614,"Construction n'existe pas");
      return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
    }


    constructionRepository.delete(construction);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
