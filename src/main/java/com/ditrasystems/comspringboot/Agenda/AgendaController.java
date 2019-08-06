package com.ditrasystems.comspringboot.Agenda;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1")
public class AgendaController {

  @Autowired
  AgendaService agendaService;

  @PostMapping("/agenda")
  public ResponseEntity<?> create(long fournisseurId, @RequestBody Agenda agenda){
    return agendaService.create(fournisseurId,agenda);
  }

  @PutMapping("/agenda/{id}")
  public ResponseEntity<?> edit(@PathVariable long id,String nom, String profession,String tel1,String tel2 ,String cin ,String email,long fournisseurId){
    return agendaService.edit( id, nom,  profession, tel1, tel2 , cin , email,fournisseurId);
  }

  @DeleteMapping("/agenda/{id}")
  public ResponseEntity<?> delete(@PathVariable long id){
    return agendaService.delete(id);
  }


}
