package com.ditrasystems.comspringboot.DemandeOffre;

import com.ditrasystems.comspringboot.ArticleOffre.ArticleOffre;
import com.ditrasystems.comspringboot.Fournisseur.Fournisseur;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Entity
@Getter
@Setter
@SQLDelete(sql=" UPDATE demande_offre SET deleted =true WHERE id = ?")
@Where(clause = "deleted = false")
public class DemandeOffre {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String code;

  @JsonFormat(pattern = "dd/MM/yyyy")
  private Date date;

  @JsonIgnore
  private boolean deleted;

  @JsonIgnore
  @OneToMany(mappedBy = "demandeOffre",cascade = CascadeType.ALL)
  private Collection<ArticleOffre> articleOffres = new ArrayList<>();

  @ManyToOne
  private Fournisseur fournisseur;
}
