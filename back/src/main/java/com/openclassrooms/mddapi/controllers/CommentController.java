package com.openclassrooms.mddapi.controllers;

import com.openclassrooms.mddapi.dto.requests.CommentRequest;
import com.openclassrooms.mddapi.dto.responses.CommentResponse;
import com.openclassrooms.mddapi.services.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * REST controller exposing endpoints for comment management.
 */
@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    /**
     * Retrieves all comments for a given post.
     *
     * @param postId the ID of the post
     * @return HTTP 200 with a list of comments
     */
    @GetMapping("/api/post/{postId}/comments")
    public ResponseEntity<List<CommentResponse>> getByPostId(@PathVariable Long postId) {
        return ResponseEntity.ok(commentService.getByPostId(postId));
    }

    /**
     * Creates a new comment.
     *
     * @param request the request body containing comment data
     * @return HTTP 201 with the created comment
     */
    @PostMapping("/api/comments")
    public ResponseEntity<CommentResponse> create(@Valid @RequestBody CommentRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.create(request));
    }
}
