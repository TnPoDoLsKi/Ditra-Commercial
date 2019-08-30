package com.ditrasystems.comspringboot.Agenda.models;

import com.ditrasystems.comspringboot.Agenda.Agenda;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AgendaUpdateModel {
  private Agenda agenda;
  private String codeFournisseur;
}
