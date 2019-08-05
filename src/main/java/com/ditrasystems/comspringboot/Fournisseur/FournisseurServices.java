package com.ditrasystems.comspringboot.Fournisseur;

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

  public ResponseEntity<?> create(Fournisseur fournisseur) {

    if (fournisseur.getName()==null)
    {
        ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),600,"Fournisseur name required");
        return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
    }

    if (fournisseur.getCode()==null)
    {
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),601,"Fournisseur code required");
      return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
    }

    if (fournisseur.getAdresse()==null)
    {
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),602,"Fournisseur adresse required");
      return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
    }

    if (fournisseur.getTel1()==null)
    {
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),603,"Fournisseur tel1 required");
      return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
    }

    if (fournisseur.getVille()==null)
    {
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),604,"Fournisseur ville required");
      return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
    }


    fournisseurRepository.save(fournisseur);

    return new ResponseEntity<Fournisseur>(fournisseur, HttpStatus.CREATED);
  }

  public ResponseEntity<?> delete(long id) {
    Optional<Fournisseur> fornisseur1 = fournisseurRepository.findById(id);

    if (!fornisseur1.isPresent()){
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),605,"Fournisseur dosen't exist");
      return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
    }

    fournisseurRepository.delete(fornisseur1.get());

    return new ResponseEntity<>(HttpStatus.OK);

  }

  public ResponseEntity<?> getById(long id) {
    Optional<Fournisseur> fornisseur = fournisseurRepository.findById(id);

    if (!fornisseur.isPresent()){
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),605,"Fournisseur dosen't exist");
      return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
    }

    return new ResponseEntity<>(fornisseur.get(),HttpStatus.OK);

  }

  public ResponseEntity<?> getAll() {
    return new ResponseEntity<>(fournisseurRepository.findAll(),HttpStatus.OK);
  }

  public ResponseEntity<?> getByCode(String code) {

    Optional<Fournisseur> fornisseur = fournisseurRepository.findFournisseurByCode(code);

    if (!fornisseur.isPresent()){
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),605,"Fournisseur dosen't exist");
      return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
    }

    return new ResponseEntity<Fournisseur>(fornisseur.get(),HttpStatus.OK);
  }

  public ResponseEntity<?> edit(long id, String name, String code, String activite, String tel1, String tel2, String adresse, String codePostale, String ville, String pays, String codeTva, String matFiscale, String cin, Float solde, String email, String website) {

    Optional<Fournisseur> fornisseur = fournisseurRepository.findById(id);

    if (!fornisseur.isPresent()){
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),605,"Fournisseur dosen't exist");
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

    fournisseurRepository.save(fornisseur.get());

    return new ResponseEntity<>(HttpStatus.OK);
  }
}
