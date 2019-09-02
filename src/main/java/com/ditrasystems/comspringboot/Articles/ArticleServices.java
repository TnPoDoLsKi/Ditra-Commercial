package com.ditrasystems.comspringboot.Articles;

import com.ditrasystems.comspringboot.Articles.Models.ArticleModelGetAll;
import com.ditrasystems.comspringboot.Articles.Models.ArticleModelGetOne;
import com.ditrasystems.comspringboot.Articles.Models.MatierePremierQuantity;
import com.ditrasystems.comspringboot.Articles.Models.ArticleModel;
import com.ditrasystems.comspringboot.Construction.Construction;
import com.ditrasystems.comspringboot.Construction.ConstructionRepository;
import com.ditrasystems.comspringboot.Famille.Famille;
import com.ditrasystems.comspringboot.Famille.FamilleRepository;
import com.ditrasystems.comspringboot.Fournisseur.Fournisseur;
import com.ditrasystems.comspringboot.Fournisseur.FournisseurRepository;
import com.ditrasystems.comspringboot.Marge.Marge;
import com.ditrasystems.comspringboot.Utils.ErrorResponseModel;
import com.ditrasystems.comspringboot.Utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleServices {


  @Autowired
  ArticleRepository articleRepository;

  @Autowired
  FournisseurRepository fournisseurRepository;

  @Autowired
  FamilleRepository familleRepository;

  @Autowired
  ConstructionRepository constructionRepository;

  public ResponseEntity<?> create(ArticleModel articleModel) {

    Article article= articleModel.getArticle();


    if (article.getCode()==null) {
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),608,"Article requis un code ");
      return new ResponseEntity<>(errorResponseModel, HttpStatus.BAD_REQUEST);

    }

    Optional<Article> articleTest = articleRepository.findByCode(article.getCode());

    if (!articleTest.isPresent()) {
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),636,"article deja existé ");
      return new ResponseEntity<>(errorResponseModel, HttpStatus.BAD_REQUEST);

    }

    if (article.getDesignation()==null) {
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),607,"Article requis une designation ");
      return new ResponseEntity<>(errorResponseModel, HttpStatus.BAD_REQUEST);

    }

    if (article.getType()==null) {
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),610,"Article requis une type ");
      return new ResponseEntity<>(errorResponseModel, HttpStatus.BAD_REQUEST);

    }

    if (article.getFournisseur().getCode() ==null) {
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),637,"Article requis un fournisseur");
      return new ResponseEntity<>(errorResponseModel, HttpStatus.BAD_REQUEST);

    }

    if (article.getFamille() ==null) {
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),638,"Article requis une famille");
      return new ResponseEntity<>(errorResponseModel, HttpStatus.BAD_REQUEST);

    }

    if ((Float) article.getPAchatHT() ==null) {
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),639,"Article requis un PAchatHT");
      return new ResponseEntity<>(errorResponseModel, HttpStatus.BAD_REQUEST);

    }

    if (article.getType().equals("PF")){

      if (articleModel.getMatierePremierQuantities().size() == 0){
        ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),612,"Produit Fini requis au min une matiere premiere");
        return new ResponseEntity<>(errorResponseModel, HttpStatus.BAD_REQUEST);
      }
      //article = articleRepository.save(article);


      for (MatierePremierQuantity matierePremierQuantity : articleModel.getMatierePremierQuantities()){

        Optional<Article> MP = articleRepository.findByCode(matierePremierQuantity.getCode());

        if (MP == null){
          ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),609,"Article n'existe pas");
          return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
        }

        Construction construction = new Construction();
        construction.setQuantite(matierePremierQuantity.getQuantity());
        construction.setMatierePrimaire(MP.get());
        construction.setProduitFini(article);
        article.addConstruction(construction);
      }

    }

    for (Marge marge : articleModel.getMarges()){
      article.addMarge(marge);
      marge.setArticle(article);
    }

    article = articleRepository.save(article);

    return new ResponseEntity<>(articleModel,HttpStatus.OK);
  }

  public ResponseEntity<?> getAll(String type) {

    ArrayList<Article> articles = new ArrayList<>();
    ArrayList<ArticleModelGetAll> articleModelGetAlls = new ArrayList<>();

    if (type!= null){
      articles = articleRepository.findByType(type);
    }else {
      articles = (ArrayList<Article>) articleRepository.findAll();
    }

    for (Article article : articles) {

      Float PAchatTTC = article.getPAchatHT() + ((article.getPAchatHT() * article.getTva()) / 100) + ((article.getPAchatHT() * article.getFodec()) / 100);
      ArticleModelGetAll articleModelGetAll = new ArticleModelGetAll(article.getCode(), article.getDesignation(), article.getStock(), article.getType(), PAchatTTC, article.getFamille(),article.getFournisseur().getCode(),article.getFournisseur().getNom());
      articleModelGetAlls.add(articleModelGetAll);
    }

    return new ResponseEntity<>(articleModelGetAlls,HttpStatus.OK);
  }

  public ResponseEntity<?> getByCode(String code) {
    Optional<Article> article = articleRepository.findByCode(code);

    if (!article.isPresent()){
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),609,"Article n'existe pas");
      return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
    }

    ArticleModelGetOne articleModelGetOne = new ArticleModelGetOne();
    articleModelGetOne.setArticle(article.get());
    articleModelGetOne.setMarges(article.get().getMarges());
    articleModelGetOne.setConstructions(article.get().getConstructions());

    return new ResponseEntity<>(articleModelGetOne,HttpStatus.OK);
  }

  public ResponseEntity<?> editByCode(String codeArticle,Article article) {

    Optional<Article> articleOptional = articleRepository.findByCode(codeArticle);
    Article articleLocal = articleOptional.get();

    if (articleOptional == null){
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),609,"Article n'existe pas");
      return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
    }



    if (article.getFournisseur().getCode() != null){
      Optional<Fournisseur> fournisseur = fournisseurRepository.findByCode(article.getFournisseur().getCode());

      if (!fournisseur.isPresent()){
        ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),605,"Fournisseur n'existe pas");
        return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
      }

    }

    if (article.getFamille().getId() != null){
      Optional<Famille> famille = familleRepository.findById(article.getFamille().getId());

      if (!famille.isPresent()){
        ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),606,"Famille n'existe pas");
        return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
      }
    }


    if (article.getCode() != null){
      Optional<Article> articleTest = articleRepository.findByCode(article.getCode());

      if (articleTest != null && articleTest.get().getCode() != codeArticle) {
        ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),636,"article deja existé ");
        return new ResponseEntity<>(errorResponseModel, HttpStatus.BAD_REQUEST);

      }
    }

    article = Utils.merge(articleLocal,article);

    articleRepository.save(article);

    return new  ResponseEntity<>(HttpStatus.OK);
  }

  public ResponseEntity<?> deleteByCode(String code) {

    Optional<Article> article = articleRepository.findByCode(code);

    if (!article.isPresent()){
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),609,"Article n'existe pas");
      return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
    }

    articleRepository.delete(article.get());

    return new ResponseEntity<>(HttpStatus.OK);

  }
}
