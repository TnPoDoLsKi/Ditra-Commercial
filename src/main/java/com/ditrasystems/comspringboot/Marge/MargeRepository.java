package com.ditrasystems.comspringboot.Marge;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface MargeRepository extends JpaRepository<Marge,Long> {
    ArrayList<Marge> findByArticleCode(String articleCode);
}
