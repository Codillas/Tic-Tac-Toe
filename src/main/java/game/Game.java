package game;

import board.Board;
import player.Player;

import java.util.Scanner;

public class Game {

    private final Player playerX;
    private final Player playerO;
    private final Board board;
    private Player currentPlayer;

    public Game(Player playerX, Player playerO, Board board) {
        this.playerX = playerX;
        this.playerO = playerO;
        this.board = board;

        this.currentPlayer = playerX;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        boolean isGameOver = false;

        System.out.println();
        System.out.println("Game on!");
        System.out.println();

        while (!isGameOver) {
            System.out.println("Current board:");

            System.out.println();
            board.printBoard();
            System.out.println();

            System.out.printf("%s's turn. Enter row and column (e.g., 1 1): ", currentPlayer.getName());
            int row = scanner.nextInt();
            int col = scanner.nextInt();

            System.out.println();

            // Validate move
            if (!board.isValidMove(row, col)) {
                System.out.println("Invalid move! Try again.");
                System.out.println();
                continue;
            }

            if (currentPlayer == playerX) {
                board.makeMoveX(row, col);
            } else {
                board.makeMoveO(row, col);
            }

            // Check game over conditions
            if (board.hasWinner()) {
                System.out.println(currentPlayer.getName() + " wins!");
                System.out.println();

                board.printBoard();

                isGameOver = true;
            } else if (board.isFull()) {
                System.out.println("It's a draw!");
                System.out.println();

                board.printBoard();

                isGameOver = true;
            } else {
                // Switch players
                currentPlayer = (currentPlayer == playerX) ? playerO : playerX;
            }

            if (isGameOver) {
                board.hasWinner();
            }
        }
        scanner.close();
    }

}
