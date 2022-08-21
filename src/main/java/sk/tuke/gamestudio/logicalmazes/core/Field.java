package sk.tuke.gamestudio.logicalmazes.core;

import sk.tuke.gamestudio.logicalmazes.enumeration.Direction;
import sk.tuke.gamestudio.logicalmazes.enumeration.FieldState;
import sk.tuke.gamestudio.logicalmazes.enumeration.TileState;
import sk.tuke.gamestudio.logicalmazes.server.webservice.seviceScore.ScoreClientException;

import java.io.*;

public class  Field implements Serializable {
    private FieldState state = FieldState.PLAYING;
    private static final String FILE = "mazes.bin";

    private final Tile[][] tiles;
    private final int row;
    private final int column;
    private int level;

    private Coin coin;
    private Player player;
    private Finish finish;

    public Field(int row,
                 int column) {
        this.row = row;
        this.column = column;

        level = 1;
        tiles = new Tile[row][column];
        coin = new Coin();

        generate();
    }

    public void generate() {
        if (getLevel() == 1) {
            makeMapGame();
        } else if (getLevel() == 2) {
            makeMapGame2();
        } else if (getLevel() == 3) {
            makeMapGame3();
        } else if (getLevel() == 4) {
            makeMapGame4();
        } else if (getLevel() == 5) {
            makeMapGame5();
        } else if (getLevel() == 6) {
            makeMapGame6();
        } else {
            return;
        }
    }

    public void winGame() {
        if (getLevel() == 6) {
            state = FieldState.WIN;
        } else {
            setLevel(getLevel() + 1);
            System.out.println(getLevel());
            System.out.println("Congratulation you completed level!");
            generate();
            System.out.println("Press any button");
        }
    }

    public void backLevel() {
        setLevel(getLevel() - 1);
        System.out.println(getLevel());
        generate();
    }

