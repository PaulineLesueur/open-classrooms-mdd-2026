package com.openclassrooms.mddapi.mappers;

import com.openclassrooms.mddapi.dto.requests.UserRequest;
import com.openclassrooms.mddapi.dto.responses.UserResponse;
import com.openclassrooms.mddapi.entities.User;
import org.springframework.stereotype.Component;

/**
 * Mapper component responsible for converting between {@link User} entities
 * and their corresponding DTOs.
 */
@Component
public class UserMapper {

    /**
     * Converts a {@link User} entity to a {@link UserResponse} DTO.
     *
     * @param user the user entity to convert
     * @return the corresponding response DTO
     */
    public UserResponse toResponse(User user) {
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setUsername(user.getUsername());
        response.setEmail(user.getEmail());
        return response;
    }
}
