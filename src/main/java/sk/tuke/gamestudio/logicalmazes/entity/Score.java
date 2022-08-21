package sk.tuke.gamestudio.logicalmazes.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Score
        implements Serializable {
    @Id
    @SequenceGenerator(
            name = "score_sequance",
            sequenceName = "score_sequance",
            allocationSize = 1
    )

    @GeneratedValue (
            strategy = GenerationType.SEQUENCE,
            generator = "player_sequance"
    )
    private int ident;
    private String player;
    private String game;
    private int point;
    private Date playerAt;

    public Score() {

    }

    public Score(String game,
                 String player,
                 int point,
                 Date playerAt) {
        this.player = player;
        this.game = game;
        this.point = point;
        this.playerAt = playerAt;
    }

    public String getPlayer() {
        return player;
    }

    public String getGame() {
        return game;
    }

    public int getPoint() {
        return point;
    }


    public Date getPlayerAt() {
        return playerAt;
    }

    @Override
    public String toString() {
        return "Score{" +
                "player='" + player + '\'' +
                ", game='" + game + '\'' +
                ", point=" + point +
                ", playerAt=" + playerAt +
                '}';
    }
}
