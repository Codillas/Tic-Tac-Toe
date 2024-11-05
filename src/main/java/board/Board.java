package board;

import color.Color;
import printer.Printer;

public class Board {

    // =================================================================================================================

    // Board building blocks
    private static final String COLUMN_DIVIDER = "|";
    private static final String ROW_DIVIDER = "-";
    private static final String INTERSECTION_DIVIDER = "+";
    private static final String CELL_PADDING = " ";

    // Cell values
    private static final char EMPTY_CELL_CHAR = ' ';
    private static final char X_CELL_CHAR = 'X';
    private static final char O_CELL_CHAR = 'O';
    private static final char HEADER_INTERSECTION_CELL_CHAR = '∅';

    private static final int CELL_LEN = (CELL_PADDING + EMPTY_CELL_CHAR + CELL_PADDING).length();

    // Colors
    private static final Color X_COLOR = Color.GREEN;
    private static final Color O_COLOR = Color.BLUE;
    private static final Color HEADER_COLOR = Color.MAGENTA;
    private static final Color WIN_COLOR = Color.RED;
    private static final Color DEFAULT_COLOR = Color.DEFAULT;

    // Radix
    private static final int DECIMAL_RADIX = 10;

    // =================================================================================================================

    private final int numCols;
    private final int numRows;
    private final int winLength;

    private final char[][] board;
    private final int[][] winLineCoordinates;

    public Board(int numRows, int numCols) {
        this(numRows, numCols, 3);
    }

    public Board(int numRows, int numCols, int winLength) {
        this.numRows = numRows;
        this.numCols = numCols;
        this.winLength = winLength;

        this.winLineCoordinates = new int[winLength][2];

        this.board = new char[numRows][numCols];

        init();
    }

    private void init() {
        for (int rowIdx = 0; rowIdx < this.numRows; rowIdx++) {
            for (int colIdx = 0; colIdx < this.numCols; colIdx++) {
                board[rowIdx][colIdx] = EMPTY_CELL_CHAR;
            }
        }
    }

    public void print() {
        printRowHeader();
        for (int rowIdx = 0; rowIdx < this.numRows; rowIdx++) {
            printRow(rowIdx);

            if (isNotLastRow(rowIdx)) {
                printRowSeparationLine();
            }
        }
    }

    public void makeMoveX(int rowIdx, int colIdx) {
        board[rowIdx][colIdx] = X_CELL_CHAR;
    }

    public void makeMoveO(int rowIdx, int colIdx) {
        board[rowIdx][colIdx] = O_CELL_CHAR;
    }

    public boolean isInvalidMove(int rowIdx, int colIdx) {
        if (rowIdx < 0 || colIdx < 0 && rowIdx >= numRows || colIdx >= numCols) {
            return true;
        }

        return board[rowIdx][colIdx] != EMPTY_CELL_CHAR;
    }

