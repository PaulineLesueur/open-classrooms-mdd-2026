package com.openclassrooms.mddapi.mappers;

import com.openclassrooms.mddapi.dto.requests.CommentRequest;
import com.openclassrooms.mddapi.dto.responses.CommentResponse;
import com.openclassrooms.mddapi.entities.Article;
import com.openclassrooms.mddapi.entities.Comment;
import com.openclassrooms.mddapi.entities.User;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {

    public CommentResponse toResponse(Comment comment) {
        CommentResponse response = new CommentResponse();
        response.setId(comment.getId());
        response.setBody(comment.getBody());
        response.setCreatedAt(comment.getCreatedAt());
        response.setAuthorUsername(comment.getAuthor().getUsername());
        return response;
    }

    public Comment toEntity(CommentRequest request, User author, Article article) {
        Comment comment = new Comment();
        comment.setBody(request.getBody());
        comment.setAuthor(author);
        comment.setArticle(article);
        return comment;
    }
}