package sk.tuke.gamestudio.logicalmazes.server.webservice.serviceRating;

import org.springframework.stereotype.Repository;
import sk.tuke.gamestudio.logicalmazes.entity.Rating;

import java.util.List;

@Repository
public interface RatingService {
    void setRating(Rating rating);

    Double getAverageRating(String game);

    List<Rating> getRating(String game);

    void reset();
}
