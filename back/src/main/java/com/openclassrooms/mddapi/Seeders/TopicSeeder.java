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
                createTopic("Java", "Langage orienté objet incontournable de l'écosystème enterprise. Découvrez les nouveautés des dernières versions (records, sealed classes, pattern matching), la JVM, la gestion mémoire, et les bonnes pratiques pour écrire du code robuste et maintenable."),
                createTopic("Python", "Du scripting à l'intelligence artificielle, Python s'impose comme le langage le plus polyvalent du moment. Ce topic couvre la syntaxe moderne, les bibliothèques phares (NumPy, Pandas, FastAPI), la data science et le machine learning."),
                createTopic("JavaScript", "Omniprésent côté frontend comme backend (Node.js), JavaScript est le langage du web. Suivez les évolutions d'ES2024, les subtilités de l'asynchronisme, les patterns modernes et les pièges classiques à éviter."),
                createTopic("Spring Boot", "Le framework Java de référence pour construire des APIs REST et des microservices. Ce topic aborde la configuration automatique, Spring Security, Spring Data JPA, la gestion des exceptions et le déploiement en production."),
                createTopic("Angular", "Framework frontend TypeScript maintenu par Google, Angular propose une architecture robuste pour les applications à grande échelle. Explorez les composants, les services, RxJS, le routing, et les dernières évolutions des versions standalone.")
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
