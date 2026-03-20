package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.dto.requests.CommentRequest;
import com.openclassrooms.mddapi.dto.responses.CommentResponse;
import com.openclassrooms.mddapi.entities.Article;
import com.openclassrooms.mddapi.entities.Comment;
import com.openclassrooms.mddapi.entities.User;
import com.openclassrooms.mddapi.mappers.CommentMapper;
import com.openclassrooms.mddapi.repositories.ArticleRepository;
import com.openclassrooms.mddapi.repositories.CommentRepository;
import com.openclassrooms.mddapi.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class handling business logic for comments.
 */
@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;
    private final CommentMapper commentMapper;

    /**
     * Retrieves all comments for a given article.
     *
     * @param articleId the ID of the article
     * @return a list of comments as response DTOs
     */
    public List<CommentResponse> getByArticleId(Long articleId) {
        return commentRepository.findByArticleId(articleId)
                .stream()
                .map(commentMapper::toResponse)
                .collect(Collectors.toList());
    }

    /**
     * Creates and persists a new comment.
     *
     * @param request the DTO containing the comment data
     * @return the created comment as a response DTO
     * @throws RuntimeException if the author or article is not found
     */
    public CommentResponse create(CommentRequest request) {
        User author = userRepository.findById(request.getAuthorId())
                .orElseThrow(() -> new RuntimeException("User not found with id : " + request.getAuthorId()));
        Article article = articleRepository.findById(request.getArticleId())
                .orElseThrow(() -> new RuntimeException("Article not found with id : " + request.getArticleId()));
        Comment comment = commentMapper.toEntity(request, author, article);
        return commentMapper.toResponse(commentRepository.save(comment));
    }
}