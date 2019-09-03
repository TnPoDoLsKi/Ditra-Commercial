package com.ditrasystems.comspringboot.Fournisseur;

import com.ditrasystems.comspringboot.Agenda.Agenda;
import com.ditrasystems.comspringboot.Fournisseur.Models.FournisseurModel;
import com.ditrasystems.comspringboot.Fournisseur.Models.FournisseurWithAgendaPrincipal;
import com.ditrasystems.comspringboot.Utils.Utils;
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

    Optional<Fournisseur> fournisseurOptional = fournisseurRepository.findByCode(fournisseur.getCode());

    if (fournisseurOptional.isPresent())
      return Utils.badRequestResponse(645,"Fournisseur deja exister");

    if (fournisseur.getNom()==null)
      return Utils.badRequestResponse(600,"Fournisseur nom requis");

    if (fournisseur.getCode()==null)
      return Utils.badRequestResponse(601,"Fournisseur code requis");

    if (fournisseur.getAdresse()==null)
      return Utils.badRequestResponse(602,"Fournisseur adresse requis");

    if (fournisseurModel.getAgendaList().size() == 0)
      return Utils.badRequestResponse(603,"ajouter une agneda au min");

    if (fournisseur.getVille()==null)
      return Utils.badRequestResponse(604,"Fournisseur ville requis");


    String telephone = "";

    for (Agenda agenda : fournisseurModel.getAgendaList()) {

      if (agenda.getNom() == null)
        return Utils.badRequestResponse(640,"un contact doit contenir un nom");

      if (agenda.getTelephone_1() == null)
        return Utils.badRequestResponse(641,"un contact doit contenir un numero de telephone");

      if (agenda.getPrincipale())
        telephone = agenda.getTelephone_1();

      agenda.setFournisseur(fournisseur);
      fournisseur.addAgenda(agenda);
    }

    fournisseurRepository.save(fournisseur);

    FournisseurWithAgendaPrincipal fournisseurWithAgendaPrincipal = new FournisseurWithAgendaPrincipal(fournisseur, telephone);

    return new ResponseEntity<>(fournisseurWithAgendaPrincipal, HttpStatus.CREATED);
  }

  public ResponseEntity<?> getByCode(String code) {

    Optional<Fournisseur> fournisseurOptional = fournisseurRepository.findByCode(code);

    if (!fournisseurOptional.isPresent())
      return Utils.badRequestResponse(605,"Fournisseur n'existe pas");

    return new ResponseEntity<>(fournisseurOptional.get(),HttpStatus.OK);
  }

  public ResponseEntity<?> getAll() {

    ArrayList<FournisseurWithAgendaPrincipal> fournisseurWithAgendaPrincipals = new ArrayList<>();

    ArrayList<Fournisseur> fournisseurs = (ArrayList<Fournisseur>) fournisseurRepository.findAll();

    for(Fournisseur fournisseur : fournisseurs){

      String telephone = fournisseur.getAgendas().stream().findFirst().get().getTelephone_1();

      for (Agenda agenda : fournisseur.getAgendas()) {
        if (agenda.getPrincipale()) {
          telephone = agenda.getTelephone_1();
          break;
        }
      }

      FournisseurWithAgendaPrincipal fournisseurWithAgendaPrincipal = new FournisseurWithAgendaPrincipal(fournisseur, telephone);
      fournisseurWithAgendaPrincipals.add(fournisseurWithAgendaPrincipal);
    }

    return new ResponseEntity<>(fournisseurWithAgendaPrincipals,HttpStatus.OK);
  }

  public ResponseEntity<?> updateByCode(String code, Fournisseur fournisseur) {

    Optional<Fournisseur> fournisseurLocal = fournisseurRepository.findByCode(code);

    if (!fournisseurLocal.isPresent())
      return Utils.badRequestResponse(605,"Fournisseur n'existe pas");

    fournisseur = Utils.merge(fournisseurLocal.get(),fournisseur);

    fournisseurRepository.save(fournisseur);

    return new ResponseEntity<>(HttpStatus.OK);
  }

  public ResponseEntity<?> deleteByCode(String code) {
    Optional<Fournisseur> fournisseur = fournisseurRepository.findByCode(code);

    if (!fournisseur.isPresent())
      return Utils.badRequestResponse(605,"Fournisseur n'existe pas");

    fournisseurRepository.delete(fournisseur.get());

    return new ResponseEntity<>(HttpStatus.OK);
  }

}
