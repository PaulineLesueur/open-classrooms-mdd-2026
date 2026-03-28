package com.openclassrooms.mddapi.dto.responses;

import lombok.Getter;
import lombok.Setter;

/**
 * Response DTO representing a topic returned to the client.
 */
@Getter
@Setter
public class TopicResponse {
    private Integer id;
    private String name;
    private String description;
}
