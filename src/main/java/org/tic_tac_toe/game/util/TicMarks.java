package org.tic_tac_toe.game.util;

public enum TicMarks {
    Cross("X"), Zero("O");

    private final String text;

    TicMarks(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
