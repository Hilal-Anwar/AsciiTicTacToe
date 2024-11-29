package org.tic_tac_toe.game.util;

public class Box {
    private ChessToken chessToken;
    private boolean selected;
    private Colors colors = Colors.WHITE;

    public Box(ChessToken chessToken, boolean selected) {
        this.chessToken = chessToken;
        this.selected = selected;
    }

    public ChessToken getChessToken() {
        return chessToken;
    }

    public void setChessToken(ChessToken chessToken) {
        this.chessToken = chessToken;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected, Colors colors) {
        this.selected = selected;
        this.colors = colors;
    }

    @Override
    public String toString() {
        return "Box{" +
                "chessToken=" + chessToken +
                ", selected=" + selected +
                ", colors=" + colors +
                '}';
    }

    public Colors getColors() {
        return colors;
    }
}
