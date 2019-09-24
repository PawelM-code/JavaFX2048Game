package com.game2048.logic;

import java.util.Arrays;

import static com.game2048.logic.Board.getSizeOfBoard;

public class GameMove {

    public void move(Move move, Board board) {
        if (Move.UP.equals(move)) {
            int[][] boardState = board.getBoardCopy(board.getBoard());
            board.setBoard(MoveUp(board.getBoard()));
            if (!Arrays.deepEquals(boardState, board.getBoard())) {
                board.setNewRandomNumberOnBoard();
            }
        } else if (Move.DOWN.equals(move)) {
            int[][] boardState = board.getBoardCopy(board.getBoard());
            board.setBoard(MoveDown(board.getBoard()));
            if (!Arrays.deepEquals(boardState, board.getBoard())) {
                board.setNewRandomNumberOnBoard();
            }
        } else if (Move.LEFT.equals(move)) {
            int[][] boardState = board.getBoardCopy(board.getBoard());
            MoveLeft(board.getBoard());
            if (!Arrays.deepEquals(boardState, board.getBoard())) {
                board.setNewRandomNumberOnBoard();
            }
        } else if (Move.RIGHT.equals(move)) {
            int[][] boardState = board.getBoardCopy(board.getBoard());
            MoveRight(board.getBoard());
            if (!Arrays.deepEquals(boardState, board.getBoard())) {
                board.setNewRandomNumberOnBoard();
            }
        }
    }

    private int[][] MoveUp(int[][] arrays) {
        int[][] boardTranspose = getArrayTransposition(arrays);
        MoveLeft(boardTranspose);
        return getArrayTransposition(boardTranspose);
    }

    private int[][] MoveDown(int[][] arrays) {
        int[][] boardTranspose = getArrayTransposition(arrays);
        MoveRight(boardTranspose);
        return getArrayTransposition(boardTranspose);
    }

    private static void MoveLeft(int[][] arrays) {
        int currentCol = -1;
        for (int[] row : arrays) {
            for (int col = 0; col < getSizeOfBoard(); col++) {
                currentCol = getMargeNumber(currentCol, row, col);
            }
            currentCol = -1;
            for (int i = 0; i < getSizeOfBoard() * getSizeOfBoard(); i++) {
                int y = i % getSizeOfBoard();

                if (y == getSizeOfBoard() - 1) continue;
                if (row[y] == 0 && row[y + 1] != 0) // current is empty and next is not
                {
                    row[y] = row[y + 1]; // move next to current
                    row[y + 1] = 0;
                }
            }

        }
    }

    private static void MoveRight(int[][] arrays) {
        int currentCol = -1;
        for (int[] row : arrays) {
            for (int col = getSizeOfBoard() - 1; col >= 0; col--) {
                currentCol = getMargeNumber(currentCol, row, col);
            }
            currentCol = -1;
            for (int i = getSizeOfBoard() * getSizeOfBoard() - 1; i >= 0; i--) {
                int y = i % getSizeOfBoard();

                if (y == 0) continue;
                if (row[y] == 0 && row[y - 1] != 0) {
                    row[y] = row[y - 1];
                    row[y - 1] = 0;
                }
            }
        }
    }

    private static int getMargeNumber(int currentCol, int[] row, int col) {
        if (row[col] == 0)
            return currentCol;
        if (currentCol == -1) {
            currentCol = col;
            return currentCol;
        }
        if (row[currentCol] != row[col]) {
            currentCol = col;
            return currentCol;
        }
        if (row[currentCol] == row[col]) {
            row[currentCol] += row[col];
            row[col] = 0;
            currentCol = -1;
        }
        return currentCol;
    }

    private int[][] getArrayTransposition(int[][] array) {
        if (array == null || array.length == 0)
            return array;

        int width = array.length;
        int height = array[0].length;

        int[][] array_new = new int[height][width];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                array_new[y][x] = array[x][y];
            }
        }
        return array_new;
    }
}
