package com.openclassrooms.mddapi.mappers;

import com.openclassrooms.mddapi.dto.requests.UserRequest;
import com.openclassrooms.mddapi.dto.responses.UserResponse;
import com.openclassrooms.mddapi.entities.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserResponse toResponse(User user) {
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setUsername(user.getUsername());
        response.setEmail(user.getEmail());
        return response;
    }
}
