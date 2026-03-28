package com.openclassrooms.mddapi.controllers;

import com.openclassrooms.mddapi.dto.responses.TopicResponse;
import com.openclassrooms.mddapi.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller exposing endpoints for managing user topic subscriptions.
 * Base path: {@code /api/subscriptions}
 */
@RestController
@RequestMapping("/api/subscriptions")
@RequiredArgsConstructor
public class SubscriptionController {

    private final UserService userService;

    /**
     * Retrieves all topics the currently authenticated user is subscribed to.
     *
     * @return HTTP 200 with a list of subscribed topics
     */
    @GetMapping
    public ResponseEntity<List<TopicResponse>> getSubscriptions() {
        return ResponseEntity.ok(userService.getSubscriptions());
    }

    /**
     * Subscribes the currently authenticated user to a topic.
     *
     * @param topicId the ID of the topic to subscribe to
     * @return HTTP 200 on success
     */
    @PostMapping("/{topicId}")
    public ResponseEntity<Void> subscribe(@PathVariable Integer topicId) {
        userService.subscribe(topicId);
        return ResponseEntity.ok().build();
    }

    /**
     * Unsubscribes the currently authenticated user from a topic.
     *
     * @param topicId the ID of the topic to unsubscribe from
     * @return HTTP 204 on success
     */
    @DeleteMapping("/{topicId}")
    public ResponseEntity<Void> unsubscribe(@PathVariable Integer topicId) {
        userService.unsubscribe(topicId);
        return ResponseEntity.noContent().build();
    }
}
