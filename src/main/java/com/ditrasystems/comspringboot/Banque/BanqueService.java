package com.ditrasystems.comspringboot.Banque;


import com.ditrasystems.comspringboot.Banque.Models.BanqueModel;
import com.ditrasystems.comspringboot.Fournisseur.Fournisseur;
import com.ditrasystems.comspringboot.Fournisseur.FournisseurRepository;
import com.ditrasystems.comspringboot.Utils.ErrorResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Optional;

@Service
public class BanqueService {

    @Autowired
    FournisseurRepository fournisseurRepository;

    @Autowired
    BanqueRepository banqueRepository;


    public ResponseEntity<?> createService(BanqueModel banqueModel) {

        Banque banque = new Banque(banqueModel.getNom(),banqueModel.getRip(),banqueModel.getAgence());

        return new ResponseEntity<>(banqueRepository.save(banque),HttpStatus.OK);
    }

    public ResponseEntity<?> getAllService() {

        return new ResponseEntity<>(banqueRepository.findAll(),HttpStatus.OK);
    }

    public ResponseEntity<?> getBanqueByIdService(long id){
        Optional<Banque> banque = banqueRepository.findById(id);

        if (!banque.isPresent()){
            ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),633,"Banque n'existe pas");
            return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(banque,HttpStatus.OK);

    }

    public ResponseEntity<?> getBanqueByFournisseurCodeService(String fournisseurCode){

        Optional<Fournisseur> fournisseur = fournisseurRepository.findByCode(fournisseurCode);

        if (!fournisseur.isPresent()){
            ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),605,"Fournisseur n'existe pas");
            return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
        }

        ArrayList<Banque> banques = banqueRepository.findByFournisseurs(fournisseur.get());

        return new ResponseEntity<>(banques,HttpStatus.OK);

    }

    public ResponseEntity<?> updateService(long id, String nom, String agence, String rip) {
        Optional<Banque> banque = banqueRepository.findById(id);

        if (!banque.isPresent()){
            ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),633,"Banque n'existe pas");
            return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
        }

        if (nom != null){
            banque.get().setNom(nom);
        }

        if (agence != null){
            banque.get().setAgence(agence);
        }

        if (rip != null){
            banque.get().setRip(rip);
        }

        banqueRepository.save(banque.get());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<?> deleteService(long id) {

        Optional<Banque> banque = banqueRepository.findById(id);

        if (!banque.isPresent()){
            ErrorResponseModel errorResponseModel = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(),633,"Banque n'existe pas");
            return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
        }

        banqueRepository.delete(banque.get());
        return new ResponseEntity<>(HttpStatus.OK);

    }
}
