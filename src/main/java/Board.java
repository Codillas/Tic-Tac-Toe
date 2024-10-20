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
        StringBuilder boardBuilder = new StringBuilder();

        String rowSeparationLine = getRowDividerLine();

        for (char[] row : board) {
            boardBuilder
                    .append(getRowLine(row))
                    .append(rowSeparationLine);
        }

        boardBuilder.setLength(
                boardBuilder.length() - rowSeparationLine.length()
        );

        System.out.println(boardBuilder);
    }

    private String getRowLine(char[] row) {
        StringBuilder rowBuilder = new StringBuilder();

        for (char cell : row) {
            String paddedCell = CELL_PADDING + cell + CELL_PADDING;

            rowBuilder
                    .append(paddedCell)
                    .append(COLUMN_DIVIDER);
        }

        rowBuilder.setLength(
                rowBuilder.length() - COLUMN_DIVIDER.length()
        );

        return rowBuilder + "\n";
    }

    private String getRowDividerLine() {

        StringBuilder rowSeparationLineBuilder = new StringBuilder();

        rowSeparationLineBuilder
                .append(
                        (ROW_DIVIDER.repeat(CELL_LEN) + INTERSECTION_DIVIDER).repeat(this.numCols)
                )
                .setLength(
                        rowSeparationLineBuilder.length() - INTERSECTION_DIVIDER.length()
                );

        return rowSeparationLineBuilder + "\n";
    }
}
