package com.openclassrooms.mddapi.Seeders;

import com.openclassrooms.mddapi.entities.Topic;
import com.openclassrooms.mddapi.repositories.TopicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Order(1)
@RequiredArgsConstructor
public class TopicSeeder implements CommandLineRunner {
    private final TopicRepository topicRepository;

    @Override
    public void run(String... args) {
        if (topicRepository.count() == 0) {
            List<Topic> topics = List.of(
                createTopic("Java", "Langage de programmation orienté objet robuste et portable"),
                createTopic("Python", "Langage polyvalent idéal pour la data science et l'IA"),
                createTopic("JavaScript", "Langage incontournable du développement web frontend"),
                createTopic("Spring Boot", "Framework Java pour construire des APIs REST"),
                createTopic("Angular", "Framework JavaScript frontend maintenu par Google")
            );
            topicRepository.saveAll(topics);
            System.out.println("Topics successfully initialized");
        } else {
            System.out.println("Topics already exist, no seeding is necessary");
        }
    }

    private Topic createTopic(String name, String description) {
        Topic topic = new Topic();
        topic.setName(name);
        topic.setDescription(description);
        return topic;
    }
}
