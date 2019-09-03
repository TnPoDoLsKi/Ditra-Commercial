package com.ditrasystems.comspringboot.Marge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class MargeController {

  @Autowired
  MargeServices margeServices;

  @PostMapping("/marge/{codeArticle}")
  public ResponseEntity<?> create(@PathVariable String codeArticle, @RequestParam float quantity , @RequestParam float PVente, @RequestParam float margeGagner ){
   return margeServices.create(codeArticle,quantity,PVente,margeGagner);
  }

  @GetMapping("/marge/{codeArticle}")
  public ResponseEntity<?> getByArticle(@PathVariable String codeArticle){
    return margeServices.getByArticle(codeArticle);
  }

  @PutMapping("/marge/{id}")
  public ResponseEntity<?> edit(@PathVariable Long id , @RequestBody Marge marge ){
    return  margeServices.edit(id,marge);
  }

  @DeleteMapping("/marge/{id}")
  public ResponseEntity<?> delete(@PathVariable long id){
    return margeServices.delete(id);
  }


}
