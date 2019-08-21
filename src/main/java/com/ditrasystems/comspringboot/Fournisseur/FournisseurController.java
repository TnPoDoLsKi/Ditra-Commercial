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
    return fournisseurServices.createService(fournisseur);
  }

  @GetMapping("/fournisseurs")
  public ResponseEntity<?> getAll(){
    return fournisseurServices.getAllService();
  }

  @GetMapping("/fournisseur/{code}")
  public ResponseEntity<?> getByCode(@PathVariable String code){
    return fournisseurServices.getByCodeService(code);
  }

  @PutMapping("/fournisseur/{code}")
  public ResponseEntity<?> editByCode(@PathVariable String code, String name, String codeUpdate, String activite, String adresse,  String codePostale,  String ville,  String pays,  String codeTva,  String matFiscale,  Float solde,  String email,  String website,Float plafont_credit,String observation){
    return  fournisseurServices.updateService(code,name,codeUpdate,activite,adresse,codePostale,ville,pays,codeTva,matFiscale,solde,email,website,plafont_credit,observation);
  }

  @DeleteMapping("/fournisseur/{code}")
  public ResponseEntity<?> delete(@PathVariable String code){
    return  fournisseurServices.deleteService(code);
  }
}
