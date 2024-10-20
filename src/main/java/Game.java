public class Game {
    public static void main(String[] args) {
        System.out.println();
        System.out.println("Welcome to Tic-Tac-Toe Game!");
        System.out.println();

        Board board = new Board(3, 5);

        board.printBoard();
    }
}
