package com.ditrasystems.comspringboot.Depence;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
@SQLDelete(sql=" UPDATE depence SET deleted =true WHERE id = ?")
@Where(clause = "deleted = false")
public class Depence {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private boolean deleted;

  public Depence() {
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

}
