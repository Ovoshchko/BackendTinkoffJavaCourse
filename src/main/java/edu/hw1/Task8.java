package edu.hw1;

public final class Task8 {

    private static final int BOARD_SIZE = 8;
    private static final byte[][] DIRECTIVES = {{1, -2}, {2, -1}, {1, 2}, {2, 1}};

    private Task8() {}

    public static boolean knightBoardCapture(int[][] board) throws Exception {

        if (isNotValid(board)) {
            throw new Exception("Invalid input"); //просто вернуть false как-будто не совсем корректно
        }

        for (int x = 0; x < BOARD_SIZE; x++) {
            for (int y = 0; y < BOARD_SIZE; y++) {

                if (board[x][y] == 1) {
                    if (canCapture(board, x, y)) {
                        return false;
                    }
                }

            }
        }

        return true;
    }

    private static boolean isNotValid(int[][] board) {

        if (board == null) {
            return true;
        }

        if (board.length != BOARD_SIZE) {
            return true;
        }

        for (int i = 0; i < BOARD_SIZE; i++) {

            if (board[i].length != BOARD_SIZE) {
                return true;
            }

        }

        return false;
    }

    private static boolean canCapture(int[][] board, int x, int y) {
        int xIndex;
        int yIndex;

        for (byte[] derivative: DIRECTIVES) {
            xIndex = x + derivative[0];
            yIndex = y + derivative[1];

            if (!((xIndex < 0) || (xIndex >= BOARD_SIZE) || (yIndex < 0) || (yIndex >= BOARD_SIZE))) {
                if (board[xIndex][yIndex] == 1) {
                    return true;
                }
            }
        }

        return false;
    }

}
