package com.ditrasystems.comspringboot.Fornisseur;

import com.ditrasystems.comspringboot.Utils.ErrorResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FornisseurServices {

  @Autowired
  FornisseurRepository fornisseurRepository;

  public ResponseEntity<?> create(Fornisseur fornisseur) {

    if (fornisseur.getName()==null)
    {
        ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),600,"Fornisseur name required");
        return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
    }

    if (fornisseur.getCode()==null)
    {
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),601,"Fornisseur code required");
      return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
    }

    if (fornisseur.getAdresse()==null)
    {
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),602,"Fornisseur adresse required");
      return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
    }

    if (fornisseur.getTel1()==null)
    {
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),603,"Fornisseur tel1 required");
      return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
    }

    if (fornisseur.getVille()==null)
    {
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),604,"Fornisseur ville required");
      return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
    }


    fornisseurRepository.save(fornisseur);

    return new ResponseEntity<Fornisseur>(fornisseur, HttpStatus.CREATED);
  }

  public ResponseEntity<?> delete(long id) {
    Optional<Fornisseur> fornisseur1 = fornisseurRepository.findById(id);

    if (!fornisseur1.isPresent()){
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),605,"Fornisseur dosen't exist");
      return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
    }

    fornisseurRepository.delete(fornisseur1.get());

    return new ResponseEntity<>(HttpStatus.OK);

  }

  public ResponseEntity<?> getById(long id) {
    Optional<Fornisseur> fornisseur = fornisseurRepository.findById(id);

    if (!fornisseur.isPresent()){
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),605,"Fornisseur dosen't exist");
      return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
    }

    return new ResponseEntity<>(fornisseur.get(),HttpStatus.OK);

  }

  public ResponseEntity<?> getAll() {
    return new ResponseEntity<>(fornisseurRepository.findAll(),HttpStatus.OK);
  }

  public ResponseEntity<?> getByCode(String code) {

    Optional<Fornisseur> fornisseur = fornisseurRepository.findByCode(code);

    if (!fornisseur.isPresent()){
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),605,"Fornisseur dosen't exist");
      return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
    }

    return new ResponseEntity<Fornisseur>(fornisseur.get(),HttpStatus.OK);
  }

  public ResponseEntity<?> edit(long id, String name, String code, String activite, String tel1, String tel2, String adresse, String codePostale, String ville, String pays, String codeTva, String matFiscale, String cin, Float solde, String email, String website) {

    Optional<Fornisseur> fornisseur = fornisseurRepository.findById(id);

    if (!fornisseur.isPresent()){
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),605,"Fornisseur dosen't exist");
      return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
    }

    if (name != null){
      fornisseur.get().setName(name);
    }

    if (code != null){
      fornisseur.get().setCode(code);
    }

    if (activite != null){
      fornisseur.get().setActivite(activite);
    }

    if (tel1 != null){
      fornisseur.get().setTel1(tel1);
    }

    if (tel2 != null){
      fornisseur.get().setTel2(tel2);
    }

    if (adresse != null){
      fornisseur.get().setAdresse(adresse);
    }

    if (codePostale != null){
      fornisseur.get().setCodePostal(codePostale);
    }

    if (ville != null){
      fornisseur.get().setVille(ville);
    }

    if (pays != null){
      fornisseur.get().setPays(pays);
    }

    if (codeTva != null){
      fornisseur.get().setCodeTVA(codeTva);
    }

    if (matFiscale != null){
      fornisseur.get().setMatFiscale(matFiscale);
    }

    if (cin != null){
      fornisseur.get().setCin(cin);
    }

    if (solde != null){
      fornisseur.get().setSolde(solde);
    }
    if (email != null){
      fornisseur.get().setEmail(email);
    }

    if (website != null){
      fornisseur.get().setWebsite(website);
    }

    fornisseurRepository.save(fornisseur.get());

    return new ResponseEntity<>(HttpStatus.OK);
  }
}
