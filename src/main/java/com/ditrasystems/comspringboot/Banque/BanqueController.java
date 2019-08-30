package com.ditrasystems.comspringboot.Banque;

import com.ditrasystems.comspringboot.Banque.Models.BanqueModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class BanqueController {

    @Autowired
    BanqueService banqueService;

    @PostMapping("/banque")
    public ResponseEntity<?> create(@RequestBody BanqueModel banqueModel){
        return banqueService.createService(banqueModel);
    }

    @GetMapping("/banques")
    public ResponseEntity<?> getAll(){
        return banqueService.getAllService();

    }

    @GetMapping("/banque/{id}")
    public ResponseEntity<?> getBanqueById(@PathVariable long id){
        return banqueService.getBanqueByIdService(id);
    }

    @GetMapping("/banque/byFournisseur/{fournisseurCode}")
    public ResponseEntity<?> getBanqueByFournisseurCode(@PathVariable String fournisseurCode){
        return banqueService.getBanqueByFournisseurCodeService(fournisseurCode);
    }

    @PutMapping("/banque/{id}")
    public ResponseEntity<?> update(@PathVariable long id, String nom, String agence, String rip){
        return banqueService.updateService(id,nom,agence,rip);
    }

    @DeleteMapping("/banque/{id}")
    public ResponseEntity<?> delete(@PathVariable long id){
        return banqueService.deleteService(id);
    }
}
