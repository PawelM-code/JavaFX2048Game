package com.game2048.logic;

public class Field {
    private int row;
    private int col;
    private int number;

    Field(int row, int col, int number) {
        this.row = row;
        this.col = col;
        this.number = number;
    }

    int getRow() {
        return row;
    }

    int getCol() {
        return col;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
