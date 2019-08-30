package com.ditrasystems.comspringboot.DemandeOffre;


import com.ditrasystems.comspringboot.DemandeOffre.Models.DemandeOffreModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("api/v1")
public class DemandeOffreController {

  @Autowired
  DemandeOffreServices demandeOffreServices;


  @PostMapping("/offre")
  public ResponseEntity<?> create(@RequestBody DemandeOffreModel demandeOffreModel){
    return  demandeOffreServices.create(demandeOffreModel);
  }


  @PostMapping("/offre/{code}/article")
  public ResponseEntity<?> addArticle(@PathVariable String code,String codeArticle,String designation,float quantiteDemander){
    return demandeOffreServices.addArticle(code,codeArticle,designation,quantiteDemander);
  }

  @GetMapping("/offres")
  public ResponseEntity<?> getAll(){
    return demandeOffreServices.getAll();
  }


  @GetMapping("/offre/{code}")
  public ResponseEntity<?> getById(@PathVariable String code){
    return demandeOffreServices.getById(code);
  }


  @PutMapping("/offre/{code}")
  public ResponseEntity<?> edit(@PathVariable String code , String codeUpdate , String date, String fournisseurCode){
    return demandeOffreServices.edit(code,codeUpdate,date, fournisseurCode);
  }


  @DeleteMapping("/offre/article/{idArticleOffre}")
  public ResponseEntity<?> deleteArticle(@PathVariable long idArticleOffre){
    return demandeOffreServices.deleteArticle(idArticleOffre);
  }


  @DeleteMapping("/offre/{code}")
  public ResponseEntity<?> delete(@PathVariable String code){
    return demandeOffreServices.delete(code);
  }
}
