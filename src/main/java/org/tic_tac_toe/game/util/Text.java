package org.tic_tac_toe.game.util;

class Text {
    public static String getColorText(String text, Colors colors) {
        return colors.getColor() + text + "\33[0m";
    }
}
