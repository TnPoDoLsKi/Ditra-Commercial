package com.ditrasystems.comspringboot.BonDeLivraison;


import com.ditrasystems.comspringboot.BonDeLivraison.Models.BonDeLivraisonModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/v1")
public class BonDeLivrasionController {

  @Autowired
  BonDeLivraisonServices bonDeLivraisonServices;

  @PostMapping("/bonDeLivrasion")
  public ResponseEntity<?> create( @RequestBody BonDeLivraisonModel bonDeLivraisonModel){
    return bonDeLivraisonServices.create(bonDeLivraisonModel);
  }

  @PutMapping("/bonDeLivraison/{id}")
  public ResponseEntity<?> edit(@PathVariable Long id , String code , Date date){
    return bonDeLivraisonServices.edit(id,code,date);
  }
 }
