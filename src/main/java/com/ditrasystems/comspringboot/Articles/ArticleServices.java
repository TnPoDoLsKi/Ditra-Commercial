package com.ditrasystems.comspringboot.Articles;

import com.ditrasystems.comspringboot.Construction.Construction;
import com.ditrasystems.comspringboot.Construction.ConstructionRepository;
import com.ditrasystems.comspringboot.Famille.Famille;
import com.ditrasystems.comspringboot.Famille.FamilleRepository;
import com.ditrasystems.comspringboot.Fournisseur.Fournisseur;
import com.ditrasystems.comspringboot.Fournisseur.FournisseurRepository;
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

  public ResponseEntity<?> create(Article article) {
    if (article.getDesignation()==null) {
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),607,"Article designation required");
      return new ResponseEntity<>(errorResponseModel, HttpStatus.BAD_REQUEST);

    }

    if (article.getCode()==null) {
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),608,"Article code required");
      return new ResponseEntity<>(errorResponseModel, HttpStatus.BAD_REQUEST);

    }

    if (article.getType()==null) {
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),610,"Article type required");
      return new ResponseEntity<>(errorResponseModel, HttpStatus.BAD_REQUEST);

    }

    article = articleRepository.save(article);

    return new ResponseEntity<>(article,HttpStatus.OK);
  }

  public ResponseEntity<?> edit(long id,Long familleId,Long fournisseurId, String code, String designation, String type, String codeABarre, Float prixHT, Float remise, Float tva, Float fodec, Float stock, Float quantiteVendu, Float stockMin, Float prixVenteHTMin) {

    Optional<Article> article = articleRepository.findById(id);

    if (!article.isPresent()){
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),609,"Article dosen't exist");
      return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
    }



    if (fournisseurId != null){
      Optional<Fournisseur> fornisseur = fournisseurRepository.findById(id);

      if (!fornisseur.isPresent()){
        ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),605,"Fournisseur dosen't exist");
        return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
      }

      article.get().setFournisseur(fornisseur.get());

    }

    if (familleId != null){
      Optional<Famille> famille = familleRepository.findById(id);

      if (!famille.isPresent()){
        ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),606,"Famille dosen't exist");
        return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
      }

      article.get().setFamille(famille.get());

    }


    if (code != null){
      article.get().setCode(code);
    }

    if (designation != null){
      article.get().setDesignation(designation);
    }

    if (type != null){
      article.get().setType(type);
    }

    if (codeABarre!= null){
      article.get().setCodeABarre(codeABarre);
    }


    if ( prixHT != null){
      article.get().setPrixHT(prixHT);
    }

    if ( remise!= null){
      article.get().setRemise(remise);
    }

    if ( tva!= null){
      article.get().setTva(tva);
    }

    if (fodec != null){
      article.get().setFodec(fodec);
    }

    if ( stock != null){
      article.get().setStock(stock);
    }

    if (quantiteVendu!= null){
      article.get().setQuantiteVendu(quantiteVendu);
    }

    if (stockMin != null){
      article.get().setStockMin(stockMin);
    }

    if (prixVenteHTMin != null){
      article.get().setPrixVenteHTMin(prixVenteHTMin);
    }

    articleRepository.save(article.get());

    return new  ResponseEntity<>(HttpStatus.OK);
  }

  public ResponseEntity<?> delete(long id) {

    Optional<Article> article = articleRepository.findById(id);

    if (!article.isPresent()){
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),609,"Article dosen't exist");
      return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
    }

    articleRepository.delete(article.get());

    return new ResponseEntity<>(HttpStatus.OK);

  }

  public ResponseEntity<?> addMatierePremier(long id, Long matieresPremiers,float quantity) {
    Optional<Article> article = articleRepository.findById(id);

    if (!article.isPresent()){
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),609,"Article dosen't exist");
      return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
    }

    if (!article.get().getType().equals("PF")){
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),611,"Article isn't a PF");
      return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
    }


    Optional<Article> articleMP = articleRepository.findById(matieresPremiers);

    if (!articleMP.isPresent()){
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),609,"Article dosen't exist");
      return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
    }

    if (!articleMP.get().getType().equals("MP")){
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),612,"Article isn't a MP");
      return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
    }

      Construction construction=new Construction();

      construction.setMatierePrimaire(articleMP.get());
      construction.setProduitFini(article.get());
      construction.setQuantite(quantity);

      constructionRepository.save(construction);
      return new ResponseEntity<>(HttpStatus.OK);
    }

  public ResponseEntity<?> getAll(String type) {

    if (type!= null){
      return new ResponseEntity<>(articleRepository.findByType(type),HttpStatus.OK);
    }

    return new ResponseEntity<>(articleRepository.findAll(),HttpStatus.OK);
  }

  public ResponseEntity<?> getMatierePremierOfProduitFini(long id) {
    Optional<Article> article = articleRepository.findById(id);

    if (!article.isPresent()){
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),609,"Article dosen't exist");
      return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
    }

    if (!article.get().getType().equals("PF")){
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),611,"Article isn't a PF");
      return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
    }



    return new ResponseEntity<>(article.get().getConstructions(),HttpStatus.OK);

  }
}
