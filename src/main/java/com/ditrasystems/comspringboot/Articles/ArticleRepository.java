package com.ditrasystems.comspringboot.Articles;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article,Long> {
  List<Article> findByType(String type);
}
