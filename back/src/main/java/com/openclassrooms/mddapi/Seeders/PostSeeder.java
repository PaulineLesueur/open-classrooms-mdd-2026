package com.openclassrooms.mddapi.Seeders;

import com.openclassrooms.mddapi.entities.Post;
import com.openclassrooms.mddapi.entities.Topic;
import com.openclassrooms.mddapi.entities.User;
import com.openclassrooms.mddapi.repositories.PostRepository;
import com.openclassrooms.mddapi.repositories.TopicRepository;
import com.openclassrooms.mddapi.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Order(3)
@RequiredArgsConstructor
public class PostSeeder implements CommandLineRunner {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final TopicRepository topicRepository;

    @Override
    public void run(String... args) {
        if (postRepository.count() == 0) {
            User author = userRepository.findAll()
                    .stream()
                    .findFirst()
                    .orElse(null);
            List<Topic> topics = topicRepository.findAll();

            if (author == null || topics.isEmpty()) {
                System.out.println("No User or Topic found on database. Seeding ignored.");
                return;
            }

            Topic java        = findTopic(topics, "Java");
            Topic springBoot  = findTopic(topics, "Spring Boot");
            Topic python      = findTopic(topics, "Python");
            Topic javascript  = findTopic(topics, "JavaScript");
            Topic angular     = findTopic(topics, "Angular");

            List<Post> posts = List.of(
                createPost(
                    "Les nouveautés de Java 21 : records, sealed classes et pattern matching",
                    "Java 21 marque une étape majeure avec l'introduction de fonctionnalités attendues depuis longtemps. " +
                    "Les records, apparus en préview dès Java 14, sont désormais stables et permettent de déclarer des classes immuables en une seule ligne. " +
                    "Plus besoin de boilerplate pour les simples porteurs de données.\n\n" +
                    "Les sealed classes, quant à elles, permettent de restreindre explicitement quelles classes peuvent étendre une classe parente, " +
                    "offrant ainsi un contrôle total sur les hiérarchies de types. Combinées au pattern matching dans les switch expressions, " +
                    "elles permettent d'écrire du code expressif et exhaustif que le compilateur peut vérifier.\n\n" +
                    "Exemple concret avec un modèle de forme géométrique : en déclarant Shape comme sealed et ses sous-types (Circle, Rectangle, Triangle) " +
                    "comme permitted, le compilateur garantit que tout switch sur un Shape traite bien tous les cas possibles. " +
                    "Fini les NullPointerException surprises dans des branches oubliées.\n\n" +
                    "Ces fonctionnalités rapprochent Java des langages fonctionnels comme Scala ou Kotlin, tout en conservant la rigueur " +
                    "et l'outillage de l'écosystème Java. Une mise à jour incontournable pour tout développeur Java sérieux.",
                    author, java
                ),
                createPost(
                    "Spring Security 6 : sécuriser une API REST avec JWT",
                    "Avec Spring Boot 3 et Spring Security 6, la configuration de la sécurité a été profondément remaniée. " +
                    "L'approche basée sur l'héritage de WebSecurityConfigurerAdapter est définitivement abandonnée au profit d'une configuration déclarative via des beans.\n\n" +
                    "Pour sécuriser une API REST stateless, le flux est le suivant : l'utilisateur s'authentifie via un endpoint public (/api/auth/login), " +
                    "le serveur génère un JWT signé avec une clé secrète HMAC-SHA256, et le client joint ce token dans chaque requête via le header Authorization: Bearer <token>.\n\n" +
                    "Côté implémentation, il faut créer un OncePerRequestFilter qui intercepte chaque requête, extrait le token, le valide (signature + expiration), " +
                    "puis alimente le SecurityContext avec l'utilisateur correspondant. La SecurityFilterChain configure ensuite quelles routes sont publiques " +
                    "et lesquelles nécessitent une authentification.\n\n" +
                    "Un point important : stocker l'identifiant immuable de l'utilisateur (son UUID) dans le subject du JWT plutôt que son email. " +
                    "Cela évite les problèmes d'invalidation de token lorsque l'utilisateur modifie son adresse mail.",
                    author, springBoot
                ),
                createPost(
                    "Python asyncio : comprendre la programmation asynchrone",
                    "La programmation asynchrone en Python repose sur une boucle d'événements (event loop) qui orchestre l'exécution de coroutines. " +
                    "Contrairement au threading, asyncio ne crée pas plusieurs threads : tout s'exécute dans un seul thread, mais les tâches en attente d'I/O " +
                    "cèdent le contrôle à d'autres tâches grâce aux mots-clés async/await.\n\n" +
                    "Le cas d'usage principal est le traitement de nombreuses requêtes réseau ou lectures de fichiers en parallèle. " +
                    "Avec asyncio.gather(), on peut lancer des dizaines de requêtes HTTP simultanément sans bloquer le thread principal, " +
                    "là où du code synchrone les traiterait une par une.\n\n" +
                    "La bibliothèque httpx, par exemple, propose une interface async native parfaite pour ces scénarios. " +
                    "FastAPI, de son côté, est entièrement construit sur asyncio et tire pleinement parti de cette architecture pour offrir " +
                    "des performances comparables à Node.js ou Go sur des workloads I/O-bound.\n\n" +
                    "Attention toutefois : asyncio ne convient pas aux tâches CPU-intensives. Pour celles-ci, préférez multiprocessing ou concurrent.futures.",
                    author, python
                ),
                createPost(
                    "JavaScript : maîtriser les Promises et async/await",
                    "L'asynchronisme est au cœur de JavaScript, et bien le maîtriser fait toute la différence entre un code lisible et un callback hell ingérable. " +
                    "Les Promises ont été la première solution propre : elles représentent une valeur future et permettent de chaîner les opérations avec .then() et .catch().\n\n" +
                    "async/await, introduit en ES2017, est du sucre syntaxique au-dessus des Promises. Une fonction async retourne toujours une Promise, " +
                    "et await suspend l'exécution de la fonction jusqu'à ce que la Promise soit résolue, sans bloquer le thread principal.\n\n" +
                    "Quelques pièges courants : oublier await devant une Promise (la variable contiendra l'objet Promise, pas sa valeur), " +
                    "ne pas gérer les rejections avec try/catch, ou exécuter des opérations séquentiellement alors qu'elles pourraient être parallèles. " +
                    "Pour ce dernier cas, Promise.all() est votre meilleur allié : il lance plusieurs Promises en parallèle et attend que toutes soient résolues.\n\n" +
                    "Maîtriser ces patterns est indispensable pour travailler efficacement avec les APIs REST, IndexedDB, ou toute opération réseau en frontend.",
                    author, javascript
                ),
                createPost(
                    "Angular 17 : les composants standalone et le nouveau control flow",
                    "Angular 17 marque un tournant dans la façon d'écrire des applications Angular. Les composants standalone, qui permettent de se passer " +
                    "entièrement des NgModules, sont désormais le mode recommandé par défaut. Chaque composant déclare ses propres dépendances via le tableau imports, " +
                    "rendant le code plus modulaire et les imports explicites.\n\n" +
                    "Autre nouveauté majeure : le nouveau syntaxe de control flow dans les templates. Fini *ngIf et *ngFor, place à @if, @for et @switch. " +
                    "La syntaxe est plus lisible, et @for introduit une gestion native du suivi des éléments (track) qui améliore significativement les performances de rendu.\n\n" +
                    "Les signaux (Signals), introduits en préview dans Angular 16 et stabilisés progressivement, représentent une alternative réactive à RxJS " +
                    "pour la gestion de l'état local des composants. Avec signal(), computed() et effect(), on peut créer des dépendances réactives sans la verbosité des Observables.\n\n" +
                    "Ces évolutions rapprochent Angular des frameworks modernes comme Vue 3 et SolidJS, tout en conservant la structure et la typo-sécurité " +
                    "qui font la force d'Angular sur les projets d'envergure.",
                    author, angular
                )
            );

            postRepository.saveAll(posts);
            System.out.println("Posts successfully initialized");
        }
    }

    private Topic findTopic(List<Topic> topics, String name) {
        return topics.stream()
                .filter(t -> t.getName().equals(name))
                .findFirst()
                .orElse(topics.get(0));
    }

    private Post createPost(String title, String content, User author, Topic topic) {
        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        post.setAuthor(author);
        post.setTopic(topic);
        return post;
    }
}
