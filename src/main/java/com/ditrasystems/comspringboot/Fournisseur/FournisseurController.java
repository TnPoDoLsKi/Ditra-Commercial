package com.ditrasystems.comspringboot.Fournisseur;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class FournisseurController {

  @Autowired
  FournisseurServices fournisseurServices;


  @PostMapping("/fornisseur")
  public ResponseEntity<?> create(@RequestBody Fournisseur fournisseur){
    return fournisseurServices.create(fournisseur);
  }


  @PutMapping("/fornisseur/{id}")
  public ResponseEntity<?> edit(@PathVariable long id,@RequestParam(required = false) String name,@RequestParam(required = false) String code,@RequestParam(required = false) String activite,@RequestParam(required = false) String tel1,@RequestParam(required = false) String tel2,@RequestParam(required = false) String adresse, @RequestParam(required = false) String codePostale, @RequestParam(required = false) String ville, @RequestParam(required = false) String pays, @RequestParam(required = false) String codeTva, @RequestParam(required = false) String matFiscale, @RequestParam(required = false) String cin, @RequestParam(required = false) Float solde, @RequestParam(required = false) String email, @RequestParam(required = false) String website){
    return  fournisseurServices.edit(id,name,code,activite,tel1,tel2,adresse,codePostale,ville,pays,codeTva,matFiscale,cin,solde,email,website);
  }

  @DeleteMapping("/fornisseur/{id}")
  public ResponseEntity<?> delete(@PathVariable long id){
    return  fournisseurServices.delete(id);
  }


  @GetMapping("/fornisseur/{id}")
  public ResponseEntity<?> getById(@PathVariable long id){
    return fournisseurServices.getById(id);
  }

  @GetMapping("/fornisseurs")
  public ResponseEntity<?> getAll(){
    return fournisseurServices.getAll();
  }

  @GetMapping("/fornisseur/{code}")
  public ResponseEntity<?> getByCode(@PathVariable String code){
    return fournisseurServices.getByCode(code);
  }
}
