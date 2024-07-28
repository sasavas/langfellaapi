package com.zenkodyazilim.langfella.features.article;

import com.zenkodyazilim.langfella.features.article.entities.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
}
