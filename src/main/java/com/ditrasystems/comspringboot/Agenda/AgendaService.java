package com.ditrasystems.comspringboot.Agenda;

import com.ditrasystems.comspringboot.Fournisseur.Fournisseur;
import com.ditrasystems.comspringboot.Fournisseur.FournisseurRepository;
import com.ditrasystems.comspringboot.Utils.ErrorResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class AgendaService {

  @Autowired
  FournisseurRepository fournisseurRepository;

  @Autowired
  AgendaRepository agendaRepository;


  public ResponseEntity<?> createService(String fournisseurCode, Agenda agenda) {
   if (fournisseurCode != null) {
     Optional<Fournisseur> fournisseur = fournisseurRepository.findById(fournisseurCode);

     if (!fournisseur.isPresent()) {
       ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(), 605, "Fournisseur n'existe pas");
       return new ResponseEntity<>(errorResponseModel, HttpStatus.BAD_REQUEST);
     }

     agenda.setFournisseur(fournisseur.get());

   }
    return new ResponseEntity<>(agendaRepository.save(agenda),HttpStatus.OK);
  }

  public ResponseEntity<?> getAllService() {
    return new ResponseEntity<>(agendaRepository.findAll(),HttpStatus.OK);

  }

  public ResponseEntity<?> getAgendaByIdService(long id){
    Optional<Agenda> agenda = agendaRepository.findById(id);

    if (!agenda.isPresent()){
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),613,"Agenda n'existe pas");
      return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<>(agenda,HttpStatus.OK);

  }

  public ResponseEntity<?> getAgendaByFournisseurCodeService(String fournisseurCode){

    Optional<Fournisseur> fournisseur = fournisseurRepository.findById(fournisseurCode);

    if (!fournisseur.isPresent()){
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),605,"Fournisseur n'existe pas");
      return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
    }

    ArrayList<Agenda> agendas = agendaRepository.findByFournisseur(fournisseur.get());

    return new ResponseEntity<>(agendas,HttpStatus.OK);

  }

  public ResponseEntity<?> updateService(long id, String nom, String profession, String tel1, String tel2, String cin, String email,String fax, Boolean principale, String fournisseurCode) {
    Optional<Agenda> agenda = agendaRepository.findById(id);

    if (!agenda.isPresent()){
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),613,"Agenda n'existe pas");
      return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
    }

    if (fournisseurCode != null) {
      Optional<Fournisseur> fournisseur = fournisseurRepository.findById(fournisseurCode);

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
      agenda.get().setProfession(profession);
    }

    if (tel1 != null){
      agenda.get().setTelephone_1(tel1);
    }

    if (tel2 != null){
      agenda.get().setTelephone_2(tel2);
    }

    if (fax != null){
      agenda.get().setFax(fax);
    }

    if (cin != null){
      agenda.get().setCin(cin);
    }

    if (email != null){
      agenda.get().setEmail(email);
    }
    if (principale != null){
      agenda.get().setPrincipale(principale);
    }

    agendaRepository.save(agenda.get());

    return new ResponseEntity<>(HttpStatus.OK);
  }

  public ResponseEntity<?> deleteService(long id) {

    Optional<Agenda> agenda = agendaRepository.findById(id);

    if (!agenda.isPresent()){
      ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),613,"Agenda n'existe pas");
      return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
    }

    agendaRepository.delete(agenda.get());
    return new ResponseEntity<>(HttpStatus.OK);

  }
}
