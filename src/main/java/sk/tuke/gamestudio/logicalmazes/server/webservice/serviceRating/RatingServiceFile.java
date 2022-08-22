package sk.tuke.gamestudio.logicalmazes.server.webservice.serviceRating;

import sk.tuke.gamestudio.logicalmazes.entity.Rating;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RatingServiceFile
        implements RatingService {

    private List<Rating> ratings
            = new ArrayList<>();

    @Override
    public void setRating(Rating rating) {
        ratings.add(rating);
    }

    @Override
    public Double getAverageRating(String game) {
        int value = 0;

        for (Rating r : ratings) {
            value += r.getRating();
        }

        return Double
                .valueOf(value / ratings.size());
    }

    @Override
    public List<Rating> getRating(String game) {
        return ratings.stream()
                .filter(s -> s.getGame().equals(game))
                .sorted((s1, s2) -> -Integer
                        .compare(s1.getRating(),
                                s2.getRating()))
                .limit(10)
                .collect(Collectors.toList());
    }

    @Override
    public void reset() {
        ratings = new ArrayList<>();
    }
}
