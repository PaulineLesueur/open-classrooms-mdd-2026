package com.openclassrooms.mddapi.mappers;

import com.openclassrooms.mddapi.dto.requests.ArticleRequest;
import com.openclassrooms.mddapi.dto.responses.ArticleResponse;
import com.openclassrooms.mddapi.entities.Article;
import com.openclassrooms.mddapi.entities.Theme;
import com.openclassrooms.mddapi.entities.User;
import org.springframework.stereotype.Component;

/**
 * Mapper component responsible for converting between {@link Article} entities
 * and their corresponding DTOs.
 */
@Component
public class ArticleMapper {

    /**
     * Converts an {@link Article} entity to an {@link ArticleResponse} DTO.
     *
     * @param article the article entity to convert
     * @return the corresponding response DTO
     */
    public ArticleResponse toResponse(Article article) {
        ArticleResponse response = new ArticleResponse();
        response.setId(article.getId());
        response.setTitle(article.getTitle());
        response.setContent(article.getContent());
        response.setCreatedAt(article.getCreatedAt());
        response.setAuthorUsername(article.getAuthor().getUsername());
        response.setThemeName(article.getTheme().getName());
        return response;
    }

    /**
     * Converts an {@link ArticleRequest} DTO to an {@link Article} entity.
     *
     * @param request the request DTO containing article data
     * @param author  the user who authored the article
     * @param theme   the theme associated with the article
     * @return a new {@link Article} entity (not yet persisted)
     */
    public Article toEntity(ArticleRequest request, User author, Theme theme) {
        Article article = new Article();
        article.setTitle(request.getTitle());
        article.setContent(request.getContent());
        article.setAuthor(author);
        article.setTheme(theme);
        return article;
    }
}
