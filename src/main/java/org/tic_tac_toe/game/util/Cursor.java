package org.tic_tac_toe.game.util;

public class Cursor {
    private int column;


    private int row;
    private final Colors colors;

    public Cursor(int column, int row, Colors colors) {
        this.column = column;
        this.row = row;
        this.colors = colors;
    }

    public void move_cursor_up() {
        row = fix_row(row - 1);
    }

    public void move_cursor_down() {
        row = fix_row(row + 1);
    }

    public void move_cursor_right() {
        column = fix_column(column + 1);
    }

    public void move_cursor_left() {
        column = fix_column(column - 1);
    }

    private int fix_column(int x) {
        return x > 2 ? 0 : x < 0 ? 2 : x;
    }

    private int fix_row(int y) {
        return y > 2 ? 0 : y < 0 ? 2 : y;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    public Colors getColors() {
        return colors;
    }
}