    public boolean isFull() {
        for (int rowIdx = 0; rowIdx < this.numRows; rowIdx++) {
            for (int colIdx = 0; colIdx < this.numCols; colIdx++) {
                if (board[rowIdx][colIdx] == EMPTY_CELL_CHAR) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean hasWinner() {
        // Check rows
        for (int rowIdx = 0; rowIdx < numRows; rowIdx++) {
            for (int colIdx = 0; colIdx <= numCols - winLength; colIdx++) {
                char cellValue = board[rowIdx][colIdx];

                if (cellValue == EMPTY_CELL_CHAR) {
                    continue;
                }

                if (lineHasWinner(rowIdx, colIdx, 0, 1)) {
                    return true;
                }
            }
        }

        // Check columns
        for (int colIdx = 0; colIdx < numCols; colIdx++) {
            for (int rowIdx = 0; rowIdx <= numRows - winLength; rowIdx++) {
                char cellValue = board[rowIdx][colIdx];

                if (cellValue == EMPTY_CELL_CHAR) {
                    continue;
                }

                if (lineHasWinner(rowIdx, colIdx, 1, 0)) {
                    return true;
                }
            }
        }

        // Check diagonals (top-left to bottom-right)
        for (int rowIdx = 0; rowIdx <= numRows - winLength; rowIdx++) {
            for (int colIdx = 0; colIdx <= numCols - winLength; colIdx++) {
                char cellValue = board[rowIdx][colIdx];

                if (cellValue == EMPTY_CELL_CHAR) {
                    continue;
                }

                if (lineHasWinner(rowIdx, colIdx, 1, 1)) {
                    return true;
                }
            }
        }

        // Check diagonals (bottom-left to top-right)
        for (int rowIdx = winLength - 1; rowIdx < numRows; rowIdx++) {
            for (int colIdx = 0; colIdx <= numCols - winLength; colIdx++) {
                char cellValue = board[rowIdx][colIdx];

                if (cellValue == EMPTY_CELL_CHAR) {
                    continue;
                }

                if (lineHasWinner(rowIdx, colIdx, -1, 1)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean lineHasWinner(int startRowIdx, int startColIdx, int rowDirection, int colDirection) {
        char firstCellValue = board[startRowIdx][startColIdx];

        winLineCoordinates[0][0] = startRowIdx;
        winLineCoordinates[0][1] = startColIdx;

        // Loop through the required number of consecutive positions
        for (int i = 1; i < winLength; i++) {
            // Calculate the next position in the given direction
            int newRow = startRowIdx + i * rowDirection;
            int newCol = startColIdx + i * colDirection;

            winLineCoordinates[i][0] = newRow;
            winLineCoordinates[i][1] = newCol;

            // Check if the character in the new position matches the first character
            if (board[newRow][newCol] != firstCellValue) {
                return false; // Break the line if any character doesn't match
            }
        }
        return true; // All characters matched, so the line is valid
    }

    // =================================================================================================================

    private void printRowHeader() {

        printCell(HEADER_INTERSECTION_CELL_CHAR, HEADER_COLOR, HEADER_COLOR, true);

        for (int colIdx = 0; colIdx < numCols; colIdx++) {
            char cellValue = Character.forDigit(colIdx, DECIMAL_RADIX);
            boolean printWithDivider = isNotLastColumn(colIdx);

            printCell(cellValue, HEADER_COLOR, HEADER_COLOR, printWithDivider);
        }

        Printer.println();

        printRowSeparationLine(HEADER_COLOR);
    }

    private void printRow(int rowIdx) {

        char colHeaderCellValue = Character.forDigit(rowIdx, DECIMAL_RADIX);
        printCell(colHeaderCellValue, HEADER_COLOR, HEADER_COLOR, true);

        for (int colIdx = 0; colIdx < this.numCols; colIdx++) {
            boolean printWithDivider = isNotLastColumn(colIdx);

            printCell(rowIdx, colIdx, printWithDivider);
        }

        Printer.println();
    }

    // =================================================================================================================

    private void printCell(int rowIdx, int colIdx, boolean printWithDivider) {
        char cellValue = board[rowIdx][colIdx];

        if (hasWinner()) {
            for (int[] winLineCoordinate : winLineCoordinates) {
                if (winLineCoordinate[0] == rowIdx && winLineCoordinate[1] == colIdx) {
                    printCell(cellValue, WIN_COLOR, DEFAULT_COLOR, printWithDivider);
                    return;
                }
            }
        }

        switch (cellValue) {
            case X_CELL_CHAR -> printCell(cellValue, X_COLOR, DEFAULT_COLOR, printWithDivider);
            case O_CELL_CHAR -> printCell(cellValue, O_COLOR, DEFAULT_COLOR, printWithDivider);
            default -> printCell(cellValue, DEFAULT_COLOR, DEFAULT_COLOR, printWithDivider);
        }
    }

    private void printCell(char cellValue, Color cellValueColor, Color dividerColor, boolean printWithDivider) {
        Printer.print(CELL_PADDING + cellValue + CELL_PADDING, cellValueColor);

        // Conditionally add column divider
        if (printWithDivider) {
            Printer.print(COLUMN_DIVIDER, dividerColor);
        }
    }

    // =================================================================================================================

    private void printRowSeparationCellBlock(Color color, boolean printWithDivider) {
        for (int i = 0; i < CELL_LEN; i++) {
            Printer.print(ROW_DIVIDER, color);
        }

        if (printWithDivider) {
            Printer.print(INTERSECTION_DIVIDER, color);
        }
    }

    private void printRowSeparationLine() {
        printRowSeparationLine(DEFAULT_COLOR);
    }

    private void printRowSeparationLine(Color color) {
        printRowSeparationCellBlock(HEADER_COLOR, true);

        for (int colIdx = 0; colIdx < this.numCols; colIdx++) {
            boolean printWithDivider = isNotLastColumn(colIdx);
            printRowSeparationCellBlock(color, printWithDivider);
        }

        Printer.println();
    }

    // =================================================================================================================

    private boolean isNotLastRow(int rowIdx) {
        return rowIdx < this.numRows - 1;
    }

    private boolean isNotLastColumn(int colIdx) {
        return colIdx < this.numCols - 1;
    }
}
