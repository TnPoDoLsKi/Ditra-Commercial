package com.ditrasystems.comspringboot.Articles;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

public interface ArticleRepository extends JpaRepository<Article,Long> {
  ArrayList<Article> findByType(String type);
  Article findByCode(String code);
}
