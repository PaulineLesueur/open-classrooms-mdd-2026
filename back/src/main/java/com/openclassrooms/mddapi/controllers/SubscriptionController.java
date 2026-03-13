package com.openclassrooms.mddapi.controllers;

import com.openclassrooms.mddapi.dto.responses.ThemeResponse;
import com.openclassrooms.mddapi.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subscriptions")
@RequiredArgsConstructor
public class SubscriptionController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<ThemeResponse>> getSubscriptions() {
        return ResponseEntity.ok(userService.getSubscriptions());
    }

    @PostMapping("/{themeId}")
    public ResponseEntity<Void> subscribe(@PathVariable Integer themeId) {
        userService.subscribe(themeId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{themeId}")
    public ResponseEntity<Void> unsubscribe(@PathVariable Integer themeId) {
        userService.unsubscribe(themeId);
        return ResponseEntity.noContent().build();
    }
}