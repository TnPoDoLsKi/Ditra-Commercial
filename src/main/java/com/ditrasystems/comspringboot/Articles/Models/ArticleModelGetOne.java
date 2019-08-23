package com.ditrasystems.comspringboot.Articles.Models;

import com.ditrasystems.comspringboot.Articles.Article;
import com.ditrasystems.comspringboot.Construction.Construction;
import com.ditrasystems.comspringboot.Marge.Marge;

import java.util.ArrayList;
import java.util.Collection;

public class ArticleModelGetOne {

    private Article article;

    private Collection<Construction> constructions=new ArrayList<>();

    private Collection<Marge> marges = new ArrayList<>();

    public ArticleModelGetOne() {}

    public ArticleModelGetOne(Article article, ArrayList<Construction> constructions, ArrayList<Marge> marges) {
        this.article = article;
        this.constructions = constructions;
        this.marges = marges;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Collection<Construction> getConstructions() {
        return constructions;
    }

    public void setConstructions(Collection<Construction> constructions) {
        this.constructions = constructions;
    }

    public Collection<Marge> getMarges() {
        return marges;
    }

    public void setMarges(Collection<Marge> marges) {
        this.marges = marges;
    }
}
