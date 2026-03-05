package com.openclassrooms.mddapi.dto.responses;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UserResponse {
    private String id;
    private String username;
    private String email;
}
