package com.openclassrooms.mddapi.repositories;

import com.openclassrooms.mddapi.entities.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for {@link Article} entities.
 * <p>
 * Extends {@link JpaRepository} to provide standard CRUD operations,
 * and declares an additional query method to fetch articles by theme.
 * </p>
 */
@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

    /**
     * Retrieves all articles associated with a given theme.
     *
     * @param themeId the ID of the theme
     * @return a list of articles belonging to the specified theme
     */
    List<Article> findByThemeId(Integer themeId);
}
