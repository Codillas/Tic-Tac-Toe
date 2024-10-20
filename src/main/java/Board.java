public class Board {

    private final int numCols;
    private final int numRows;

    private final char[][] board;

    public Board(int numRows, int numCols) {
        this.numRows = numRows;
        this.numCols = numCols;
        this.board = new char[numRows][numCols];

        // Initialize the board with empty cells.
        for (int rowIdx = 0; rowIdx < this.numRows; rowIdx++) {
            for (int colIdx = 0; colIdx < this.numCols; colIdx++) {
                board[rowIdx][colIdx] = ' ';
            }
        }
    }

    public void printBoard() {
        // For each row on the board, print the row with its cells and the row separation line.
        for (int rowIdx = 0; rowIdx < this.numRows; rowIdx++) {
            // Print the padded cell value with a column divider in between.
            for (int colIdx = 0; colIdx < this.numCols; colIdx++) {
                char cellValue = board[rowIdx][colIdx];

                System.out.print(" " + cellValue + " ");

                // Only print the column divider if colIdx is not the last one.
                if (colIdx < this.numCols - 1) {
                    System.out.print("|");
                }
            }
            System.out.println();

            // Only print the row separation line if rowIdx is not the last one.
            if (rowIdx < this.numRows - 1) {
                // Print the row separation line using the row divider, with the intersection divider in between.
                for (int colIdx = 0; colIdx < this.numCols; colIdx++) {
                    System.out.print("---");

                    // Only print the intersection divider if colIdx is not the last one.
                    if (colIdx < this.numCols - 1) {
                        System.out.print("+");
                    }
                }
                System.out.println();
            }
        }
    }
}
