package com.ditrasystems.comspringboot.Agenda;

import com.ditrasystems.comspringboot.Agenda.models.AgendaUpdateModel;
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

  @PostMapping("/agenda/{codeFournisseur}")
  public ResponseEntity<?> create(@PathVariable String codeFournisseur, @RequestBody Agenda agenda){
    return agendaService.createService(codeFournisseur, agenda);
  }

  @GetMapping("/agendas")
  public ResponseEntity<?> getAll(){ return agendaService.getAllService();  }

  @GetMapping("/agenda/{id}")
  public ResponseEntity<?> getAgendaById(@PathVariable long id){
    return agendaService.getAgendaByIdService(id);
  }

  @GetMapping("/agendas/byFournisseur/{codeFournisseur}")
  public ResponseEntity<?> getAgendaByCodeFournisseur(@PathVariable String codeFournisseur){
    return agendaService.getAgendaByCodeFournisseurService(codeFournisseur);
  }

  @PutMapping("/agenda/{id}")
  public ResponseEntity<?> update(@PathVariable long id, @RequestBody AgendaUpdateModel agendaUpdateModel){
    return agendaService.updateService( id, agendaUpdateModel);
  }

  @DeleteMapping("/agenda/{id}")
  public ResponseEntity<?> delete(@PathVariable long id){
    return agendaService.deleteService(id);
  }


}