    private void makeMapGame() {
        char[][] map;

        map = new char[][]{
                {'O', ' ', ' ', ' ', ' ', '#', ' ', ' ', ' '},
                {' ', ' ', '#', '#', ' ', '#', ' ', ' ', ' '},
                {' ', 'C', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', 'C', ' ', ' ', '#', '#'},
                {' ', ' ', '#', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', '#', 'C', '#', '#', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', '#', '#', ' ', ' ', ' ', '#', '#'},
                {' ', ' ', ' ', ' ', ' ', '#', ' ', ' ', 'F'},
        };
        addObject(map);
    }

    private void makeMapGame2() {
        char[][] map;

        map = new char[][]{
                {' ', ' ', ' ', ' ', '#', ' ', ' ', ' ', ' '},
                {' ', ' ', '#', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', 'C', ' ', ' ', ' ', ' ', '#', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', '#', ' ', 'O', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', '#', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', 'F', ' ', ' ', ' '},
                {'C', ' ', ' ', ' ', ' ', '#', 'C', ' ', ' '},
        };
        addObject(map);
    }

    private void makeMapGame3() {
        char[][] map;

        map = new char[][]{
                {'O', ' ', ' ', ' ', ' ', '#', 'C', ' ', ' '},
                {' ', ' ', '#', '#', ' ', '#', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', 'C', ' ', ' ', ' ', '#', '#'},
                {'C', ' ', '#', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', '#', ' ', '#', '#', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', '#', '#', ' ', ' ', ' ', '#', '#'},
                {'C', ' ', ' ', ' ', 'C', '#', ' ', ' ', 'F'},
        };
        addObject(map);
    }

    private void makeMapGame4() {
        char[][] map;

        map = new char[][]{
                {' ', '#', ' ', ' ', '#', 'C', ' ', ' ', ' '},
                {'C', ' ', ' ', ' ', ' ', ' ', '#', ' ', ' '},
                {'#', '#', ' ', '#', ' ', ' ', '#', ' ', '#'},
                {'O', '#', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', '#', ' ', ' ', '#', 'C'},
                {'#', ' ', ' ', ' ', '#', '#', ' ', '#', '#'},
                {'C', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'F'},
                {' ', ' ', ' ', '#', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', 'C', '#', ' ', '#'},
        };
        addObject(map);
    }

    private void makeMapGame5() {
        char[][] map;

        map = new char[][]{
                {' ', '#', ' ', 'C', '#', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', '#', ' ', 'C'},
                {'#', '#', ' ', '#', ' ', ' ', '#', ' ', '#'},
                {'O', '#', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', '#', ' ', ' ', '#', ' '},
                {'#', ' ', ' ', 'C', '#', '#', ' ', '#', '#'},
                {'C', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'F'},
                {' ', ' ', ' ', '#', ' ', ' ', 'C', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', '#', 'C', '#'},
        };
        addObject(map);
    }

    private void makeMapGame6() {
        char[][] map;

        map = new char[][]{
                {' ', ' ', ' ', 'C', '#', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', '#', '#', ' ', ' ', '#', '#'},
                {'C', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {'#', '#', ' ', '#', '#', ' ', ' ', '#', 'F'},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', '#', '#'},
                {'O', ' ', ' ', ' ', '#', ' ', ' ', ' ', ' '},
                {' ', '#', ' ', ' ', '#', '#', ' ', ' ', ' '},
                {'#', '#', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {'C', ' ', ' ', ' ', ' ', '#', ' ', '#', ' '},
        };
        addObject(map);
    }

    private void addObject(char[][] map) {
        for (int row = 0; row < getRow(); row++) {
            for (int column = 0; column < getColumn(); column++) {
                addObjectField(map, row, column);
            }
        }
    }

    private void addObjectField(char[][] map,
                                int row,
                                int column) {
        switch (map[row][column]) {
            case 'O':
                player = new Player(row, column);
                tiles[row][column] = player;
                tiles[row][column].setState(TileState.PLAYER);
                break;
            case 'F':
                tiles[row][column] = new Finish();
                tiles[row][column].setState(TileState.FINISH);
                break;
            case '#':
                tiles[row][column] = new Wall();
                tiles[row][column].setState(TileState.WALL);
                break;
            case ' ':
                tiles[row][column] = new Clue();
                tiles[row][column].setState(TileState.CLUE);
                break;
            case 'C':
                tiles[row][column] = new Coin();
                tiles[row][column].setState(TileState.COIN);
                break;
            //teleport
/*            case 'T':
                tiles[row][column] = new Teleport();
                tiles[row][column].setState(TileState.TELEPORT);
                break;*/
        }
    }

    public void moving(Direction direction) {
        try {
            movingDirection(direction);
        } catch (Exception e) {
            System.out.println();
        }
    }


    private void movingDirection(Direction direction) {
        switch (direction) {
            case UP:
                moveUp();
                coinUp();
                finishUp();
                break;
            case DOWN:
                moveDown();
                coinDown();
                finishDown();
                break;
            case LEFT:
                moveLeft();
                coinLeft();
                finishLeft();
                break;
            case RIGHT:
                moveRight();
                coinRight();
                finishRight();
                //teleport
//                teleportRight();
                break;
            default:
                break;
        }
    }

    public void restart() {
        if (level == 1) {
            tiles[player.getRow()][player.getColumn()] = new Clue();
            player.setColumn(0);
            player.setRow(0);
            tiles[player.getRow()][player.getColumn()] = player;
        } else if (level == 2) {
            tiles[player.getRow()][player.getColumn()] = new Clue();
            player.setColumn(0);
            player.setRow(3);
            tiles[player.getRow()][player.getColumn()] = player;
        } else {
            tiles[player.getRow()][player.getColumn()] = new Clue();
            player.setColumn(0);
            player.setRow(5);
            tiles[player.getRow()][player.getColumn()] = player;
        }
    }

    private void moveRight() {
        while (tiles[player.getRow()]
                [player.getColumn() + 1] instanceof Clue) {

            player.setColumn(player.getColumn() + 1);
            tiles[player.getRow()]
                    [player.getColumn()] = player;

            tiles[player.getRow()]
                    [player.getColumn() - 1] = new Clue();
        }
    }

    private void moveLeft() {
        while (tiles[player.getRow()]
                [player.getColumn() - 1] instanceof Clue) {

            player.setColumn(player.getColumn() - 1);
            tiles[player.getRow()]
                    [player.getColumn()] = player;

            tiles[player.getRow()]
                    [player.getColumn() + 1] = new Clue();
        }
    }

    private void moveDown() {
        while (tiles[player.getRow() + 1]
                [player.getColumn()] instanceof Clue) {

            player.setRow(player.getRow() + 1);
            tiles[player.getRow()]
                    [player.getColumn()] = player;

            tiles[player.getRow() - 1]
                    [player.getColumn()] = new Clue();
        }
    }

    private void moveUp() {
        while (tiles[player.getRow() - 1]
                [player.getColumn()] instanceof Clue) {

            player.setRow(player.getRow() - 1);
            tiles[player.getRow()]
                    [player.getColumn()] = player;

            tiles[player.getRow() + 1]
                    [player.getColumn()] = new Clue();
        }
    }

    public int count() {
        if (getState() == FieldState.PLAYING) {
            tiles[player.getRow()]
                    [player.getColumn()] = player;

            coin.countCoin();
            System.out.println(coin.getCount());
        }

        return coin.getCount();
    }

    public Coin getCoin() {
        return coin;
    }

    private void coinRight() {
        if (tiles[player.getRow()]
                [player.getColumn() + 1] instanceof Coin) {

            player.setColumn(player.getColumn() + 1);
            count();

            tiles[player.getRow()]
                    [player.getColumn() - 1] = new Clue();
        }
    }

    private void coinLeft() {
        if (tiles[player.getRow()]
                [player.getColumn() - 1] instanceof Coin) {

            player.setColumn(player.getColumn() - 1);
            count();

            tiles[player.getRow()]
                    [player.getColumn() + 1] = new Clue();
        }
    }

    private void coinDown() {
        if (tiles[player.getRow() + 1]
                [player.getColumn()] instanceof Coin) {

            player.setRow(player.getRow() + 1);
            count();

            tiles[player.getRow() - 1]
                    [player.getColumn()] = new Clue();
        }
    }

    private void coinUp() {
        if (tiles[player.getRow() - 1]
                [player.getColumn()] instanceof Coin) {

            player.setRow(player.getRow() - 1);
            count();

            tiles[player.getRow() + 1]
                    [player.getColumn()] = new Clue();
        }
    }

    //teleport
 /*   private void teleportRight() {
        if (getLevel() == 1) {
            if (tiles[player.getRow()][player.getColumn() + 1] instanceof Teleport) {
                tiles[player.getRow()][player.getColumn()] = new Clue();
                tiles[player.getRow()][player.getColumn() + 1] = new Clue();
                player.setColumn(6);
                player.setRow(0);
                tiles[player.getRow()][player.getColumn()] = player;
            }
        }
    }*/

    private void finishRight() {
        if (tiles[player.getRow()][player.getColumn() + 1] instanceof Finish) {
            player.setColumn(player.getColumn() + 1);
            tiles[player.getRow()][player.getColumn()] = player;
            tiles[player.getRow()][player.getColumn() - 1] = new Clue();
            winGame();
        }
    }

    private void finishLeft() {
        if (tiles[player.getRow()]
                [player.getColumn() - 1] instanceof Finish) {

            player.setColumn(player.getColumn() - 1);
            tiles[player.getRow()]
                    [player.getColumn()] = player;

            tiles[player.getRow()]
                    [player.getColumn() + 1] = new Clue();

            winGame();
        }
    }

    private void finishDown() {
        if (tiles[player.getRow() + 1]
                [player.getColumn()] instanceof Finish) {

            player.setRow(player.getRow() + 1);
            tiles[player.getRow()]
                    [player.getColumn()] = player;

            tiles[player.getRow() - 1]
                    [player.getColumn()] = new Clue();

            winGame();
        }
    }

    private void finishUp() {
        if (tiles[player.getRow() - 1]
                [player.getColumn()] instanceof Finish) {

            player.setRow(player.getRow() - 1);
            tiles[player.getRow()]
                    [player.getColumn()] = player;

            tiles[player.getRow() + 1]
                    [player.getColumn()] = new Clue();

            winGame();
        }
    }

    public void save() {
        try (var os =
                     new ObjectOutputStream(
                             new FileOutputStream(FILE))) {

            os.writeObject(this);
        } catch (IOException e) {
            throw new ScoreClientException(e);
        }
    }


    public static Field load() {
        try (var is =
                     new ObjectInputStream
                             (new FileInputStream(FILE))) {

            return (Field) is.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new ScoreClientException(e);
        }
    }

    public FieldState getState() {
        return state;
    }

    public Tile getTiles(int row,
                         int column) {
        return tiles[row][column];
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
