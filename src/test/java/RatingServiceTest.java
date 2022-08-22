import org.checkerframework.checker.units.qual.C;
import org.junit.Assert;
import org.junit.Test;
import sk.tuke.gamestudio.logicalmazes.core.Coin;
import sk.tuke.gamestudio.logicalmazes.core.Field;
import sk.tuke.gamestudio.logicalmazes.core.Finish;
import sk.tuke.gamestudio.logicalmazes.core.Player;
import sk.tuke.gamestudio.logicalmazes.entity.Rating;
import sk.tuke.gamestudio.logicalmazes.server.webservice.serviceRating.RatingService;
import sk.tuke.gamestudio.logicalmazes.server.webservice.serviceRating.RatingServiceFile;

import java.util.Date;

public class RatingServiceTest
        extends Assert {

    private final RatingService ratingService
            = new RatingServiceFile();

    @Test
    public void testReset() {
        ratingService.reset();
        assertEquals(0,
                ratingService
                        .getRating("mazes")
                        .size());
    }

    @Test
    public void testAddRating() {
        var date = new Date();

        ratingService.setRating(
                new Rating(
                        "mazes",
                        "Jaro",
                        10,
                        date
                )
        );
    }

    @Test
    public void testAverageRating() {
        ratingService.reset();
        var date = new Date();

        ratingService.setRating(
                new Rating(
                        "mazes",
                        "Jaro",
                        10,
                        date
                )
        );

        ratingService.setRating(
                new Rating(
                        "mazes",
                        "Stas",
                        1,
                        date
                )
        );

        var average =
                ratingService
                        .getAverageRating("mazes");

        assertEquals(5, average.intValue());
    }
}

