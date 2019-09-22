package com.game2048.logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Board {
    private static int SIZE_OF_BOARD = 3;
    private int[][] board = new int[SIZE_OF_BOARD][SIZE_OF_BOARD];
    private ArrayList<Field> boardEmptyField = new ArrayList<>();
    private GameMove gameMove = new GameMove();

    public Board() {
        initBoard();
    }

    int[][] getBoard() {
        return board;
    }

    void setBoard(int[][] board) {
        this.board = board;
    }

    public static int getSizeOfBoard() {
        return SIZE_OF_BOARD;
    }

    public static void setSizeOfBoard(int sizeOfBoard) {
        SIZE_OF_BOARD = sizeOfBoard;
    }

    private void initBoard() {
        for (int row = 0; row < SIZE_OF_BOARD; row++) {
            for (int col = 0; col < SIZE_OF_BOARD; col++) {
                board[row][col] = 0;
            }
        }
        setNewRandomNumberOnBoard();
        setNewRandomNumberOnBoard();
    }

    private void getBoardEmptyFields() {
        boardEmptyField.clear();
        for (int row = 0; row < SIZE_OF_BOARD; row++) {
            for (int col = 0; col < SIZE_OF_BOARD; col++) {
                if (board[row][col] == 0) {
                    boardEmptyField.add(new Field(row, col, board[row][col]));
                }
            }
        }
    }

    public int getBoardFieldNumber(int row, int col) {
        return board[row][col];
    }

    private int getRandomStartBoardValue() {
        Random random = new Random();
        int value = random.nextInt(100);

        if (value < 75) {
            return 2;
        } else {
            return 4;
        }
    }

    void setNewRandomNumberOnBoard() {
        Random random = new Random();
        getBoardEmptyFields();
        if (boardEmptyField.size() > 1) {
            int listNumber = random.nextInt(boardEmptyField.size() - 1);
            Field randomEmptyField = boardEmptyField.get(listNumber);
            board[randomEmptyField.getRow()][randomEmptyField.getCol()] = getRandomStartBoardValue();
        } else if (boardEmptyField.size() == 1) {
            board[boardEmptyField.get(0).getRow()][boardEmptyField.get(0).getCol()] = getRandomStartBoardValue();
        }
    }

    public void setBoardNumber(int row, int col, int number) {
        board[row][col] = number;
    }

    int[][] getBoardCopy(int[][] board) {
        return Arrays.stream(board).map(int[]::clone).toArray(int[][]::new);
    }

    private int getBoardSize(int[][] board) {
        int size = 0;
        for (int i = 0; i < board.length; i++)
            for (int j = 0; j < board[i].length; j++)
                size += board[i][j];
        return size;
    }

    public boolean isEndOfGame() {
        int[][] boardCopy = getBoardCopy(board);
        int size = getBoardSize(board);
        gameMove.move(Move.DOWN, this);
        if (size < getBoardSize(board)) {
            setBoard(boardCopy);
            return false;
        }
        gameMove.move(Move.UP, this);
        if (size < getBoardSize(board)) {
            setBoard(boardCopy);
            return false;
        }
        gameMove.move(Move.LEFT, this);
        if (size < getBoardSize(board)) {
            setBoard(boardCopy);
            return false;
        }
        gameMove.move(Move.RIGHT, this);
        if (size < getBoardSize(board)) {
            setBoard(boardCopy);
            return false;
        } else {
            System.out.println("End of the Game!");
            return true;
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (int row = 0; row < SIZE_OF_BOARD; row++) {
            for (int col = 0; col < SIZE_OF_BOARD; col++) {
                stringBuilder.append(board[row][col]).append("\t");
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}
