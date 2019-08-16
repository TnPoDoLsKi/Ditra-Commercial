package com.ditrasystems.comspringboot.BonDeCommande;

import com.ditrasystems.comspringboot.ArticleBonCommande.ArticleBonCommande;
import com.ditrasystems.comspringboot.ArticleBonCommande.ArticleBonCommandeRepository;
import com.ditrasystems.comspringboot.Articles.Article;
import com.ditrasystems.comspringboot.Articles.ArticleRepository;
import com.ditrasystems.comspringboot.BonDeCommande.Models.ArticleQuantityModel;
import com.ditrasystems.comspringboot.BonDeCommande.Models.BonDeCommandeModel;
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
public class BonDeCommandeServices {
  @Autowired
  FournisseurRepository fournisseurRepository;

  @Autowired
  ArticleRepository articleRepository;

  @Autowired
  BonDeCommandeRepository bonDeCommandeRepository;

  @Autowired
  ArticleBonCommandeRepository articleBonCommandeRepository;


  public ResponseEntity<?> create(BonDeCommandeModel bonDeCommandeModel) {
    Optional<Fournisseur> fournisseur = fournisseurRepository.findById(bonDeCommandeModel.getFournisseur());

    if (!fournisseur.isPresent()){
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),605,"Fournisseur n'existe pas");
      return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
    }



    List<ArticleBonCommande> articleBonCommandes = new ArrayList<>();

    for (ArticleQuantityModel articleQuantityModel :bonDeCommandeModel.getArticlesQuantity()) {

     Optional<Article> article = articleRepository.findById(articleQuantityModel.getarticle());

      if (!article.isPresent()) {
        ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(), 609, "Article n'existe pas");
        return new ResponseEntity<>(errorResponseModel, HttpStatus.BAD_REQUEST);
      }

      if (article.get().getFournisseur().getId() != bonDeCommandeModel.getFournisseur()){
        ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(), 616, "Cet article n'appartient pas a ce fournisseur");
        return new ResponseEntity<>(errorResponseModel, HttpStatus.BAD_REQUEST);
      }

      ArticleBonCommande articleBonCommande = new ArticleBonCommande();

      articleBonCommande.setArticle(article.get());
      articleBonCommande.setPrix(article.get().getPrixHT());
      articleBonCommande.setQuantite(articleQuantityModel.getQuantity());
      articleBonCommandes.add(articleBonCommande);
    }

    BonDeCommande bonDeCommande = new BonDeCommande();

    bonDeCommande.setFournisseur(fournisseur.get());

    bonDeCommande.setCode(bonDeCommandeModel.getCode());

    bonDeCommande = bonDeCommandeRepository.save(bonDeCommande);

    for (ArticleBonCommande articleBonCommandee :articleBonCommandes){
      articleBonCommandee.setBonDeCommande(bonDeCommande);
      articleBonCommandeRepository.save(articleBonCommandee);

    }


    return new ResponseEntity<>(HttpStatus.OK);
  }


  public ResponseEntity<?> edit(Long id, String code, Date date) {
    Optional<BonDeCommande> bonDeCommande = bonDeCommandeRepository.findById(id);

    if (!bonDeCommande.isPresent()){
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),617,"Bon de commande n'existe pas");
      return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
    }

    if (code != null){
      bonDeCommande.get().setCode(code);
    }

    if (date != null){
      bonDeCommande.get().setDate(date);
    }


    bonDeCommandeRepository.save(bonDeCommande.get());
    return new ResponseEntity<>(HttpStatus.OK);
  }

  public ResponseEntity<?> editArticle(Long id, List<ArticleQuantityModel> articleQuantityModels) {

    Optional<BonDeCommande> bonDeCommande = bonDeCommandeRepository.findById(id);

    if (!bonDeCommande.isPresent()){
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),618,"Bon de commande n'existe pas");
      return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
    }

    List<ArticleBonCommande> articleBonCommandes = new ArrayList<>();


    for (ArticleQuantityModel articleQuantityModel :articleQuantityModels) {

      Optional<Article> article = articleRepository.findById(articleQuantityModel.getarticle());

      if (!article.isPresent()) {
        ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(), 609, "Article n'existe pas");
        return new ResponseEntity<>(errorResponseModel, HttpStatus.BAD_REQUEST);
      }

      if (article.get().getFournisseur().getId() != bonDeCommande.get().getFournisseur().getId()){
        ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(), 616, "Cet article n'appartient pas a ce fournisseur");
        return new ResponseEntity<>(errorResponseModel, HttpStatus.BAD_REQUEST);
      }

      ArticleBonCommande articleBonCommande = new ArticleBonCommande();

      articleBonCommande.setArticle(article.get());
      articleBonCommande.setPrix(article.get().getPrixHT());
      articleBonCommande.setQuantite(articleQuantityModel.getQuantity());
      articleBonCommandes.add(articleBonCommande);
    }

    for (ArticleBonCommande articleBonCommande :articleBonCommandes){
      articleBonCommande.setBonDeCommande(bonDeCommande.get());
      articleBonCommandeRepository.save(articleBonCommande);
    }

    return new ResponseEntity<>(HttpStatus.OK);
  }

  public ResponseEntity<?> deleteArticle(Long id, List<Article> articles) {
    Optional<BonDeCommande> bonDeCommande = bonDeCommandeRepository.findById(id);

    if (!bonDeCommande.isPresent()) {
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(), 618, "Bon de commande n'existe pas");
      return new ResponseEntity<>(errorResponseModel, HttpStatus.BAD_REQUEST);
    }

    List<ArticleBonCommande> articleBonCommandes = new ArrayList<>();

    if (articles != null) {
      for (Article article : articles) {

        Optional<Article> article1 = articleRepository.findById(article.getId());

        if (!article1.isPresent()) {
          ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(), 609, "Article n'existe pas");
          return new ResponseEntity<>(errorResponseModel, HttpStatus.BAD_REQUEST);
        }

        Optional<ArticleBonCommande> articleBonCommande = articleBonCommandeRepository.findArticleBonCommandeByArticleAndBonDeCommande(article1.get(),bonDeCommande.get());

        if (!articleBonCommande.isPresent()) {
          ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(), 619, "cette Bon de commande ne contient pas ce article");
          return new ResponseEntity<>(errorResponseModel, HttpStatus.BAD_REQUEST);
        }

        articleBonCommandes.add(articleBonCommande.get());
      }


      for (ArticleBonCommande articleBonCommande : articleBonCommandes) {
        articleBonCommandeRepository.delete(articleBonCommande);
      }
    }

    return new ResponseEntity<>(HttpStatus.OK);

  }

  public ResponseEntity<?> delete(Long id) {
    Optional<BonDeCommande> bonDeCommande = bonDeCommandeRepository.findById(id);

    if (!bonDeCommande.isPresent()) {
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(), 617, "Bon de commande n'existe pas");
      return new ResponseEntity<>(errorResponseModel, HttpStatus.BAD_REQUEST);
    }

    bonDeCommandeRepository.delete(bonDeCommande.get());

    return new ResponseEntity<>(HttpStatus.OK);
  }

  public ResponseEntity<?> getAll() {
    return new ResponseEntity<>(bonDeCommandeRepository.findAll(),HttpStatus.OK);
  }

  public ResponseEntity<?> getById(Long id) {

    Optional<BonDeCommande> bonDeCommande = bonDeCommandeRepository.findById(id);

    if (!bonDeCommande.isPresent()) {
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(), 617, "Bon de commande n'existe pas");
      return new ResponseEntity<>(errorResponseModel, HttpStatus.BAD_REQUEST);
    }


    return new ResponseEntity<>(bonDeCommande.get(),HttpStatus.OK);
  }
}
