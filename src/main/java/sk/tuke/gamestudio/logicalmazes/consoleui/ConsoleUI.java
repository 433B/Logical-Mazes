package sk.tuke.gamestudio.logicalmazes.consoleui;

import org.springframework.beans.factory.annotation.Autowired;
import sk.tuke.gamestudio.logicalmazes.core.*;
import sk.tuke.gamestudio.logicalmazes.entity.Comment;
import sk.tuke.gamestudio.logicalmazes.entity.Rating;
import sk.tuke.gamestudio.logicalmazes.entity.Score;
import sk.tuke.gamestudio.logicalmazes.enumeration.Direction;
import sk.tuke.gamestudio.logicalmazes.enumeration.FieldState;
import sk.tuke.gamestudio.logicalmazes.server.webservice.serviceComment.CommentService;
import sk.tuke.gamestudio.logicalmazes.server.webservice.serviceRating.RatingService;
import sk.tuke.gamestudio.logicalmazes.server.webservice.seviceScore.ScoreService;

import java.util.Date;
import java.util.Scanner;

public class ConsoleUI {
    private final Field field;
    private Scanner scanner = new Scanner(System.in);

    private String name;
    private String comment;
    private int mark;

    @Autowired
    private ScoreService scoreService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private RatingService ratingService;

    public ConsoleUI(Field field) {
        this.field = field;
    }

    private void playingGame() {
        while (field.getState() == FieldState.PLAYING) {

            printField();
            direction();
        }
    }

    private void solvedGame() {
        if (field.getState() == FieldState.WIN) {
            System.out.println("Game Win!");

            field.moving(Direction.NONE);
            printField();
        }
    }

    public boolean menu() {
        return menuPrint();
    }

    private boolean menuPrint() {
        boolean isQuit = false;

        String menu = menuHeader();

        while (!isQuit) {
            byte function = scanner.nextByte();
            isQuit = menuCommand(isQuit, menu, function);
        }
        return isQuit;
    }

    private void play() {
        playingGame();
        solvedGame();
    }

    private boolean menuCommand(boolean isQuit,
                                String menu,
                                byte function) {

        switch (function) {
            case 1:
                System.out.println("\nGood luck!\n");
                Field field =
                        new Field(9, 9);
                play();

                scoreService.addScore(
                        new Score("mazes",
                                System.getProperty("user.name"),
                                field.getCoin().getCount(),
                                new Date()));

                writeCommentToDb();
                isQuit = true;
                break;
            case 2:
                field = Field.load();
                play();

                scoreService.addScore(
                        new Score("mazes",
                                System.getProperty("user.name"),
                                field.count(),
                                new Date()));

                writeCommentToDb();
                isQuit = true;
                break;
            case 3:
                printDirection();
                System.out.println(menu);
                break;
            case 4:
                System.out.println("This is a maze in which you need" +
                        " to get from start O to finish F. This can be " +
                        "the fastest method, and can be the longest, " +
                        "the main thing in this game is to find a way " +
                        "out of the maze!\n");
                System.out.println(menu);
                break;
            case 5:
                printTopScores();
                System.out.println(menu);
                break;
            case 6:
                printComment();
                System.out.println(menu);
                break;
            case 7:
                printRating();
                System.out.println(menu);
                break;
            case 8:
                System.out.println("Goodbye! Come again");
                isQuit = true;
        }
        return isQuit;
    }


    private String menuHeader() {
        String menu = "\t-------------------------------------\n" +
                "\n\n\t\tWelcome! Let's play Logical Mazes!\n\n" +
                "1 - Play\n" + "2 - Continue\n" + "3 - Control\n"
                + "4 - Description\n" + "5 - Top gamer\n" + "6 - Comment\n"
                + "7 - Rating\n" + "8 - Quit\n"
                + "\n\t-------------------------------------";

        System.out.println(menu);
        return menu;
    }

    private void printField() {
        System.out.print(" ");

        for (int column = 0; column < field.getColumn(); column++) {
            System.out.print("--");
        }

        System.out.println();
        printFieldTile();
        System.out.print(" ");

        for (int column = 0; column < field.getColumn(); column++) {
            System.out.print("--");
        }

        printDirection();
    }

