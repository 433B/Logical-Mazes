package sk.tuke.gamestudio.logicalmazes.server.webservice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sk.tuke.gamestudio.logicalmazes.server.webservice.serviceComment.CommentService;
import sk.tuke.gamestudio.logicalmazes.server.webservice.serviceComment.CommentServiceJPA;
import sk.tuke.gamestudio.logicalmazes.server.webservice.serviceRating.RatingService;
import sk.tuke.gamestudio.logicalmazes.server.webservice.serviceRating.RatingServiceJPA;
import sk.tuke.gamestudio.logicalmazes.server.webservice.seviceScore.ScoreService;
import sk.tuke.gamestudio.logicalmazes.server.webservice.seviceScore.ScoreServiceJPA;

@Configuration
public class Config {
    @Bean
    public ScoreService scoreService() {
        return new ScoreServiceJPA();
    }

    @Bean
    public CommentService commentService() {
        return new CommentServiceJPA();
    }

    @Bean
    public RatingService ratingService() {
        return new RatingServiceJPA();
    }
}

