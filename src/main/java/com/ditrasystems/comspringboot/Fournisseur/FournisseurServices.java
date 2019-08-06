package com.ditrasystems.comspringboot.Fournisseur;

import com.ditrasystems.comspringboot.Agenda.Agenda;
import com.ditrasystems.comspringboot.Fournisseur.Models.FournisseurModel;
import com.ditrasystems.comspringboot.Utils.ErrorResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FournisseurServices {

  @Autowired
  FournisseurRepository fournisseurRepository;

  public ResponseEntity<?> create(FournisseurModel fournisseurModel) {

    Fournisseur fournisseur=fournisseurModel.getFournisseur();


    if (fournisseur.getName()==null)
    {
        ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),600,"Fournisseur name requis");
        return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
    }

    if (fournisseur.getCode()==null)
    {
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),601,"Fournisseur code requis");
      return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
    }

    if (fournisseur.getAdresse()==null)
    {
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),602,"Fournisseur adresse requis");
      return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
    }

    if (fournisseurModel.getAgendaList().size() == 0)
    {
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),603,"ajouter une agneda au min");
      return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
    }

    if (fournisseur.getVille()==null)
    {
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),604,"Fournisseur ville requis");
      return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
    }

    for (Agenda agenda:fournisseurModel.getAgendaList()){
      agenda.setFournisseur(fournisseur);
      fournisseur.addAgenda(agenda);
    }

    fournisseurRepository.save(fournisseur);


    return new ResponseEntity<Fournisseur>(fournisseur, HttpStatus.CREATED);
  }

  public ResponseEntity<?> delete(long id) {
    Optional<Fournisseur> fournisseur1 = fournisseurRepository.findById(id);

    if (!fournisseur1.isPresent()){
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),605,"Fournisseur n'existe pas");
      return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
    }

    fournisseurRepository.delete(fournisseur1.get());

    return new ResponseEntity<>(HttpStatus.OK);

  }

  public ResponseEntity<?> getById(long id) {
    Optional<Fournisseur> fournisseur = fournisseurRepository.findById(id);

    if (!fournisseur.isPresent()){
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),605,"Fournisseur n'existe pas");
      return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
    }

    return new ResponseEntity<>(fournisseur.get(),HttpStatus.OK);

  }

  public ResponseEntity<?> getAll() {
    return new ResponseEntity<>(fournisseurRepository.findAll(),HttpStatus.OK);
  }

  public ResponseEntity<?> getByCode(String code) {

    Optional<Fournisseur> fournisseur = fournisseurRepository.findFournisseurByCode(code);

    if (!fournisseur.isPresent()){
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),605,"Fournisseur n'existe pas");
      return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
    }

    return new ResponseEntity<Fournisseur>(fournisseur.get(),HttpStatus.OK);
  }

  public ResponseEntity<?> edit(long id, String name, String code, String activite, String adresse, String codePostale, String ville, String pays, String codeTva, String matFiscale, Float solde, String email, String website) {

    Optional<Fournisseur> fournisseur = fournisseurRepository.findById(id);

    if (!fournisseur.isPresent()){
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),605,"Fournisseur n'existe pas");
      return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
    }

    if (name != null){
      fournisseur.get().setName(name);
    }

    if (code != null){
      fournisseur.get().setCode(code);
    }

    if (activite != null){
      fournisseur.get().setActivite(activite);
    }

    if (adresse != null){
      fournisseur.get().setAdresse(adresse);
    }

    if (codePostale != null){
      fournisseur.get().setCodePostal(codePostale);
    }

    if (ville != null){
      fournisseur.get().setVille(ville);
    }

    if (pays != null){
      fournisseur.get().setPays(pays);
    }

    if (codeTva != null){
      fournisseur.get().setCodeTVA(codeTva);
    }

    if (matFiscale != null){
      fournisseur.get().setMatFiscale(matFiscale);
    }

    if (solde != null){
      fournisseur.get().setSolde(solde);
    }
    if (email != null){
      fournisseur.get().setEmail(email);
    }

    if (website != null){
      fournisseur.get().setWebsite(website);
    }

    fournisseurRepository.save(fournisseur.get());

    return new ResponseEntity<>(HttpStatus.OK);
  }
}
