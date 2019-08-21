package com.ditrasystems.comspringboot.Fournisseur;


import com.ditrasystems.comspringboot.Fournisseur.Models.FournisseurModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class FournisseurController {

  @Autowired
  FournisseurServices fournisseurServices;


  @PostMapping("/fournisseur")
  public ResponseEntity<?> create(@RequestBody FournisseurModel fournisseur){
    return fournisseurServices.create(fournisseur);
  }

  @GetMapping("/fournisseurs")
  public ResponseEntity<?> getAll(){
    return fournisseurServices.getAllService();
  }

  @GetMapping("/fournisseur/{id}")
  public ResponseEntity<?> getById(@PathVariable long id){
    return fournisseurServices.getByIdService(id);
  }

  @GetMapping("/fournisseur/ByCode/{code}")
  public ResponseEntity<?> getByCode(@PathVariable String code){
    return fournisseurServices.getByCode(code);
  }

  @PutMapping("/fournisseur/{id}")
  public ResponseEntity<?> edit(@PathVariable long id, String name, String code, String activite, String adresse,  String codePostale,  String ville,  String pays,  String codeTva,  String matFiscale,  Float solde,  String email,  String website){
    return  fournisseurServices.edit(id,name,code,activite,adresse,codePostale,ville,pays,codeTva,matFiscale,solde,email,website);
  }

  @DeleteMapping("/fournisseur/{id}")
  public ResponseEntity<?> delete(@PathVariable long id){
    return  fournisseurServices.delete(id);
  }
}
