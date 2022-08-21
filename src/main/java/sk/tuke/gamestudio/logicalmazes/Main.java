package sk.tuke.gamestudio.logicalmazes;

import sk.tuke.gamestudio.logicalmazes.consoleui.ConsoleUI;
import sk.tuke.gamestudio.logicalmazes.core.Field;

public class Main {
    public static void main(String[] args) {
        Field field = new Field(9, 9);
        ConsoleUI ui = new ConsoleUI(field);
        ui.menu();
    }
}