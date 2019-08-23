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

    if (article.getDesignation()==null) {
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),607,"Article requis une designation ");
      return new ResponseEntity<>(errorResponseModel, HttpStatus.BAD_REQUEST);

    }

    if (article.getCode()==null) {
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),608,"Article requis un code ");
      return new ResponseEntity<>(errorResponseModel, HttpStatus.BAD_REQUEST);

    }

    if (article.getType()==null) {
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),610,"Article requis une type ");
      return new ResponseEntity<>(errorResponseModel, HttpStatus.BAD_REQUEST);

    }

    if (article.getType().equals("PF")){

      if (articleModel.getMatierePremierQuantities().size() == 0){
        ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),612,"Produit Fini requis au min une matiere premiere");
        return new ResponseEntity<>(errorResponseModel, HttpStatus.BAD_REQUEST);
      }
      //article = articleRepository.save(article);


      for (MatierePremierQuantity matierePremierQuantity : articleModel.getMatierePremierQuantities()){

        Optional<Article> MP = articleRepository.findById(matierePremierQuantity.getId());

        if (!MP.isPresent()){
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

  public ResponseEntity<?> addMatierePremierForPFService(String code, String matieresPremiersCode,float quantity) {
    Article article = articleRepository.findByCode(code);

    if (article == null){
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),609,"Article n'existe pas");
      return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
    }

    if (!article.getType().equals("PF")){
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),611,"Article n'est pasPF");
      return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
    }


    Article articleMP = articleRepository.findByCode(matieresPremiersCode);

    if (articleMP == null){
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),609,"Article n'existe pas");
      return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
    }

    if (!articleMP.getType().equals("MP")){
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),612,"Article n'est pasPF");
      return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
    }

      Construction construction=new Construction();

      construction.setMatierePrimaire(articleMP);
      construction.setProduitFini(article);
      construction.setQuantite(quantity);

      constructionRepository.save(construction);
      return new ResponseEntity<>(HttpStatus.OK);
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

      Float PAchatTTC = article.getPAchatHT() + ((article.getPAchatHT() * article.getTva()) / 100);
      ArticleModelGetAll articleModelGetAll = new ArticleModelGetAll(article.getId(), article.getCode(), article.getDesignation(), article.getStock(), article.getType(), PAchatTTC, article.getFamille(),article.getFournisseur().getCode(),article.getFournisseur().getNom());
      articleModelGetAlls.add(articleModelGetAll);
    }

    return new ResponseEntity<>(articleModelGetAlls,HttpStatus.OK);
  }

  public ResponseEntity<?> getConstructions(long id) {
    Optional<Article> article = articleRepository.findById(id);

    if (!article.isPresent()){
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),609,"Article n'existe pas");
      return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
    }

    if (!article.get().getType().equals("PF")){
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),611,"L'Article n'est pas un PF");
      return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
    }



    return new ResponseEntity<>(article.get().getConstructions(),HttpStatus.OK);

  }

  public ResponseEntity<?> getByCode(String code) {
    Article article = articleRepository.findByCode(code);

    if (article == null){
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),609,"Article n'existe pas");
      return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
    }

    ArticleModelGetOne articleModelGetOne = new ArticleModelGetOne();
    articleModelGetOne.setArticle(article);
    articleModelGetOne.setMarges(article.getMarges());
    articleModelGetOne.setConstructions(article.getConstructions());

    return new ResponseEntity<>(articleModelGetOne,HttpStatus.OK);
  }

  public ResponseEntity<?> edit(String codeArticle,Long familleId,Long fournisseurId, String code, String designation, String type, String codeABarre, Float PAchatHT, Float remise, Float tva, Float fodec, Float stock, Float quantiteVendu, Float stockMin, Float prixVenteHTMin) {

    Article article = articleRepository.findByCode(codeArticle);

    if (article == null){
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),609,"Article n'existe pas");
      return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
    }



    if (fournisseurId != null){
      Optional<Fournisseur> fournisseur = fournisseurRepository.findById(fournisseurId);

      if (!fournisseur.isPresent()){
        ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),605,"Fournisseur n'existe pas");
        return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
      }

      article.setFournisseur(fournisseur.get());

    }

    if (familleId != null){
      Optional<Famille> famille = familleRepository.findById(familleId);

      if (!famille.isPresent()){
        ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),606,"Famille n'existe pas");
        return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
      }

      article.setFamille(famille.get());

    }


    if (code != null){
      article.setCode(code);
    }

    if (designation != null){
      article.setDesignation(designation);
    }

    if (type != null){
      article.setType(type);
    }

    if (codeABarre!= null){
      article.setCodeABarre(codeABarre);
    }


    if ( PAchatHT != null){
      article.setPAchatHT(PAchatHT);
    }

    if ( remise!= null){
      article.setRemise(remise);
    }

    if ( tva!= null){
      article.setTva(tva);
    }

    if (fodec != null){
      article.setFodec(fodec);
    }

    if ( stock != null){
      article.setStock(stock);
    }

    if (quantiteVendu!= null){
      article.setQuantiteVendu(quantiteVendu);
    }

    if (stockMin != null){
      article.setStockMin(stockMin);
    }

    if (prixVenteHTMin != null){
      article.setPrixVenteHTMin(prixVenteHTMin);
    }

    articleRepository.save(article);

    return new  ResponseEntity<>(HttpStatus.OK);
  }

  public ResponseEntity<?> delete(String code) {

    Article article = articleRepository.findByCode(code);

    if (article == null){
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),609,"Article n'existe pas");
      return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
    }

    articleRepository.delete(article);

    return new ResponseEntity<>(HttpStatus.OK);

  }
}
