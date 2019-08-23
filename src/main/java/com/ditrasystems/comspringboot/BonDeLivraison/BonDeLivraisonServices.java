package com.ditrasystems.comspringboot.BonDeLivraison;


import com.ditrasystems.comspringboot.ArticleBonCommande.ArticleBonCommande;
import com.ditrasystems.comspringboot.ArticleBonCommande.ArticleBonCommandeRepository;
import com.ditrasystems.comspringboot.ArticleBonLivraison.ArticleBonLivraison;
import com.ditrasystems.comspringboot.ArticleBonLivraison.ArticleBonLivraisonRepository;
import com.ditrasystems.comspringboot.Articles.Article;
import com.ditrasystems.comspringboot.Articles.ArticleRepository;
import com.ditrasystems.comspringboot.BonDeCommande.BonDeCommande;
import com.ditrasystems.comspringboot.BonDeCommande.BonDeCommandeRepository;
import com.ditrasystems.comspringboot.BonDeLivraison.Models.ArticleQuantityModel;
import com.ditrasystems.comspringboot.BonDeLivraison.Models.BonDeLivraisonModel;
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
public class BonDeLivraisonServices {

  @Autowired
  BonDeLivraisonRepository bonDeLivraisonRepository;

  @Autowired
  BonDeCommandeRepository bonDeCommandeRepository;

  @Autowired
  ArticleBonCommandeRepository articleBonCommandeRepository;

  @Autowired
  ArticleBonLivraisonRepository articleBonLivraisonRepository;

  @Autowired
  FournisseurRepository fournisseurRepository;

  @Autowired
  ArticleRepository articleRepository;



