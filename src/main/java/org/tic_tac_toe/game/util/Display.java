package org.tic_tac_toe.game.util;

import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

import java.io.IOException;

public class Display {
    public Terminal terminal;
    protected Display(){
        try {
            terminal = TerminalBuilder.terminal();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    protected void clear_display()
    {
        System.out.println("\033[H\033[J");
    }
}
