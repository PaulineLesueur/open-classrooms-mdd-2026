package com.openclassrooms.mddapi.mappers;

import com.openclassrooms.mddapi.dto.requests.ThemeRequest;
import com.openclassrooms.mddapi.dto.responses.ThemeResponse;
import com.openclassrooms.mddapi.entities.Theme;
import org.springframework.stereotype.Component;

@Component
public class ThemeMapper {
    public ThemeResponse toResponse(Theme theme) {
        ThemeResponse response = new ThemeResponse();
        response.setId(theme.getId());
        response.setName(theme.getName());
        response.setDescription(theme.getDescription());
        return response;
    }

    public Theme toEntity(ThemeRequest request) {
        Theme theme = new Theme();
        theme.setName(request.getName());
        theme.setDescription(request.getDescription());
        return theme;
    }
}
