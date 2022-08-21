package sk.tuke.gamestudio.logicalmazes.server.webservice.serviceRating;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sk.tuke.gamestudio.logicalmazes.entity.Rating;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rating")
public class RatingServiceRest {
    @Autowired
    private RatingService ratingService;

    @GetMapping("/{game}")
    List<Rating> getRating(@PathVariable String game) {
        return ratingService.getRating(game);
    }

    @PostMapping
    void setRating(@RequestBody Rating rating) {
        ratingService.setRating(rating);
    }

    @GetMapping("/{game}/average")
    Double getAverageRating(String game) {
        return ratingService.getAverageRating(game);
    }
}
