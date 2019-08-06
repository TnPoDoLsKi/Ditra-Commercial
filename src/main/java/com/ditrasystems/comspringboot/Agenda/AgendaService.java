package com.ditrasystems.comspringboot.Agenda;

import com.ditrasystems.comspringboot.Fournisseur.Fournisseur;
import com.ditrasystems.comspringboot.Fournisseur.FournisseurRepository;
import com.ditrasystems.comspringboot.Utils.ErrorResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AgendaService {

  @Autowired
  FournisseurRepository fournisseurRepository;

  @Autowired
  AgendaRepository agendaRepository;


  public ResponseEntity<?> create(long fournisseurId, Agenda agenda) {
   if (fournisseurId != 0) {
     Optional<Fournisseur> fournisseur = fournisseurRepository.findById(fournisseurId);

     if (!fournisseur.isPresent()) {
       ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(), 605, "Fournisseur n'existe pas");
       return new ResponseEntity<>(errorResponseModel, HttpStatus.BAD_REQUEST);
     }

     agenda.setFournisseur(fournisseur.get());

   }
    return new ResponseEntity<>(agendaRepository.save(agenda),HttpStatus.OK);
  }

  public ResponseEntity<?> edit(long id, String nom, String profession, String tel1, String tel2, String cin, String email, long fournisseurId) {
    Optional<Agenda> agenda = agendaRepository.findById(id);

    if (!agenda.isPresent()){
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),613,"Agenda n'existe pas");
      return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
    }

    if (fournisseurId != 0) {
      Optional<Fournisseur> fournisseur = fournisseurRepository.findById(fournisseurId);

      if (!fournisseur.isPresent()) {
        ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(), 605, "Fournisseur n'existe pas");
        return new ResponseEntity<>(errorResponseModel, HttpStatus.BAD_REQUEST);
      }

      agenda.get().setFournisseur(fournisseur.get());

    }


    if (nom != null){
      agenda.get().setNom(nom);
    }

    if (profession != null){
      agenda.get().setprofession(profession);
    }

    if (tel1 != null){
      agenda.get().setTel1(tel1);
    }

    if (tel2 != null){
      agenda.get().setTel2(tel2);
    }

    if (cin != null){
      agenda.get().setCin(cin);
    }

    if (email != null){
      agenda.get().setEmail(email);
    }

    agendaRepository.save(agenda.get());

    return new ResponseEntity<>(HttpStatus.OK);
  }

  public ResponseEntity<?> delete(long id) {

    Optional<Agenda> agenda = agendaRepository.findById(id);

    if (!agenda.isPresent()){
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),613,"Agenda n'existe pas");
      return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
    }

    agendaRepository.delete(agenda.get());
    return new ResponseEntity<>(HttpStatus.OK);

  }
}
