package com.openclassrooms.mddapi.controllers;

import com.openclassrooms.mddapi.dto.responses.ThemeResponse;
import com.openclassrooms.mddapi.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller exposing endpoints for managing user theme subscriptions.
 * Base path: {@code /api/subscriptions}
 */
@RestController
@RequestMapping("/api/subscriptions")
@RequiredArgsConstructor
public class SubscriptionController {

    private final UserService userService;

    /**
     * Retrieves all themes the currently authenticated user is subscribed to.
     *
     * @return HTTP 200 with a list of subscribed themes
     */
    @GetMapping
    public ResponseEntity<List<ThemeResponse>> getSubscriptions() {
        return ResponseEntity.ok(userService.getSubscriptions());
    }

    /**
     * Subscribes the currently authenticated user to a theme.
     *
     * @param themeId the ID of the theme to subscribe to
     * @return HTTP 200 on success
     */
    @PostMapping("/{themeId}")
    public ResponseEntity<Void> subscribe(@PathVariable Integer themeId) {
        userService.subscribe(themeId);
        return ResponseEntity.ok().build();
    }

    /**
     * Unsubscribes the currently authenticated user from a theme.
     *
     * @param themeId the ID of the theme to unsubscribe from
     * @return HTTP 204 on success
     */
    @DeleteMapping("/{themeId}")
    public ResponseEntity<Void> unsubscribe(@PathVariable Integer themeId) {
        userService.unsubscribe(themeId);
        return ResponseEntity.noContent().build();
    }
}