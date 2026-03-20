package com.openclassrooms.mddapi.controllers;

import com.openclassrooms.mddapi.dto.requests.ArticleRequest;
import com.openclassrooms.mddapi.dto.responses.ArticleResponse;
import com.openclassrooms.mddapi.services.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * REST controller exposing endpoints for article management.
 * Base path: {@code /api/articles}
 */
@RestController
@RequestMapping("/api/articles")
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;

    /**
     * Retrieves all articles.
     *
     * @return HTTP 200 with a list of all articles
     */
    @GetMapping
    public ResponseEntity<List<ArticleResponse>> getAll() {
        return ResponseEntity.ok(articleService.getAll());
    }

    /**
     * Retrieves a single article by its ID.
     *
     * @param id the ID of the article
     * @return HTTP 200 with the article, or an error if not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<ArticleResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(articleService.getById(id));
    }

    /**
     * Creates a new article.
     *
     * @param request the request body containing article data
     * @return HTTP 201 with the created article
     */
    @PostMapping
    public ResponseEntity<ArticleResponse> create(@Valid @RequestBody ArticleRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(articleService.create(request));
    }
}
