package com.ditrasystems.comspringboot.Fournisseur.Models;

import com.ditrasystems.comspringboot.Agenda.Agenda;
import com.ditrasystems.comspringboot.Fournisseur.Fournisseur;

import java.util.List;

public class FournisseurModel {

  //{ code, name, activite, pays, ville, codePostal, address, codeTVA, solde, plafontCredit, email, siteweb, observation }

  private Fournisseur fournisseur;

  private List<Agenda> agendaList;

  public Fournisseur getFournisseur() {
    return fournisseur;
  }

  public void setFournisseur(Fournisseur fournisseur) {
    this.fournisseur = fournisseur;
  }

  public List<Agenda> getAgendaList() {
    return agendaList;
  }

  public void setAgendaList(List<Agenda> agendaList) {
    this.agendaList = agendaList;
  }
}
