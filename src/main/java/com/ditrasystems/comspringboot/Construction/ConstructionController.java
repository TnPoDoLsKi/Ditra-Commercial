package com.ditrasystems.comspringboot.Construction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ConstructionController {

  @Autowired
  ConstructionServices constructionServices;


  @PutMapping("/construction/{codePF}")
  public ResponseEntity<?> create(@PathVariable String codePF, @RequestParam  String codeMP , @RequestParam float quantity ){
    return constructionServices.create(codePF,codeMP,quantity);
  }

/*  @PutMapping("/constructions/{codePF}")
  public ResponseEntity<?> getAll(@PathVariable String codePF){
    return constructionServices.getAll(codePF);
  }*/

 /* @PutMapping("/construction/{codePF}")
  public ResponseEntity<?> edit(@PathVariable String codePF , String codeMP , float quantity ){
    return  constructionServices.edit(codePF,codeMP,quantity);
  }*/

  @DeleteMapping("/construction/{codePF}/{codeMP}")
  public ResponseEntity<?> delete(@PathVariable String codePF,@PathVariable String codeMP){
    return constructionServices.delete(codePF,codeMP);
  }
}
