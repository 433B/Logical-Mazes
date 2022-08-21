package sk.tuke.gamestudio.logicalmazes.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Rating {
    @Id
    @SequenceGenerator(
            name = "rating_sequance",
            sequenceName = "rating_sequance",
            allocationSize = 1
    )

    @GeneratedValue (
            strategy = GenerationType.SEQUENCE,
            generator = "player_sequance"
    )
    private Long id;
    private String player;
    private String game;
    private Integer rating;
    private Date ratedOn;

    public Rating() {

    }

    public Rating(String player,
                  String game,
                  Integer rating,
                  Date ratedOn) {
        this.player = player;
        this.game = game;
        this.rating = rating;
        this.ratedOn = ratedOn;
    }

    public String getPlayer() {
        return player;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public Integer getRating() {
        return rating;
    }

    public Date getRatedOn() {
        return ratedOn;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "player='" + player + '\'' +
                ", game='" + game + '\'' +
                ", rating=" + rating +
                ", ratedOn=" + ratedOn +
                '}';
    }
}
