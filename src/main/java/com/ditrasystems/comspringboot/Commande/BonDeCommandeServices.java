package com.ditrasystems.comspringboot.Commande;

import com.ditrasystems.comspringboot.ArticleCommande.ArticleCommande;
import com.ditrasystems.comspringboot.ArticleCommande.ArticleBonCommandeRepository;
import com.ditrasystems.comspringboot.Articles.Article;
import com.ditrasystems.comspringboot.Articles.ArticleRepository;
import com.ditrasystems.comspringboot.Commande.Models.*;
import com.ditrasystems.comspringboot.Fournisseur.Fournisseur;
import com.ditrasystems.comspringboot.Fournisseur.FournisseurRepository;
import com.ditrasystems.comspringboot.Utils.ErrorResponseModel;
import com.ditrasystems.comspringboot.Utils.FournisseurModel;
import com.ditrasystems.comspringboot.Utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    float pAchatHT,fodec,tva,quantityCommander,quantityLivrer,quantityRestante,PAchatTTC;

    Optional<Commande> bonDeCommande = bonDeCommandeRepository.findByCode(bonDeCommandeModel.getCode());

    if (bonDeCommande.isPresent()) {
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(), 618, "commande deja exister");
      return new ResponseEntity<>(errorResponseModel, HttpStatus.BAD_REQUEST);
    }

    if (bonDeCommandeModel.getCodeFournisseur()==null)
    {
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),601,"Fournisseur code requis");
      return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
    }

    Optional<Fournisseur> fournisseur = fournisseurRepository.findByCode(bonDeCommandeModel.getCodeFournisseur());

    if (!fournisseur.isPresent()){
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),605,"Fournisseur n'existe pas");
      return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
    }

    if(bonDeCommandeModel.getDate() == null){
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),649,"Commande requis un date");
      return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
    }

    Date date = null;
    try {
      date = new SimpleDateFormat("dd/MM/yyyy").parse(bonDeCommandeModel.getDate());
    } catch (ParseException e) {
      e.printStackTrace();
    }


    List<ArticleCommande> articleCommandes = new ArrayList<>();

    for (ArticleQuantityModel articleQuantityModel :bonDeCommandeModel.getArticles()) {

      if (articleQuantityModel.getCodeArticle()==null)
      {
        ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),651,"Article code requis");
        return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
      }

      Optional<Article> articleOptional = articleRepository.findByCode(articleQuantityModel.getCodeArticle());
      Article article = articleOptional.get();


      if (!articleOptional.isPresent()) {
        ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(), 609, "Article n'existe pas");
        return new ResponseEntity<>(errorResponseModel, HttpStatus.BAD_REQUEST);
      }

      Optional<ArticleCommande> articleBonCommande = articleBonCommandeRepository.findArticleCommandeByArticleAndCommande(article,bonDeCommande.get());

      if (articleBonCommande.isPresent()){
        ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),652,"cette article est deja exister dans cette commande");
        return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
      }

      if (!article.getFournisseur().getCode().equals(bonDeCommandeModel.getCodeFournisseur()) ){
        ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(), 616, "Cet article n'appartient pas a ce fournisseur");
        return new ResponseEntity<>(errorResponseModel, HttpStatus.BAD_REQUEST);
      }

      if(articleQuantityModel.getQuantityCommander() == null){
        ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(), 647, "Commande requis quantité");
        return new ResponseEntity<>(errorResponseModel, HttpStatus.BAD_REQUEST);
      }

      if(articleQuantityModel.getQuantityLivrer()== null){
        ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(), 648, "Commande requis quantité Livrer");
        return new ResponseEntity<>(errorResponseModel, HttpStatus.BAD_REQUEST);
      }

      ArticleCommande articleCommande = new ArticleCommande();


      if (articleQuantityModel.getPAchatHT() == null) {
        pAchatHT = article.getPAchatHT();
      }else {
        pAchatHT = articleQuantityModel.getPAchatHT();
      }

      if (articleQuantityModel.getFodec() == null){
        fodec = article.getFodec();
      }else{
        fodec = articleQuantityModel.getFodec();
      }

      if (articleQuantityModel.getTva() == null){
        tva = article.getTva();
      }else {
        tva = articleQuantityModel.getTva();
      }

      PAchatTTC = pAchatHT + ((pAchatHT * tva) / 100) + ((pAchatHT * fodec) / 100);

      quantityCommander = articleQuantityModel.getQuantityCommander();
      quantityLivrer = articleQuantityModel.getQuantityLivrer();
      quantityRestante = quantityCommander - quantityLivrer;

      articleCommande.setArticle(article);
      articleCommande.setPAchatHT(pAchatHT);
      articleCommande.setPAchatTTC(PAchatTTC);
      articleCommande.setQuantiteCommander(quantityCommander);
      articleCommande.setQuantiteLivrer(quantityLivrer);
      articleCommande.setQuantiteStock(article.getStock());
      articleCommande.setQuantiteRestante(quantityRestante);
      articleCommande.setTva(tva);

      articleCommandes.add(articleCommande);
    }

    Commande commande = new Commande();

    commande.setFournisseur(fournisseur.get());

    commande.setCode(bonDeCommandeModel.getCode());

    commande.setDate(date);

    commande = bonDeCommandeRepository.save(commande);

    for (ArticleCommande articleCommandee : articleCommandes){

      articleCommandee.setCommande(commande);
      articleBonCommandeRepository.save(articleCommandee);
    }


    return new ResponseEntity<>(articleCommandes,HttpStatus.OK);
  }

  public ResponseEntity<?> addArticle(String code,ArticleQuantityModel articleQuantityModel) {

    float pAchatHT,fodec,tva,quantityCommander,quantityLivrer,quantityRestante,PAchatTTC;

    Optional<Commande> commande = bonDeCommandeRepository.findByCode(code);

    if (!commande.isPresent()){
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),618,"commande n'existe pas");
      return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
    }

    if (articleQuantityModel.getCodeArticle() == null)
    {
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),651,"Article code requis");
      return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
    }

    Optional<Article> articleOptional = articleRepository.findByCode(articleQuantityModel.getCodeArticle());
    Article article = articleOptional.get();

    if (!articleOptional.isPresent()) {
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(), 609, "Article n'existe pas");
      return new ResponseEntity<>(errorResponseModel, HttpStatus.BAD_REQUEST);
    }

    Optional<ArticleCommande> articleBonCommande = articleBonCommandeRepository.findArticleCommandeByArticleAndCommande(article,commande.get());

    if (articleBonCommande.isPresent()){
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),652,"cette article est deja exister dans cette commande");
      return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
    }

    if (article.getFournisseur().getCode() != commande.get().getFournisseur().getCode()){
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(), 616, "Cet article n'appartient pas a ce fournisseur");
      return new ResponseEntity<>(errorResponseModel, HttpStatus.BAD_REQUEST);
    }

    if(articleQuantityModel.getQuantityCommander() == null){
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(), 647, "Commande requis quantité");
      return new ResponseEntity<>(errorResponseModel, HttpStatus.BAD_REQUEST);
    }

    if(articleQuantityModel.getQuantityLivrer() == null){
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(), 648, "Commande requis quantité Livrer");
      return new ResponseEntity<>(errorResponseModel, HttpStatus.BAD_REQUEST);
    }

    ArticleCommande articleCommande = new ArticleCommande();

    if (articleQuantityModel.getPAchatHT() == null) {
      pAchatHT = article.getPAchatHT();
    }else {
      pAchatHT = articleQuantityModel.getPAchatHT();
    }

    if (articleQuantityModel.getFodec() == null){
      fodec = article.getFodec();
    }else{
      fodec = articleQuantityModel.getFodec();
    }

    if (articleQuantityModel.getTva() == null){
      tva = article.getTva();
    }else {
      tva = articleQuantityModel.getTva();
    }

    PAchatTTC = pAchatHT + ((pAchatHT * tva) / 100) + ((pAchatHT * fodec) / 100);

    quantityCommander = articleQuantityModel.getQuantityCommander();
    quantityLivrer = articleQuantityModel.getQuantityLivrer();
    quantityRestante = quantityCommander - quantityLivrer;

    articleCommande.setArticle(article);
    articleCommande.setPAchatHT(pAchatHT);
    articleCommande.setPAchatTTC(PAchatTTC);
    articleCommande.setQuantiteCommander(quantityCommander);
    articleCommande.setQuantiteLivrer(quantityLivrer);
    articleCommande.setQuantiteStock(article.getStock());
    articleCommande.setQuantiteRestante(quantityRestante);
    articleCommande.setTva(tva);

    articleCommande.setCommande(commande.get());

    articleBonCommandeRepository.save(articleCommande);

    return new ResponseEntity<>(articleCommande,HttpStatus.OK);

  }

  public ResponseEntity<?> getAll() {
    //[{ id, code, fournisseur, date, etat, totalHT, totalTTC }]
    float totalHT,totalTTC;

    ArrayList<Commande> commandes = (ArrayList<Commande>) bonDeCommandeRepository.findAll();
    ArrayList<CommandesModel> commandesModels = new ArrayList<>();
    for(Commande commande : commandes){
      FournisseurModel fournisseurModel = new FournisseurModel(commande.getFournisseur().getCode(), commande.getFournisseur().getNom(), commande.getFournisseur().getActivite());
      totalHT = 0 ;
      totalTTC = 0;
      for (ArticleCommande articleCommande : commande.getArticleCommandes()){
        totalHT = totalHT + articleCommande.getPAchatHT();
        totalTTC = totalTTC + articleCommande.getPAchatTTC();
      }

      CommandesModel commandesModel = new CommandesModel(commande.getCode(),fournisseurModel,commande.getDate(),commande.getEtat(),totalHT, totalTTC);
      commandesModels.add(commandesModel);
    }

    return new ResponseEntity<>(commandesModels,HttpStatus.OK);
  }

  public ResponseEntity<?> getByCode(String code) {
    float totalHT,totalTTC;


    Optional<Commande> commandeOptional = bonDeCommandeRepository.findByCode(code);

    if (!commandeOptional.isPresent()) {
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(), 618, "commande n'existe pas");
      return new ResponseEntity<>(errorResponseModel, HttpStatus.BAD_REQUEST);
    }
    ArrayList<CommandeArticleModel> commandeArticleModels = new ArrayList<>();

    Commande commande = commandeOptional.get();
    FournisseurModel fournisseurModel = new FournisseurModel(commande.getFournisseur().getCode(), commande.getFournisseur().getNom(), commande.getFournisseur().getActivite());

    totalHT = 0 ;
    totalTTC = 0;

    for (ArticleCommande articleCommande : commande.getArticleCommandes()){
      totalHT = totalHT + articleCommande.getPAchatHT();
      totalTTC = totalTTC + articleCommande.getPAchatTTC();
      CommandeArticleModel commandeArticleModel = new CommandeArticleModel(articleCommande.getArticle().getCode(),articleCommande.getArticle().getDesignation(),articleCommande.getQuantiteCommander(),articleCommande.getQuantiteStock(),articleCommande.getQuantiteLivrer(),articleCommande.getPAchatHT(),articleCommande.getPAchatTTC(),articleCommande.getArticle().getFodec(),articleCommande.getTva());
      commandeArticleModels.add(commandeArticleModel);
    }

    CommandesModel commandesModel = new CommandesModel(commande.getCode(),fournisseurModel,commande.getDate(),commande.getEtat(),totalHT, totalTTC);
    CommandeModel commandeModel = new CommandeModel(commandesModel,commandeArticleModels);

    return new ResponseEntity<>(commandeModel,HttpStatus.OK);
  }

  public ResponseEntity<?> editByCode(String code, Commande commande) {

    Optional<Commande> commandeLocal = bonDeCommandeRepository.findByCode(code);

    if (!commandeLocal.isPresent()){
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),618,"commande n'existe pas");
      return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
    }


    if (commande.getFournisseur()!= null) {
      Optional<Fournisseur> fournisseur = fournisseurRepository.findByCode(commande.getFournisseur().getCode());

      if (!fournisseur.isPresent()) {
        ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(), 605, "Fournisseur n'existe pas");
        return new ResponseEntity<>(errorResponseModel, HttpStatus.BAD_REQUEST);
      }

      commande.setFournisseur(fournisseur.get());
    }

      commande = Utils.merge(commandeLocal.get(),commande);

    bonDeCommandeRepository.save(commande);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  public ResponseEntity<?> deleteArticleByCode(String codeCommande, String codeArticle ) {

    Optional<Commande> commande = bonDeCommandeRepository.findByCode(codeCommande);

    if (!commande.isPresent()){
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),618,"commande n'existe pas");
      return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
    }

    Optional<Article> article = articleRepository.findByCode(codeArticle);

    if (!article.isPresent()) {
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(), 609, "Article n'existe pas");
      return new ResponseEntity<>(errorResponseModel, HttpStatus.BAD_REQUEST);
    }

    Optional<ArticleCommande> articleCommande = articleBonCommandeRepository.findArticleCommandeByArticleAndCommande(article.get(),commande.get());

    if (!articleCommande.isPresent()){
      System.out.println("---------");
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),620,"il y a aucune bon commande qui a cette article");
      return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
    }
    System.out.println("******");

    articleBonCommandeRepository.delete(articleCommande.get());


    return new ResponseEntity<>(HttpStatus.OK);

  }

  public ResponseEntity<?> deleteByCode(String code) {

    Optional<Commande> bonDeCommande = bonDeCommandeRepository.findByCode(code);

    if (!bonDeCommande.isPresent()) {
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(), 618, "commande n'existe pas");
      return new ResponseEntity<>(errorResponseModel, HttpStatus.BAD_REQUEST);
    }

    bonDeCommandeRepository.delete(bonDeCommande.get());

    return new ResponseEntity<>(HttpStatus.OK);
  }

}