    private void printFieldTile() {
        for (int row = 0; row < field.getRow(); row++) {
            System.out.print("|");
            for (int column = 0; column < field.getColumn(); column++) {
                var tile = field.getTiles(row, column);
                System.out.print(" ");
                printObject(tile);
            }
            System.out.print(" |");
            System.out.println();
        }
    }


    private void printDirection() {
        System.out.println();
        System.out.println("Direction UP    -> 'W'");
        System.out.println("Direction DOWN  -> 'S'");
        System.out.println("Direction LEFT  -> 'A'");
        System.out.println("Direction RIGHT -> 'D'");
        System.out.println("Restart -> 'R'");
        System.out.println("Exit -> 'E'\n");
    }

    private void printObject(Tile tile) {
        switch (tile.getState()) {
            case CLUE:
                if (tile instanceof Clue) {
                    System.out.print(' ');
                }
            case WALL:
                if (tile instanceof Wall) {
                    System.out.print('#');
                }
            case PLAYER:
                if (tile instanceof Player) {
                    System.out.print('O');
                }
            case FINISH:
                if (tile instanceof Finish) {
                    System.out.print('F');
                }
            case COIN:
                if (tile instanceof Coin) {
                    System.out.print('C');
                }
            default:
                break;
        }
    }

    public void direction() {
        char Move = scanner.next().charAt(0);

        switch (Move) {
            case 'w':
            case 'W':
                field.moving(Direction.UP);
                break;
            case 'S':
            case 's':
                field.moving(Direction.DOWN);
                break;
            case 'A':
            case 'a':
                field.moving(Direction.LEFT);
                break;
            case 'd':
            case 'D':
                field.moving(Direction.RIGHT);
                break;
            case 'e':
            case 'E':
                writeCommentToDb();
                System.out.println("Goodbye! Come again");
                field.save();
                System.exit(0);
            case 'R':
            case 'r':
                field.restart();
                break;
            case 'p':
            case 'P':
                field.backLevel();
                break;
            default:
                System.out.println("Wrong input!");
        }
    }

    private void printTopScores() {
        var scores =
                scoreService.getTopScores("mazes");

        for (int i = 0; i < scores.size(); i++) {
            var score = scores.get(i);

            System.out.printf("%s %s %d %s\n",
                    score.getPlayer(), score.getGame(),
                    score.getPoint(), score.getPlayerAt());
        }
    }

    private void printComment() {
        var comments =
                commentService.getComments("mazes");

        for (int i = 0; i < comments.size(); i++) {
            var com = comments.get(i);

            System.out.printf("%s %s %s %s\n",
                    com.getPlayer(), com.getGame(),
                    com.getCommentedOn(), com.getComment());
        }
    }

    private void printRating() {
        var ratings =
                ratingService.getRating("mazes");

        for (int i = 0; i < ratings.size(); i++) {
            var rate = ratings.get(i);

            System.out.printf("%s %s %s %d\n",
                    rate.getPlayer(), rate.getGame(),
                    rate.getRatedOn(), rate.getRating());
        }
    }

    public void writeCommentToDb() {
        System.out.println("Enter your comment: ");
        scanner = new Scanner(System.in);

        this.comment = scanner.nextLine();
        commentService.addComment(
                new Comment(
                        System.getProperty("user.name"),
                        "mazes",
                        getComment(),
                        new Date()));

        System.out.println("Enter your rating 0 - 10: ");
        scanner = new Scanner(System.in);

        this.mark = scanner.nextInt();

        if (mark > 10) {
            mark = 10;
        } else if (mark < 0) {
            mark = 0;
        }

        ratingService.setRating(
                new Rating(
                        System.getProperty("user.name"),
                        "mazes",
                        getMark(),
                        new Date()));

        System.out.println("Thanks i want make my game better");
    }

    public String getName() {
        return name;
    }

    public String getComment() {
        return comment;
    }

    public int getMark() {
        return mark;
    }
}
