package com.openclassrooms.mddapi.controllers;

import com.openclassrooms.mddapi.dto.requests.PostRequest;
import com.openclassrooms.mddapi.dto.responses.PostResponse;
import com.openclassrooms.mddapi.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * REST controller exposing endpoints for post management.
 * Base path: {@code /api/posts}
 */
@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    /**
     * Retrieves all posts.
     *
     * @return HTTP 200 with a list of all posts
     */
    @GetMapping
    public ResponseEntity<List<PostResponse>> getAll() {
        return ResponseEntity.ok(postService.getAll());
    }

    /**
     * Retrieves a single post by its ID.
     *
     * @param id the ID of the post
     * @return HTTP 200 with the post, or an error if not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<PostResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(postService.getById(id));
    }

    /**
     * Creates a new post.
     *
     * @param request the request body containing post data
     * @return HTTP 201 with the created post
     */
    @PostMapping
    public ResponseEntity<PostResponse> create(@Valid @RequestBody PostRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(postService.create(request));
    }
}
