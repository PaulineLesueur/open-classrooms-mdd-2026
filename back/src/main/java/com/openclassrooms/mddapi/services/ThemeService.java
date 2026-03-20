package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.dto.responses.ThemeResponse;
import com.openclassrooms.mddapi.entities.Theme;
import com.openclassrooms.mddapi.mappers.ThemeMapper;
import com.openclassrooms.mddapi.repositories.ThemeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class handling business logic for themes.
 */
@Service
@RequiredArgsConstructor
public class ThemeService {
    private final ThemeRepository themeRepository;
    private final ThemeMapper themeMapper;

    /**
     * Retrieves all available themes.
     *
     * @return a list of all themes as response DTOs
     */
    public List<ThemeResponse> getAll() {
        return themeRepository.findAll()
                .stream()
                .map(themeMapper::toResponse)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a single theme by its ID.
     *
     * @param id the ID of the theme
     * @return the theme as a response DTO
     * @throws RuntimeException if no theme is found with the given ID
     */
    public ThemeResponse getById(Integer id) {
        Theme theme = themeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No theme found with the id : " + id));
        return themeMapper.toResponse(theme);
    }
}
