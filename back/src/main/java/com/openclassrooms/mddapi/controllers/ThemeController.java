package com.openclassrooms.mddapi.controllers;

import com.openclassrooms.mddapi.dto.responses.ThemeResponse;
import com.openclassrooms.mddapi.services.ThemeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST controller exposing endpoints for theme consultation.
 * Base path: {@code /api/themes}
 */
@RestController
@RequestMapping("api/themes")
@RequiredArgsConstructor
public class ThemeController {
    private final ThemeService themeService;

    /**
     * Retrieves all available themes.
     *
     * @return HTTP 200 with a list of all themes
     */
    @GetMapping
    public ResponseEntity<List<ThemeResponse>> getAll() {
        return ResponseEntity.ok(themeService.getAll());
    }

    /**
     * Retrieves a single theme by its ID.
     *
     * @param id the ID of the theme
     * @return HTTP 200 with the theme, or an error if not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<ThemeResponse> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(themeService.getById(id));
    }
}
