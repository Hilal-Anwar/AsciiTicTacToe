package org.tic_tac_toe.game.util;

public enum Players {
    BLACK, WHITE;

    public boolean isEqual(Box token) {
        if (token.getChessToken() == null)
            return false;
        else return token.getChessToken().getPiece().equals(this);
    }
}
