import board.Board;
import game.Game;
import player.Player;
import color.Color;

public class Main {
    public static void main(String[] args) {

        Board board1 = new Board(3, 3);

        board1.print();

        Board board2 = new Board(5, 2);

        board2.print();

    }
}
