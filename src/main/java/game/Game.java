package game;

import board.Board;
import player.Player;
import color.Color;
import printer.Printer;

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

        while (!isGameOver) {
            Printer.cls();
            board.printBoard();
            Printer.println();

            int rowIdx;
            int colIdx;

            // Read player's move and check whether the move is valid
            do {
                Printer.print(currentPlayer.getName(), currentPlayer.getColor());
                Printer.print("'s turn. Enter row and column (e.g., 0 0): ");

                rowIdx = scanner.nextInt();
                colIdx = scanner.nextInt();

                Printer.println();

                if (board.isInvalidMove(rowIdx, colIdx)) {
                    Printer.println("Invalid move!", Color.RED);
                    Printer.println();
                }
            } while (board.isInvalidMove(rowIdx, colIdx));

            if (currentPlayer == playerX) {
                board.makeMoveX(rowIdx, colIdx);
            } else {
                board.makeMoveO(rowIdx, colIdx);
            }

            // Check game over conditions
            if (board.hasWinner()) {
                Printer.cls();
                board.printBoard();
                Printer.println();

                Printer.print(currentPlayer.getName(), currentPlayer.getColor());
                Printer.println(" wins!");

                isGameOver = true;
            } else if (board.isFull()) {
                Printer.cls();
                board.printBoard();
                Printer.println();

                Printer.println("It is a draw!", Color.YELLOW);

                isGameOver = true;
            } else {
                // Switch players
                currentPlayer = (currentPlayer == playerX) ? playerO : playerX;
            }
        }
        scanner.close();
    }
}
