package com.ditrasystems.comspringboot.Fournisseur;

import com.ditrasystems.comspringboot.Agenda.Agenda;
import com.ditrasystems.comspringboot.Fournisseur.Models.FournisseurModel;
import com.ditrasystems.comspringboot.Fournisseur.Models.FournisseurWithAgendaPrincipal;
import com.ditrasystems.comspringboot.Utils.ErrorResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class FournisseurServices {

  @Autowired
  FournisseurRepository fournisseurRepository;

  public ResponseEntity<?> createService(FournisseurModel fournisseurModel) {

    Fournisseur fournisseur=fournisseurModel.getFournisseur();


    if (fournisseur.getName()==null)
    {
        ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),600,"Fournisseur nom requis");
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

  public ResponseEntity<?> getByCodeService(String code) {
    Optional<Fournisseur> fournisseur = fournisseurRepository.findFournisseurByCode(code);

    if (!fournisseur.isPresent()){
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),605,"Fournisseur n'existe pas");
      return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
    }

    return new ResponseEntity<>(fournisseur.get(),HttpStatus.OK);

  }

  public ResponseEntity<?> getAllService() {

    String  telephone = "";

    ArrayList<FournisseurWithAgendaPrincipal> fournisseurWithAgendaPrincipals = new ArrayList<>();

    ArrayList<Fournisseur> fournisseurs = (ArrayList<Fournisseur>) fournisseurRepository.findAll();

    for(Fournisseur fournisseur : fournisseurs){

      for (Agenda agenda : fournisseur.getAgendas()){
        if (agenda.getPrincipale() == true){
          telephone = agenda.getTelephone_1();
          break;
        }
      }

      FournisseurWithAgendaPrincipal fournisseurWithAgendaPrincipal = new FournisseurWithAgendaPrincipal(fournisseur.getCode(),fournisseur.getNom(),fournisseur.getActivite(),fournisseur.getVille(),fournisseur.getSolde(),telephone);
      fournisseurWithAgendaPrincipals.add(fournisseurWithAgendaPrincipal);
    }

    return new ResponseEntity<>(fournisseurWithAgendaPrincipals,HttpStatus.OK);
  }

  public ResponseEntity<?> updateService(String code, String name, String codeUpdate, String activite, String adresse, String codePostale, String ville, String pays, String codeTva, String matFiscale, Float solde, String email, String website, Float plafont_credit,String observation) {

    Optional<Fournisseur> fournisseur = fournisseurRepository.findFournisseurByCode(code);

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

    if (plafont_credit != null){
      fournisseur.get().setPlafont_credit(plafont_credit);
    }

    if (observation != null){
      fournisseur.get().setObservation(observation);
    }

    fournisseurRepository.save(fournisseur.get());

    return new ResponseEntity<>(HttpStatus.OK);
  }

  public ResponseEntity<?> deleteService(String code) {
    Optional<Fournisseur> fournisseur = fournisseurRepository.findFournisseurByCode(code);

    if (!fournisseur.isPresent()){
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),605,"Fournisseur n'existe pas");
      return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
    }

    fournisseurRepository.delete(fournisseur.get());

    return new ResponseEntity<>(HttpStatus.OK);

  }

}