  public ResponseEntity<?> create(BonDeLivraisonModel bonDeLivraisonModel) {
    BonDeLivrasion bonDeLivrasion = new BonDeLivrasion();

    Optional<Fournisseur> fournisseur = fournisseurRepository.findFournisseurByCode(bonDeLivraisonModel.getCodeFournisseur());

    if (!fournisseur.isPresent()){
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),605,"Fournisseur n'existe pas");
      return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
    }


    bonDeLivrasion.setFournisseur(fournisseur.get());

    for (BonDeCommande bonDeCommandeId : bonDeLivraisonModel.getBonDeCommandes()) {
      Optional<BonDeCommande> bonDeCommande = bonDeCommandeRepository.findById(bonDeCommandeId.getId());

      if (!bonDeCommande.isPresent()){
        ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),617,"Bon de commande n'existe pas");
        return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
      }

      bonDeLivrasion.addBonDeCommande(bonDeCommande.get());
    }



    List<ArticleBonLivraison> articleBonLivraisons = new ArrayList<>();

    for (ArticleQuantityModel articleQuantityModel : bonDeLivraisonModel.getArticlesQuantity()){

      Optional<Article> article = articleRepository.findById(articleQuantityModel.getArticle());


      if (!article.isPresent()) {
        ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(), 609, "Article n'existe pas");
        return new ResponseEntity<>(errorResponseModel, HttpStatus.BAD_REQUEST);
      }

      if (article.get().getFournisseur().getCode() != bonDeLivraisonModel.getCodeFournisseur()){
        ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(), 616, "Cet article n'appartient pas a ce fournisseur");
        return new ResponseEntity<>(errorResponseModel, HttpStatus.BAD_REQUEST);
      }

      Optional<BonDeCommande> bonDeCommande = Optional.empty();

      if (articleQuantityModel.getBonDeCommande() != null){

       bonDeCommande = bonDeCommandeRepository.findById(articleQuantityModel.getBonDeCommande());

        if (!bonDeCommande.isPresent()){
          ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),617,"Bon de commande n'existe pas");
          return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
        }

        Optional<ArticleBonCommande> articleBonCommande = articleBonCommandeRepository.findArticleBonCommandeByArticleAndBonDeCommande(article.get(),bonDeCommande.get());

        if (!articleBonCommande.isPresent()){
          ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),620,"il y a aucune bon commande qui a cette article");
          return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
        }


        for (BonDeCommande bonDeCommande1 : bonDeLivraisonModel.getBonDeCommandes()){
          if (bonDeCommande1.getId() != articleQuantityModel.getBonDeCommande() ){
            ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),619,"il y a un article que sa bon de commande nappartient pas au bon de livraison");
            return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
          }
        }

        if (articleBonCommande.get().getQuantiteCommander() - articleBonCommande.get().getQuantiteLivrer() < articleQuantityModel.getQuantity()){
          ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),621,"la quantite livrer est plus grand que la quantite a livrer ");
          return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
        }

        articleBonCommande.get().setQuantiteLivrer(articleBonCommande.get().getQuantiteLivrer() + articleQuantityModel.getQuantity());

        articleBonCommandeRepository.save(articleBonCommande.get());
      }


       ArticleBonLivraison articleBonLivraison = new ArticleBonLivraison();

       articleBonLivraison.setArticle(article.get());

       articleBonLivraison.setPrix(articleQuantityModel.getPrix());

       articleBonLivraison.setQuantite(articleQuantityModel.getQuantity());

      if (bonDeCommande.isPresent()) {
        articleBonLivraison.setBonDeCommande(bonDeCommande.get().getId());
      }
      else {
        articleBonLivraison.setBonDeCommande(null);
      }

      articleBonLivraisons.add(articleBonLivraison);

      article.get().setPAchatHT(articleQuantityModel.getPrix());
      article.get().setStock(article.get().getStock()+articleQuantityModel.getQuantity());

      articleRepository.save(article.get());
    }

    bonDeLivrasion.setCode(bonDeLivraisonModel.getCode());

    bonDeLivrasion =bonDeLivraisonRepository.save(bonDeLivrasion);


    for (ArticleBonLivraison articleBonLivraison :articleBonLivraisons){
      articleBonLivraison.setBonDeLivrasion(bonDeLivrasion);
      articleBonLivraisonRepository.save(articleBonLivraison);
    }


    return new ResponseEntity<>(HttpStatus.OK);
  }

  public ResponseEntity<?> edit(Long id, String code, Date date) {
    Optional<BonDeLivrasion> bonDeLivrasion = bonDeLivraisonRepository.findById(id);

    if (!bonDeLivrasion.isPresent()){
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),622,"Bon de livraison n'existe pas");
      return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
    }

    if (code != null){
      bonDeLivrasion.get().setCode(code);
    }

    if (date != null){
      bonDeLivrasion.get().setDate(date);
    }


    return new ResponseEntity<>(bonDeLivraisonRepository.save(bonDeLivrasion.get()),HttpStatus.OK);
  }


  public ResponseEntity<?> editArticle(Long id, List<ArticleBonLivraison> articleBonLivraisons) {

    Optional<BonDeLivrasion> bonDeLivrasion = bonDeLivraisonRepository.findById(id);

    if (!bonDeLivrasion.isPresent()){
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),622,"Bon de livraison n'existe pas");
      return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
    }

    for (ArticleBonLivraison articleBonLivraison : articleBonLivraisons){

      Optional<ArticleBonLivraison> articleBonLivraison1 = articleBonLivraisonRepository.findById(articleBonLivraison.getId());


      if (!articleBonLivraison1.isPresent()) {
        ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(), 623, "la table intermediaire entre larticle et la bon de livraison n'existe pas");
        return new ResponseEntity<>(errorResponseModel, HttpStatus.BAD_REQUEST);
      }

      if (articleBonLivraison.getPrix() != null){
        articleBonLivraison1.get().setPrix(articleBonLivraison.getPrix());
      }

      if (articleBonLivraison.getQuantite() != null){

        if (articleBonLivraison1.get().getBonDeCommande() != null){
          Optional<BonDeCommande> bonDeCommande = bonDeCommandeRepository.findById(articleBonLivraison1.get().getBonDeCommande());

          Optional<ArticleBonCommande>articleBonCommande = articleBonCommandeRepository.findArticleBonCommandeByArticleAndBonDeCommande(articleBonLivraison1.get().getArticle(),bonDeCommande.get());

          articleBonCommande.get().setQuantiteLivrer(articleBonCommande.get().getQuantiteLivrer() - articleBonLivraison.getQuantite() + articleBonLivraison1.get().getQuantite());


          if (articleBonCommande.get().getQuantiteLivrer() > articleBonCommande.get().getQuantiteCommander()){
            ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),621,"la quantite livrer est plus grand que la quantite a livrer ");
            return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
          }
           articleBonCommandeRepository.save(articleBonCommande.get());
        }

        Float stock = articleBonLivraison1.get().getArticle().getStock();
        stock = stock - articleBonLivraison1.get().getQuantite() + articleBonLivraison.getQuantite();
        articleBonLivraison1.get().getArticle().setStock(stock);
      }

      articleBonLivraisonRepository.save(articleBonLivraison1.get());
    }

    return new ResponseEntity<>(HttpStatus.OK);
  }

  public ResponseEntity<?> deleteArticle(Long id, List<ArticleBonLivraison> articleBonLivraisons) {

    Optional<BonDeLivrasion> bonDeLivrasion = bonDeLivraisonRepository.findById(id);

    if (!bonDeLivrasion.isPresent()){
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),622,"Bon de livraison n'existe pas");
      return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
    }

    for (ArticleBonLivraison articleBonLivraison : articleBonLivraisons){

      Optional<ArticleBonLivraison> articleBonLivraison1 = articleBonLivraisonRepository.findById(articleBonLivraison.getId());


      if (!articleBonLivraison1.isPresent()) {
        ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(), 623, "la table intermediaire entre larticle et la bon de livraison n'existe pas");
        return new ResponseEntity<>(errorResponseModel, HttpStatus.BAD_REQUEST);
      }



        if (articleBonLivraison1.get().getBonDeCommande() != null){
          Optional<BonDeCommande> bonDeCommande = bonDeCommandeRepository.findById(articleBonLivraison1.get().getBonDeCommande());

          Optional<ArticleBonCommande> articleBonCommande = articleBonCommandeRepository.findArticleBonCommandeByArticleAndBonDeCommande(articleBonLivraison1.get().getArticle(),bonDeCommande.get());

          System.out.println(articleBonCommande.get().getId());

          articleBonCommande.get().setQuantiteLivrer(articleBonCommande.get().getQuantiteLivrer() - articleBonLivraison1.get().getQuantite());

          articleBonCommandeRepository.save(articleBonCommande.get());

        }
        Float stock = articleBonLivraison1.get().getArticle().getStock();

        articleBonLivraison1.get().getArticle().setStock(stock - articleBonLivraison1.get().getQuantite());


      articleBonLivraisonRepository.save(articleBonLivraison1.get());

      articleBonLivraisonRepository.delete(articleBonLivraison1.get());

    }

    return new ResponseEntity<>(HttpStatus.OK);
  }

  public ResponseEntity<?> delete(Long id) {

    Optional<BonDeLivrasion> bonDeLivrasion = bonDeLivraisonRepository.findById(id);

    if (!bonDeLivrasion.isPresent()){
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),622,"Bon de livraison n'existe pas");
      return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
    }

    deleteArticle(id, (List<ArticleBonLivraison>) bonDeLivrasion.get().getArticleBonLivraisons());

    bonDeLivraisonRepository.delete(bonDeLivrasion.get());
    return new ResponseEntity<>(HttpStatus.OK);
  }

  public ResponseEntity<?> getAll() {
    return new ResponseEntity<>(bonDeLivraisonRepository.findAll(),HttpStatus.OK);
  }

  public ResponseEntity<?> getById(Long id) {

    Optional<BonDeLivrasion> bonDeLivrasion = bonDeLivraisonRepository.findById(id);

    if (!bonDeLivrasion.isPresent()){
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),622,"Bon de livraison n'existe pas");
      return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<>(bonDeLivrasion.get(),HttpStatus.OK);

  }
}
