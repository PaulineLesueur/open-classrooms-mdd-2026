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

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;
    private final CommentMapper commentMapper;

    public List<CommentResponse> getByArticleId(Long articleId) {
        return commentRepository.findByArticleId(articleId)
                .stream()
                .map(commentMapper::toResponse)
                .collect(Collectors.toList());
    }

    public CommentResponse create(CommentRequest request) {
        User author = userRepository.findById(request.getAuthorId())
                .orElseThrow(() -> new RuntimeException("User not found with id : " + request.getAuthorId()));
        Article article = articleRepository.findById(request.getArticleId())
                .orElseThrow(() -> new RuntimeException("Article not found with id : " + request.getArticleId()));
        Comment comment = commentMapper.toEntity(request, author, article);
        return commentMapper.toResponse(commentRepository.save(comment));
    }
}