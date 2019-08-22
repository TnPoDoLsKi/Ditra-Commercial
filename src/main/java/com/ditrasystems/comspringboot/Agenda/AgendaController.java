package com.ditrasystems.comspringboot.Agenda;

import com.ditrasystems.comspringboot.Utils.View;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1")
public class AgendaController {

  @Autowired
  AgendaService agendaService;

  @PostMapping("/agenda")
  public ResponseEntity<?> create(String fournisseurCode, @RequestBody Agenda agenda){
    return agendaService.createService(fournisseurCode,agenda);
  }

  @GetMapping("/agendas")
  public ResponseEntity<?> getAll(){
    return agendaService.getAllService();

  }

  @GetMapping("/agenda/{id}")
  public ResponseEntity<?> getAgendaById(@PathVariable long id){
    return agendaService.getAgendaByIdService(id);
  }

  @GetMapping("/agendas/byFournisseur/{fournisseurCode}")
  public ResponseEntity<?> getAgendaByFournisseurCode(@PathVariable String fournisseurCode){
    return agendaService.getAgendaByFournisseurCodeService(fournisseurCode);
  }

  @PutMapping("/agenda/{id}")
  public ResponseEntity<?> update(@PathVariable long id,String nom, String profession,String tel1,String tel2 ,String cin ,String email,String fax, Boolean principale,String fournisseurCode){
    return agendaService.updateService( id, nom,  profession, tel1, tel2 , cin , email,fax, principale, fournisseurCode);
  }

  @DeleteMapping("/agenda/{id}")
  public ResponseEntity<?> delete(@PathVariable long id){
    return agendaService.deleteService(id);
  }


}
