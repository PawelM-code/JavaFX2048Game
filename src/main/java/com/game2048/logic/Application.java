package com.game2048.logic;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        Board board = new Board();
        GameMove gameMove = new GameMove();
        Scanner scanner = new Scanner(System.in);

        System.out.println(board);

        while (!board.isEndOfGame()) {
            String move = scanner.nextLine();
            if (move.equals("w")) {
                gameMove.move(Move.UP, board);
            }
            if (move.equals("s")) {
                gameMove.move(Move.DOWN, board);
            }
            if (move.equals("a")) {
                gameMove.move(Move.LEFT, board);
            }
            if (move.equals("d")) {
                gameMove.move(Move.RIGHT, board);
            }
            System.out.println(board);
        }
//        board.setBoardNumber(0, 0, 2);
//        board.setBoardNumber(0, 1, 2);
//        board.setBoardNumber(0, 2, 4);
//        board.setBoardNumber(1, 2, 8);
//        System.out.println(board);
//        gameMove.move(Move.RIGHT, board);
//        gameMove.move(Move.RIGHT, board);
//        gameMove.move(Move.DOWN, board);
//        board.setBoardNumber(1, 2, 16);
//        board.setBoardNumber(0, 1, 32);
//        System.out.println(board);
//        gameMove.move(Move.UP, board);
//        gameMove.move(Move.LEFT, board);
//        System.out.println(board);
//        board.setNewRandomNumberOnBoard();
//        System.out.println(board);
//        gameMove.move(Move.UP,board);
//        System.out.println(board);
//        gameMove.move(Move.LEFT, board);
//        System.out.println(board);
//        board.setNewRandomNumberOnBoard();
//        System.out.println(board);
//        gameMove.move(Move.RIGHT, board);
//        System.out.println(board);
    }
}
