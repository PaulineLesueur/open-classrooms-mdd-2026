package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.dto.responses.ThemeResponse;
import com.openclassrooms.mddapi.entities.Theme;
import com.openclassrooms.mddapi.mappers.ThemeMapper;
import com.openclassrooms.mddapi.repositories.ThemeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ThemeService {
    private final ThemeRepository themeRepository;
    private final ThemeMapper themeMapper;

    public List<ThemeResponse> getAll() {
        return themeRepository.findAll()
                .stream()
                .map(themeMapper::toResponse)
                .collect(Collectors.toList());
    }

    public ThemeResponse getById(Integer id) {
        Theme theme = themeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No theme found with the id : " + id));
        return themeMapper.toResponse(theme);
    }
}
