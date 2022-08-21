package sk.tuke.gamestudio.logicalmazes.server.webservice.serviceRating;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;
import sk.tuke.gamestudio.logicalmazes.entity.Rating;

import java.util.Arrays;
import java.util.List;

public class RatingServiceRestClient
        implements RatingService {
    @Value("${remote.server.api}")
    private String url;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void setRating(Rating rating) {
        restTemplate.postForEntity(
                url + "/rating",
                rating,
                Rating.class);
    }

    @Override
    public Double getAverageRating(String game) {
        return restTemplate.getForObject(
                url + "/rating/average/" + game,
                Double.class);
    }

    @Override
    public List<Rating> getRating(String game) {
        return Arrays.asList(
                restTemplate
                        .getForEntity(
                                url + "/rating/" + game,
                                Rating[].class).getBody());
    }

    @Override
    public void reset() {
        throw new RatingClientException(
                "Not supported via web service");
    }
}