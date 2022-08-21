package sk.tuke.gamestudio.logicalmazes.server.webservice.seviceScore;

import org.springframework.stereotype.Repository;
import sk.tuke.gamestudio.logicalmazes.entity.Score;

import java.util.List;

@Repository
public interface ScoreService {
    void addScore(Score score);
    List<Score> getTopScores(String game);
    void reset();
}

