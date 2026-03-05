package com.openclassrooms.mddapi.mappers;

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
}
