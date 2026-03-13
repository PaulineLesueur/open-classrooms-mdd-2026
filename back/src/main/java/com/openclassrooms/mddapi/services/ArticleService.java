package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.dto.requests.ArticleRequest;
import com.openclassrooms.mddapi.dto.responses.ArticleResponse;
import com.openclassrooms.mddapi.entities.Article;
import com.openclassrooms.mddapi.entities.Theme;
import com.openclassrooms.mddapi.entities.User;
import com.openclassrooms.mddapi.mappers.ArticleMapper;
import com.openclassrooms.mddapi.repositories.ArticleRepository;
import com.openclassrooms.mddapi.repositories.ThemeRepository;
import com.openclassrooms.mddapi.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;
    private final ThemeRepository themeRepository;
    private final ArticleMapper articleMapper;

    public List<ArticleResponse> getAll() {
        return articleRepository.findAll()
                .stream()
                .map(articleMapper::toResponse)
                .collect(Collectors.toList());
    }

    public ArticleResponse getById(Long id) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Article not found with id : " + id));
        return articleMapper.toResponse(article);
    }

    public ArticleResponse create(ArticleRequest request) {
        User author = userRepository.findById(request.getAuthorId())
                .orElseThrow(() -> new RuntimeException("User not found with id : " + request.getAuthorId()));
        Theme theme = themeRepository.findById(request.getThemeId())
                .orElseThrow(() -> new RuntimeException("Theme not found with id : " + request.getThemeId()));
        Article article = articleMapper.toEntity(request, author, theme);
        return articleMapper.toResponse(articleRepository.save(article));
    }
}
