package sk.tuke.gamestudio.logicalmazes.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;
import sk.tuke.gamestudio.logicalmazes.core.Coin;
import sk.tuke.gamestudio.logicalmazes.core.Field;
import sk.tuke.gamestudio.logicalmazes.core.Tile;
import sk.tuke.gamestudio.logicalmazes.entity.Comment;
import sk.tuke.gamestudio.logicalmazes.entity.Rating;
import sk.tuke.gamestudio.logicalmazes.entity.Score;
import sk.tuke.gamestudio.logicalmazes.enumeration.Direction;
import sk.tuke.gamestudio.logicalmazes.enumeration.FieldState;
import sk.tuke.gamestudio.logicalmazes.server.webservice.serviceComment.CommentService;
import sk.tuke.gamestudio.logicalmazes.server.webservice.serviceRating.RatingService;
import sk.tuke.gamestudio.logicalmazes.server.webservice.seviceScore.ScoreService;

import java.util.Date;

@Controller
@RequestMapping("/v1/mazes")
@Scope(WebApplicationContext.SCOPE_SESSION)
public class MazesController {
    private Field field =
            new Field(9, 9);
    @Autowired
    private ScoreService scoreService;

    @Autowired
    private RatingService ratingService;

    @Autowired
    private CommentService commentService;

    private boolean isScore = false;
    private boolean isFeedback = false;
    private boolean isCommented = false;
    private Coin coin = new Coin();


    public String getHtmlField() throws IllegalAccessException {
        StringBuilder sb = new StringBuilder();
        sb.append("<table>\n");

        for (int row = 0; row < field.getRow(); row++) {
            sb.append("<tr>\n");
            for (int column = 0; column < field.getColumn(); column++) {
                var tile = field.getTiles(row, column);
                sb.append("<td>\n");
                sb.append("<a href='/v1/mazes?row=" + row + "&column=" + column + "'>\n");
                sb.append("<img src='/image/" + getImageName(tile) + ".png'>");
                sb.append("</a>\n");
                sb.append("</td>\n");
            }
            sb.append("</tr>\n");
        }

        sb.append("</table>\n");
        return sb.toString();
    }

    public void mazes(String direction) {
        if (field.getState() == FieldState.PLAYING) {
            switch (direction) {
                case "w":
                    topMove();
                    break;
                case "s":
                    downMove();
                    break;
                case "d":
                    rightMove();
                    break;
                case "a":
                    leftMove();
                    break;
                default:
                    break;
            }
        }

        if (!isScore) {
            gameWon();
        }
    }

    public void gameWon() {
        if (field.getState() ==
                FieldState.WIN
        ) {

            field.moving(Direction.NONE);

            if (!isScore) {
                scoreService.addScore(
                        new Score(
                                "mazes",
                                System.getProperty("user.name"),
                                field.count(),
                                new Date()));
                isScore = true;
            }
        }
    }

    @GetMapping
    public String changeDirection(@RequestParam(required = false)
                                  String direction, Model model) {
        if (direction != null) {
            mazes(direction);
        }

        model.addAttribute("scores", scoreService.getTopScores("mazes"));
        model.addAttribute("rating", ratingService.getRating("mazes"));
        model.addAttribute("comment", commentService.getComments("mazes"));

        return "mazes";
    }


    @GetMapping(value = "/new")
    public String newGame() {
        field = new Field(9, 9);
        return "redirect:/v1/mazes";
    }

    @GetMapping(value = "/restart")
    public String restartGame() {
        field.restart();
        return "redirect:/v1/mazes";
    }

    @GetMapping(value = "/previous")
    public String previousLevel() {
        field.backLevel();
        return "redirect:/v1/mazes";
    }


    @PostMapping("/api/rating")
    public String rating(@ModelAttribute("rating") int rating) {
        if (!isFeedback) {
            ratingService.setRating(
                    new Rating(
                            System.getProperty("user.name"),
                            "mazes",
                            rating,
                            new Date()));
            isFeedback = true;
        }


        return "redirect:/v1/mazes";
    }

    @PostMapping("/api/comment")
    public String comment(@ModelAttribute("comment") String comment) {
        if (!isCommented) {
            commentService.addComment(
                    new Comment(
                            System.getProperty("user.name"),
                            "mazes",
                            comment,
                            new Date()));
            isCommented = true;
        }
        return "redirect:/v1/mazes";
    }

    public String topMove() {
        field.moving(Direction.UP);
        return "mazes";
    }

    public String leftMove() {
        field.moving(Direction.LEFT);
        return "mazes";
    }

    public String rightMove() {
        field.moving(Direction.RIGHT);
        return "mazes";
    }

    public String downMove() {
        field.moving(Direction.DOWN);
        return "mazes";
    }

    private String getImageName(Tile tile)
            throws IllegalAccessException {
        switch (tile.getState()) {
            case PLAYER:
                return "lepricom";
            case CLUE:
                return "clue";
            case WALL:
                return "wall";
            case COIN:
                return "gold";
            case FINISH:
                return "pod";
            default:
                throw new IllegalAccessException("Unexpected tile state!");
        }
    }

    public boolean getIsFeedback() {
        return isFeedback;
    }

    public boolean isCommented() {
        return isCommented;
    }

    public String getState() {
        return field.getState().toString();
    }

    public int getScore() {
        return field.getCoin().getCount();
    }

    public String getStateWin() {
        if (field.getState() == FieldState.WIN) {
            return FieldState.WIN.toString();
        }

        return getState();
    }

    public int getRating() {
        return ratingService.getAverageRating("mazes").intValue();
    }
}
