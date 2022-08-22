import org.junit.Assert;
import org.junit.Test;
import sk.tuke.gamestudio.logicalmazes.entity.Score;
import sk.tuke.gamestudio.logicalmazes.server.webservice.seviceScore.ScoreService;
import sk.tuke.gamestudio.logicalmazes.server.webservice.seviceScore.ScoreServiceFile;

import java.util.Date;

public class ScoreServiceTest extends Assert {
    private final ScoreService scoreService
            = new ScoreServiceFile();


    @Test
    public void testReset() {
        scoreService.reset();
        assertEquals(0,
                scoreService
                        .getTopScores("mazes")
                        .size());
    }


    @Test
    public void testAddScore() {
        scoreService.reset();
        var date = new Date();

        scoreService.addScore(
                new Score(
                        "mazes",
                        "Jaro",
                        100,
                        date));
    }

    @Test
    public void testGetTopScore() {
        scoreService.reset();
        var date = new Date();

        scoreService.addScore(
                new Score("mazes",
                        "Stas",
                        700,
                        date));

        scoreService.addScore(
                new Score("mazes",
                        "Misha",
                        500,
                        date));

        scoreService.addScore(
                new Score("mazes",
                        "Andre",
                        300,
                        date));

        var scores =
                scoreService.getTopScores("mazes");

        assertEquals(3, scores.size());

        assertEquals("mazes", scores.get(0).getGame());
        assertEquals("Stas", scores.get(0).getPlayer());
        assertEquals(700, scores.get(0).getPoint());
        assertEquals(date, scores.get(0).getPlayerAt());

        assertEquals("mazes", scores.get(1).getGame());
        assertEquals("Misha", scores.get(1).getPlayer());
        assertEquals(500, scores.get(1).getPoint());
        assertEquals(date, scores.get(1).getPlayerAt());

        assertEquals("mazes", scores.get(2).getGame());
        assertEquals("Andre", scores.get(2).getPlayer());
        assertEquals(300, scores.get(2).getPoint());
        assertEquals(date, scores.get(2).getPlayerAt());
    }

}

