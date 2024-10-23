import board.Board;
import game.Game;
import player.Player;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println();
        System.out.println("Welcome to Tic-Tac-Toe ULTRA!");
        System.out.println();

        Scanner scanner = new Scanner(System.in);

        // Ask the user for the grid size (m x n)
        System.out.print("Enter the number of rows (m): ");
        int numRows = scanner.nextInt();

        System.out.print("Enter the number of columns (n): ");
        int numCols = scanner.nextInt();

        // Ask the user for the winning length (k)
        System.out.print("Enter the number of consecutive symbols needed to win (k): ");
        int winLength = scanner.nextInt();

        // Ensure that the winning length is valid for the given grid
        if (winLength > numRows && winLength > numCols) {
            System.out.println("Error: The winning length (k) cannot be greater than both rows (m) and columns (n).");
            System.exit(1);
        }

        Board board = new Board(numRows, numCols, winLength);

        Player playerX = new Player("Player-X");
        Player playerO = new Player("Player-O");

        Game game = new Game(playerX, playerO, board);

        game.start();
    }
}
