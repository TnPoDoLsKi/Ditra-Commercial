package com.ditrasystems.comspringboot.Marge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MargeController {

  @Autowired
  MargeServices margeServices;

  @PostMapping("/marge")
  public ResponseEntity<?> create(@RequestParam Long articleId, Float quantity , Float prix ){
   return margeServices.create(articleId,quantity,prix);
  }

  @PutMapping("/marge/{id}")
  public ResponseEntity<?> edit(@PathVariable Long id , Float quantity , Float prix  ){
    return  margeServices.edit(id,quantity,prix);
  }

  @DeleteMapping("/marge/{id}")
  public ResponseEntity<?> delete(@PathVariable Long id){
    return margeServices.delete(id);
  }


}
