package com.ditrasystems.comspringboot.Construction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ConstructionController {

  @Autowired
  ConstructionServices constructionServices;


  @PutMapping("/construction/{id}")
  public ResponseEntity<?> edit(@PathVariable Long id , Long matiere , Float quantity ){
    return  constructionServices.edit(id,matiere,quantity);
  }

  @DeleteMapping("/construction/{id}")
  public ResponseEntity<?> delete(@PathVariable Long id){
    return constructionServices.delete(id);
  }
}
