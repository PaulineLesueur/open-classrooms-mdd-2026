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

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/article/{articleId}")
    public ResponseEntity<List<CommentResponse>> getByArticleId(@PathVariable Long articleId) {
        return ResponseEntity.ok(commentService.getByArticleId(articleId));
    }

    @PostMapping
    public ResponseEntity<CommentResponse> create(@Valid @RequestBody CommentRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.create(request));
    }
}