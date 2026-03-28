package com.openclassrooms.mddapi.dto.responses;

import lombok.Getter;
import lombok.Setter;

/**
 * Response DTO representing a user's public profile returned to the client.
 */
@Getter
@Setter
public class UserResponse {
    private String id;
    private String username;
    private String email;
}
