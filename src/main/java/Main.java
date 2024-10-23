import board.Board;
import game.Game;
import player.Player;
import color.Color;

public class Main {
    public static void main(String[] args) {

        Player playerX = new Player("Player-X", Color.GREEN);
        Player playerO = new Player("Player-O", Color.BLUE);

        Board board = new Board(3, 3, 3);

        Game game = new Game(playerX, playerO, board);

        game.start();
    }
}
