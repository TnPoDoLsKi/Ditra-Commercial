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
  public ResponseEntity<?> getByCode(@PathVariable String code){
    return demandeOffreServices.getByCode(code);
  }


  @PutMapping("/offre/{code}")
  public ResponseEntity<?> editByCode(@PathVariable String code ,@RequestBody DemandeOffre demandeOffre){
    return demandeOffreServices.editByCode(code,demandeOffre);
  }


  @DeleteMapping("/offre/article/{idArticleOffre}")
  public ResponseEntity<?> deleteArticleByCode(@PathVariable long idArticleOffre){
    return demandeOffreServices.deleteArticleByCode(idArticleOffre);
  }


  @DeleteMapping("/offre/{code}")
  public ResponseEntity<?> deleteByCode(@PathVariable String code){
    return demandeOffreServices.deleteByCode(code);
  }
}
