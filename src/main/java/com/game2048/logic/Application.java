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
    }
}
