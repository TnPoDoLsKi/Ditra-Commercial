package com.ditrasystems.comspringboot.Agenda;

import com.ditrasystems.comspringboot.Agenda.models.AgendaUpdateModel;
import com.ditrasystems.comspringboot.Fournisseur.Fournisseur;
import com.ditrasystems.comspringboot.Fournisseur.FournisseurRepository;
import com.ditrasystems.comspringboot.Utils.ErrorResponseModel;
import com.ditrasystems.comspringboot.Utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class AgendaService {

  @Autowired
  FournisseurRepository fournisseurRepository;

  @Autowired
  AgendaRepository agendaRepository;


  public ResponseEntity<?> createService(String codeFournisseur, Agenda agenda) {

    Optional<Fournisseur> fournisseur = fournisseurRepository.findByCode(codeFournisseur);

    if (!fournisseur.isPresent())
      return Utils.badRequestResponse(605,"Fournisseur n'existe pas");

    if (agenda.getNom() == null)
      return Utils.badRequestResponse(640,"un contact doit contenir un nom");

    if (agenda.getTelephone_1() == null)
      return Utils.badRequestResponse(641,"un contact doit contenir un numero de telephone");

    agenda.setFournisseur(fournisseur.get());

    agenda = agendaRepository.save(agenda);

    if (agenda.getPrincipale()) {
      Optional<Agenda> principalAgenda = agendaRepository.findByFournisseurAndPrincipaleAndIdNot(agenda.getFournisseur(), true, agenda.getId());

      if(principalAgenda.isPresent()) {
        principalAgenda.get().setPrincipale(false);
        agendaRepository.save(principalAgenda.get());
      }
    }

    return new ResponseEntity<>(agenda, HttpStatus.OK);
  }

  public ResponseEntity<?> getAllService() {
    return new ResponseEntity<>(agendaRepository.findAll(),HttpStatus.OK);
  }

  public ResponseEntity<?> getAgendaByIdService(long id){
    
    Optional<Agenda> agenda = agendaRepository.findById(id);

    if (!agenda.isPresent())
      return Utils.badRequestResponse(613,"Agenda n'existe pas");
    
    return new ResponseEntity<>(agenda,HttpStatus.OK);
  }

  public ResponseEntity<?> getAgendaByCodeFournisseurService(String codeFournisseur){

    Optional<Fournisseur> fournisseur = fournisseurRepository.findByCode(codeFournisseur);

    if (!fournisseur.isPresent())
      return Utils.badRequestResponse(605,"Fournisseur n'existe pas");

    ArrayList<Agenda> agenda = agendaRepository.findByFournisseur(fournisseur.get());

    return new ResponseEntity<>(agenda,HttpStatus.OK);
  }

  public ResponseEntity<?> updateService(long id, AgendaUpdateModel agendaUpdateModel) {

    Optional<Agenda> agendaOptional = agendaRepository.findById(id);

    if (!agendaOptional.isPresent())
      return Utils.badRequestResponse(613,"Agenda n'existe pas");

    Agenda agenda = agendaOptional.get();

    if (agendaUpdateModel.getCodeFournisseur() != null) {
      Optional<Fournisseur> fournisseur = fournisseurRepository.findByCode(agendaUpdateModel.getCodeFournisseur());

      if (!fournisseur.isPresent())
        return Utils.badRequestResponse(605,"Fournisseur n'existe pas");

      agenda.setFournisseur(fournisseur.get());
    }

    agenda = Utils.merge(agenda, agendaUpdateModel.getAgenda());

    agendaRepository.save(agenda);

    if (agenda.getPrincipale()) {
      Optional<Agenda> principalAgenda = agendaRepository.findByFournisseurAndPrincipaleAndIdNot(agenda.getFournisseur(), true, agenda.getId());

      if(principalAgenda.isPresent()) {
        principalAgenda.get().setPrincipale(false);
        agendaRepository.save(principalAgenda.get());
      }
    }

    return new ResponseEntity<>(HttpStatus.OK);
  }

  public ResponseEntity<?> deleteService(long id) {

    Optional<Agenda> agenda = agendaRepository.findById(id);

    if (!agenda.isPresent())
      return Utils.badRequestResponse(613,"Agenda n'existe pas");

    agendaRepository.delete(agenda.get());

    return new ResponseEntity<>(HttpStatus.OK);
  }
}
