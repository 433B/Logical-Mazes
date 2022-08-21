package sk.tuke.gamestudio.logicalmazes;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import sk.tuke.gamestudio.logicalmazes.consoleui.ConsoleUI;
import sk.tuke.gamestudio.logicalmazes.core.Field;
import sk.tuke.gamestudio.logicalmazes.server.webservice.serviceComment.CommentService;
import sk.tuke.gamestudio.logicalmazes.server.webservice.serviceComment.CommentServiceRestClient;
import sk.tuke.gamestudio.logicalmazes.server.webservice.serviceRating.RatingService;
import sk.tuke.gamestudio.logicalmazes.server.webservice.serviceRating.RatingServiceRestClient;
import sk.tuke.gamestudio.logicalmazes.server.webservice.seviceScore.ScoreService;
import sk.tuke.gamestudio.logicalmazes.server.webservice.seviceScore.ScoreServiceRestClient;

@Configuration
public class SpringService {
    @Bean
    public CommandLineRunner runner(ConsoleUI console) {
        return s -> console.menu();
    }

    @Bean
    public ConsoleUI console(Field field) {
        return new ConsoleUI(field);
    }

    @Bean
    public Field field() {
        return new Field(9, 9);
    }

    @Bean
    public ScoreService scoreService() {
        return new ScoreServiceRestClient();
    }

    @Bean
    public CommentService commentService() {
        return new CommentServiceRestClient();
    }

    @Bean
    public RatingService ratingService() {
        return new RatingServiceRestClient();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}

