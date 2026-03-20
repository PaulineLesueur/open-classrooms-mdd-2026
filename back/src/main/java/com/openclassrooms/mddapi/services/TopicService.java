package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.dto.responses.TopicResponse;
import com.openclassrooms.mddapi.entities.Topic;
import com.openclassrooms.mddapi.mappers.TopicMapper;
import com.openclassrooms.mddapi.repositories.TopicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class handling business logic for topics.
 */
@Service
@RequiredArgsConstructor
public class TopicService {
    private final TopicRepository topicRepository;
    private final TopicMapper topicMapper;

    /**
     * Retrieves all available topics.
     *
     * @return a list of all topics as response DTOs
     */
    public List<TopicResponse> getAll() {
        return topicRepository.findAll()
                .stream()
                .map(topicMapper::toResponse)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a single topic by its ID.
     *
     * @param id the ID of the topic
     * @return the topic as a response DTO
     * @throws RuntimeException if no topic is found with the given ID
     */
    public TopicResponse getById(Integer id) {
        Topic topic = topicRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No topic found with the id : " + id));
        return topicMapper.toResponse(topic);
    }
}
