package com.openclassrooms.mddapi.mappers;

import com.openclassrooms.mddapi.dto.requests.CommentRequest;
import com.openclassrooms.mddapi.dto.responses.CommentResponse;
import com.openclassrooms.mddapi.entities.Article;
import com.openclassrooms.mddapi.entities.Comment;
import com.openclassrooms.mddapi.entities.User;
import org.springframework.stereotype.Component;

/**
 * Mapper component responsible for converting between {@link Comment} entities
 * and their corresponding DTOs.
 */
@Component
public class CommentMapper {

    /**
     * Converts a {@link Comment} entity to a {@link CommentResponse} DTO.
     *
     * @param comment the comment entity to convert
     * @return the corresponding response DTO
     */
    public CommentResponse toResponse(Comment comment) {
        CommentResponse response = new CommentResponse();
        response.setId(comment.getId());
        response.setBody(comment.getBody());
        response.setCreatedAt(comment.getCreatedAt());
        response.setAuthorUsername(comment.getAuthor().getUsername());
        return response;
    }

    /**
     * Converts a {@link CommentRequest} DTO to a {@link Comment} entity.
     *
     * @param request the request DTO containing comment data
     * @param author  the user who posted the comment
     * @param article the article the comment belongs to
     * @return a new {@link Comment} entity (not yet persisted)
     */
    public Comment toEntity(CommentRequest request, User author, Article article) {
        Comment comment = new Comment();
        comment.setBody(request.getBody());
        comment.setAuthor(author);
        comment.setArticle(article);
        return comment;
    }
}