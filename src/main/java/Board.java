public class Board {

    private static final String COLUMN_DIVIDER = "|";
    private static final String ROW_DIVIDER = "-";
    private static final String INTERSECTION_DIVIDER = "+";
    private static final String CELL_PADDING = " ";

    private static final char EMPTY_CELL_CHAR = ' ';

    private static final int CELL_LEN = (CELL_PADDING + EMPTY_CELL_CHAR + CELL_PADDING).length();

    private final int numCols;
    private final int numRows;

    private final char[][] board;

    public Board(int numRows, int numCols) {
        this.numRows = numRows;
        this.numCols = numCols;
        this.board = new char[numRows][numCols];

        initBoard();
    }

    private void initBoard() {
        for (int rowIdx = 0; rowIdx < this.numRows; rowIdx++) {
            for (int colIdx = 0; colIdx < this.numCols; colIdx++) {
                board[rowIdx][colIdx] = EMPTY_CELL_CHAR;
            }
        }
    }

    public void printBoard() {
        for (int rowIdx = 0; rowIdx < this.numRows; rowIdx++) {
            printRow(rowIdx);

            if (isNotLastRow(rowIdx)) {
                printRowSeparationLine();
            }
        }
    }

    private void printRow(int rowIdx) {
        for (int colIdx = 0; colIdx < this.numCols; colIdx++) {
            char cellValue = board[rowIdx][colIdx];

            System.out.print(CELL_PADDING + cellValue + CELL_PADDING);

            if (isNotLastColumn(colIdx)) {
                System.out.print(COLUMN_DIVIDER);
            }
        }
        System.out.println();
    }

    private void printRowSeparationLine() {
        for (int colIdx = 0; colIdx < this.numCols; colIdx++) {
            for (int i = 0; i < CELL_LEN; i++) {
                System.out.print(ROW_DIVIDER);
            }

            if (isNotLastColumn(colIdx)) {
                System.out.print(INTERSECTION_DIVIDER);
            }
        }
        System.out.println();
    }

    private boolean isNotLastRow(int rowIdx) {
        return rowIdx < this.numRows - 1;
    }

    private boolean isNotLastColumn(int colIdx) {
        return colIdx < this.numCols - 1;
    }
}
