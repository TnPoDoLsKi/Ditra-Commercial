package com.ditrasystems.comspringboot.Fournisseur.Models;

import com.ditrasystems.comspringboot.Agenda.Agenda;
import com.ditrasystems.comspringboot.Fournisseur.Fournisseur;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@FieldDefaults(level= AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FournisseurModel {

   Fournisseur fournisseur;

   List<Agenda> agendaList;

}
