package com.openclassrooms.mddapi.controllers;

import com.openclassrooms.mddapi.dto.responses.TopicResponse;
import com.openclassrooms.mddapi.services.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST controller exposing endpoints for topic consultation.
 * Base path: {@code /api/topics}
 */
@RestController
@RequestMapping("/api/topics")
@RequiredArgsConstructor
public class TopicController {
    private final TopicService topicService;

    /**
     * Retrieves all available topics.
     *
     * @return HTTP 200 with a list of all topics
     */
    @GetMapping
    public ResponseEntity<List<TopicResponse>> getAll() {
        return ResponseEntity.ok(topicService.getAll());
    }

    /**
     * Retrieves a single topic by its ID.
     *
     * @param id the ID of the topic
     * @return HTTP 200 with the topic, or an error if not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<TopicResponse> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(topicService.getById(id));
    }
}
