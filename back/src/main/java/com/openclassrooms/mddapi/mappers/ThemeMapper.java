package com.openclassrooms.mddapi.mappers;

import com.openclassrooms.mddapi.dto.responses.ThemeResponse;
import com.openclassrooms.mddapi.entities.Theme;
import org.springframework.stereotype.Component;

/**
 * Mapper component responsible for converting between {@link Theme} entities
 * and their corresponding DTOs.
 */
@Component
public class ThemeMapper {

    /**
     * Converts a {@link Theme} entity to a {@link ThemeResponse} DTO.
     *
     * @param theme the theme entity to convert
     * @return the corresponding response DTO
     */
    public ThemeResponse toResponse(Theme theme) {
        ThemeResponse response = new ThemeResponse();
        response.setId(theme.getId());
        response.setName(theme.getName());
        response.setDescription(theme.getDescription());
        return response;
    }
}
