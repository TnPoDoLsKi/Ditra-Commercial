package com.ditrasystems.comspringboot.DemandeOffre;

import com.ditrasystems.comspringboot.ArticleOffre.ArticleOffre;
import com.ditrasystems.comspringboot.ArticleOffre.ArticleOffreRepository;
import com.ditrasystems.comspringboot.Articles.Article;
import com.ditrasystems.comspringboot.Articles.ArticleRepository;
import com.ditrasystems.comspringboot.DemandeOffre.Models.ArticleOffreModel;
import com.ditrasystems.comspringboot.DemandeOffre.Models.ArticleQuantityModel;
import com.ditrasystems.comspringboot.DemandeOffre.Models.DemandeOffreModel;
import com.ditrasystems.comspringboot.DemandeOffre.Models.GetByIdModel;
import com.ditrasystems.comspringboot.Fournisseur.Fournisseur;
import com.ditrasystems.comspringboot.Fournisseur.FournisseurRepository;
import com.ditrasystems.comspringboot.Utils.ErrorResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DemandeOffreServices {
  @Autowired
  FournisseurRepository fournisseurRepository;

  @Autowired
  ArticleRepository articleRepository;

  @Autowired
  DemandeOffreRepository demandeOffreRepository;

  @Autowired
  ArticleOffreRepository articleOffreRepository;


  public ResponseEntity<?> create(DemandeOffreModel demandeOffreModel) {
    Optional<Fournisseur> fournisseur = fournisseurRepository.findFournisseurByCode(demandeOffreModel.getCodeFournisseur());

    if (!fournisseur.isPresent()){
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),605,"Fournisseur n'existe pas");
      return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
    }

    List<ArticleOffre> articleOffres = new ArrayList<>();

    for (ArticleQuantityModel articleQuantityModel :demandeOffreModel.getArticlesQuantity()) {

      Article article = articleRepository.findByCode(articleQuantityModel.getCodeArticle());

      ArticleOffre articleOffre = new ArticleOffre();

      if(article == null){

        articleOffre.setArticle(null);
        articleOffre.setDesignation(articleQuantityModel.getDesignation());
        articleOffre.setQuantiteStock(0);

      }else {

        if (article.getFournisseur().getCode() != demandeOffreModel.getCodeFournisseur()){
          ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(), 616, "Cet article n'appartient pas a ce fournisseur");
          return new ResponseEntity<>(errorResponseModel, HttpStatus.BAD_REQUEST);
        }

        articleOffre.setArticle(article);
        articleOffre.setDesignation(article.getDesignation());
        articleOffre.setQuantiteStock(article.getStock());

      }

      articleOffre.setQuantiteDemander(articleQuantityModel.getQuantiteDemander());

      articleOffres.add(articleOffre);
    }

    DemandeOffre demandeOffre = new DemandeOffre();

    demandeOffre.setFournisseur(fournisseur.get());

    demandeOffre.setCode(demandeOffreModel.getCode());

    demandeOffre = demandeOffreRepository.save(demandeOffre);

    for (ArticleOffre articleOffre :articleOffres){

      articleOffre.setDemandeOffre(demandeOffre);
      articleOffreRepository.save(articleOffre);
    }


    return new ResponseEntity<>(HttpStatus.OK);
  }

  public ResponseEntity<?> edit(Long id, String code, Date date, String fournisseurCode) {

    Optional<DemandeOffre> demandeOffre = demandeOffreRepository.findById(id);

    if (!demandeOffre.isPresent()){
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),617,"Demande d'offre n'existe pas");
      return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
    }

    Optional<Fournisseur> fournisseur = fournisseurRepository.findFournisseurByCode(fournisseurCode);

    if (fournisseurCode != null) {
      if (!fournisseur.isPresent()) {
        ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(), 605, "Fournisseur n'existe pas");
        return new ResponseEntity<>(errorResponseModel, HttpStatus.BAD_REQUEST);
      }

      for (ArticleOffre articleOffre : demandeOffre.get().getArticleOffres()) {
        if (articleOffre.getArticle() != null) {
          if (articleOffre.getArticle().getFournisseur().getCode() != fournisseurCode) {
            ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(), 642, "il y'a un Article qui n'appartient pas a ce fournisseur");
            return new ResponseEntity<>(errorResponseModel, HttpStatus.BAD_REQUEST);

          }
        }
      }
      demandeOffre.get().setFournisseur(fournisseur.get());
    }

    if (code != null){
      demandeOffre.get().setCode(code);
    }

    if (date != null){
      demandeOffre.get().setDate(date);
    }

    demandeOffreRepository.save(demandeOffre.get());
    return new ResponseEntity<>(HttpStatus.OK);
  }

  public ResponseEntity<?> delete(Long id) {
    Optional<DemandeOffre> demandeOffre = demandeOffreRepository.findById(id);

    if (!demandeOffre.isPresent()) {
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(), 617, "Demande d'offre n'existe pas");
      return new ResponseEntity<>(errorResponseModel, HttpStatus.BAD_REQUEST);
    }

    demandeOffreRepository.delete(demandeOffre.get());

    return new ResponseEntity<>(HttpStatus.OK);
  }

  public ResponseEntity<?> getAll() {
    return new ResponseEntity<>(demandeOffreRepository.findAll(),HttpStatus.OK);

  }

  public ResponseEntity<?> getById(Long id) {
    //{ id, code, fournisseur, date, articles : [{ codeArticle, designation, quantiteStock, quantiteDemander }] }
    Optional<DemandeOffre> demandeOffre = demandeOffreRepository.findById(id);

    if (!demandeOffre.isPresent()) {
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(), 617, "Demande d'offre n'existe pas");
      return new ResponseEntity<>(errorResponseModel, HttpStatus.BAD_REQUEST);

    }

    GetByIdModel getByIdModel = new GetByIdModel();
    getByIdModel.setDemandeOffre(demandeOffre.get());

    ArticleOffreModel articleOffreModel = new ArticleOffreModel();
    ArrayList<ArticleOffreModel> articleOffreModels = new ArrayList<>();

    for(ArticleOffre articleOffre : demandeOffre.get().getArticleOffres()){

      if(articleOffre.getArticle() != null) {
        articleOffreModel.setCodeArticle(articleOffre.getArticle().getCode());
      }else{
          articleOffreModel.setCodeArticle(null);
      }
      articleOffreModel.setDesignation(articleOffre.getDesignation());
      articleOffreModel.setQuantiteDemander(articleOffre.getQuantiteDemander());
      articleOffreModel.setQuantiteStock(articleOffre.getQuantiteStock());

      articleOffreModels.add(articleOffreModel);

    }
    getByIdModel.setArticleOffreModels(articleOffreModels);


    return new ResponseEntity<>(getByIdModel,HttpStatus.OK);
  }

  public ResponseEntity<?> deleteArticle(long idArticleOffre) {

    Optional<ArticleOffre> articleOffre = articleOffreRepository.findById(idArticleOffre);

    if (!articleOffre.isPresent()) {
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(), 643, "Article Offre n'existe pas");
      return new ResponseEntity<>(errorResponseModel, HttpStatus.BAD_REQUEST);
    }
    articleOffreRepository.delete(articleOffre.get());
    return new ResponseEntity<>(HttpStatus.OK);

  }

  public ResponseEntity<?> addArticle(long code, String codeArticle, String designation, float quantiteDemander) {

    Optional<DemandeOffre> demandeOffre = demandeOffreRepository.findById(code);

    if (!demandeOffre.isPresent()){
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),617,"Demande d'offre n'existe pas");
      return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
    }


    ArticleOffre articleOffre = new ArticleOffre();

    if(codeArticle != null){

      Article article = articleRepository.findByCode(codeArticle);

      if (article == null){
        ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),609,"Article n'existe pas");
        return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
      }

      ArticleOffre articleAndOffre = articleOffreRepository.findArticleOffreByArticleAndDemandeOffre(article,demandeOffre.get());

      if (articleAndOffre == null){
        ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),643,"Article Offre n'existe pas");
        return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
      }

      if (article.getFournisseur().getCode() != demandeOffre.get().getFournisseur().getCode()) {
        ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(), 616, "Cet article n'appartient pas a ce fournisseur");
        return new ResponseEntity<>(errorResponseModel, HttpStatus.BAD_REQUEST);
      }

      articleOffre.setArticle(article);
      articleOffre.setDesignation(designation);
      articleOffre.setQuantiteStock(article.getStock());



    }else {
      articleOffre.setArticle(null);
      articleOffre.setDesignation(designation);
      articleOffre.setQuantiteStock(0);

    }
    articleOffre.setQuantiteDemander(quantiteDemander);
    articleOffreRepository.save(articleOffre);

    return new ResponseEntity<>(articleOffre,HttpStatus.OK);
  }



}
