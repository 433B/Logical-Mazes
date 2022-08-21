package sk.tuke.gamestudio.logicalmazes.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Comment {
    @Id
    @SequenceGenerator(
            name = "comment_sequance",
            sequenceName = "comment_sequance",
            allocationSize = 1
    )

    @GeneratedValue (
            strategy = GenerationType.SEQUENCE,
            generator = "player_sequance"
    )
    private Long id;
    private String player;
    private String game;
    private String comment;
    private Date commentedOn;

    public Comment() {

    }

    public Comment(String player,
                   String game,
                   String comment,
                   Date commentedOn) {
        this.player = player;
        this.game = game;
        this.comment = comment;
        this.commentedOn = commentedOn;
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

    public String getComment() {
        return comment;
    }

    public Date getCommentedOn() {
        return commentedOn;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "player='" + player + '\'' +
                ", game='" + game + '\'' +
                ", comment='" + comment + '\'' +
                ", commentedOn=" + commentedOn +
                '}';
    }
}
