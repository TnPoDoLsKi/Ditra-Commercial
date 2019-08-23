package com.ditrasystems.comspringboot.Construction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class ConstructionController {

  @Autowired
  ConstructionServices constructionServices;


  @PostMapping("/construction/{codePF}")
  public ResponseEntity<?> create(@PathVariable String codePF, @RequestParam  String codeMP , @RequestParam float quantity ){
    return constructionServices.create(codePF,codeMP,quantity);
  }

  @GetMapping("/constructions/{codePF}")
  public ResponseEntity<?> getAll(@PathVariable String codePF){
    return constructionServices.getAll(codePF);
  }

  @PutMapping("/construction/{codePF}/{codeMP}")
  public ResponseEntity<?> edit(@PathVariable String codePF ,@PathVariable  String codeMP , String codeMPUpdate ,float quantity ){
    return  constructionServices.edit(codePF,codeMP,codeMPUpdate,quantity);
  }

  @DeleteMapping("/construction/{codePF}/{codeMP}")
  public ResponseEntity<?> delete(@PathVariable String codePF,@PathVariable String codeMP){
    return constructionServices.delete(codePF,codeMP);
  }
}
