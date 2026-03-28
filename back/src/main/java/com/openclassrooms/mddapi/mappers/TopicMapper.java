package com.openclassrooms.mddapi.mappers;

import com.openclassrooms.mddapi.dto.responses.TopicResponse;
import com.openclassrooms.mddapi.entities.Topic;
import org.springframework.stereotype.Component;

/**
 * Mapper component responsible for converting between {@link Topic} entities
 * and their corresponding DTOs.
 */
@Component
public class TopicMapper {

    /**
     * Converts a {@link Topic} entity to a {@link TopicResponse} DTO.
     *
     * @param topic the topic entity to convert
     * @return the corresponding response DTO
     */
    public TopicResponse toResponse(Topic topic) {
        TopicResponse response = new TopicResponse();
        response.setId(topic.getId());
        response.setName(topic.getName());
        response.setDescription(topic.getDescription());
        return response;
    }
}
