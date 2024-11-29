package org.tic_tac_toe.game.util;


public class ChessToken {
    private final TicMarks ticMarks;
    private Players players;
    private Colors color;
    private int noOfMoves;

    public int getNoOfMoves() {
        return noOfMoves;
    }

    public void setNoOfMoves(int noOfMoves) {
        this.noOfMoves = noOfMoves;
    }

    public TicMarks getChessPieceType() {
        return ticMarks;
    }


    public Players getPiece() {
        return players;
    }

    public void setPiece(Players players) {
        this.players = players;
    }

    public ChessToken(TicMarks ticMarks, Colors color, Players players) {
        this.ticMarks = ticMarks;
        this.color = color;
        this.players = players;
    }

    public Colors getColor() {
        return color;
    }

    public void setColor(Colors color) {
        this.color = color;
    }

    public String getText() {
        return " " + Text.getColorText(this.ticMarks.getText(), color) + " ";
    }
}
