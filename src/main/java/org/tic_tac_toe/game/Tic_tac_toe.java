package org.tic_tac_toe.game;

import org.tic_tac_toe.game.util.*;

import java.util.Objects;
import java.util.Scanner;

class Tic_tac_toe extends Display implements buttons {
    int count = 0;
    int col, row;
    String[][] Box = new String[7][16];
    String val = "X";
    Box board[][];
    String gameStatus = "running";
    String message = "";

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Welcome to tic tac toe");
        Tic_tac_toe game = new Tic_tac_toe();
        game._start();
    }

    public void _start() throws InterruptedException {
        Board bo = new Board(new Cursor(1, 1, Colors.ORANGE));
        board = bo.getChessBoard();
        for (int i = 0; i <= 2; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = new Box(null, false);
            }
        }
        String w = option();
        box();
        KeyBoardInput keyBoardInput = new KeyBoardInput(this);
        clear_display();
        bo.draw(message, terminal.getWidth(), terminal.getHeight());
        while (true) {
            var key = keyBoardInput.getKeyBoardKey();
            switch (key) {
                case UP -> bo.move_cursor_up();
                case DOWN -> bo.move_cursor_down();
                case RIGHT -> bo.move_cursor_right();
                case LEFT -> bo.move_cursor_left();
                case ENTER -> {
                    row = bo.getRow();
                    col = bo.getColumn();
                    if (Box[y[row]][x[col]].equals(" ")) {
                        Box[y[row]][x[col]] = val;
                        start(w);
                        if (Objects.equals(val, "X"))
                            board[row][col].setChessToken(new
                                    ChessToken(TicMarks.Cross, Colors.GREEN, Players.BLACK));
                        DeviceTurn(y[row], x[col]);
                        int k = check();
                        if (k == 3 && count == 9) {
                            message = "The game is draw";
                            clear_display();
                            bo.draw(message, terminal.getWidth(), terminal.getHeight());
                            thank();
                            gameStatus = "stopped";
                        }
                        if (k == 1) {
                            if (w.equals("Computer"))
                                message = "You lose";
                            else
                                message = w + " player " + "won";
                            clear_display();
                            bo.draw(message, terminal.getWidth(), terminal.getHeight());
                            thank();
                            gameStatus = "stopped";
                        }
                        if (k == 2) {
                            if (w.equals("Computer"))
                                System.out.println("You won");
                            else message = w + " player " + "won";
                            clear_display();
                            bo.draw(message, terminal.getWidth(), terminal.getHeight());
                            thank();
                            gameStatus = "stopped";
                        }
                    } else
                        message = "Invalid box";
                }
                case ESC -> System.exit(-1);
            }
            if (!key.equals(Key.NONE)) {
                if (gameStatus.equals("running")) {
                    clear_display();
                    bo.draw(message, terminal.getWidth(), terminal.getHeight());
                } else System.exit(-1);
                //System.out.println(memory);
            }
            keyBoardInput.setKeyBoardKey(Key.NONE);
            Thread.sleep(30);
        }

    }

    void start(String w) {
        if (w.equals("Computer")) {
            move(w);
        } else
            move(w);
    }

    void DeviceTurn(int b, int a) {
        int z = 0;
        boolean condition = checkWinO();
        if (!condition) {
            z = Defence(b, a);
            if (z == 0) {
                count++;
            }
        }
        if (z == 1 && count < 8)
            action(b, a);
    }

    private void action(int n, int m) {
        count++;
        int q;
        int algorithm = (int) (Math.random() * 2 + 1);
        if (((n == y[1] && m == x[2]) || (n == y[2] && m == x[1])) && Box[y[0]][x[0]].equals(" ")) {
            Box[y[0]][x[0]] = "0";
            dumAction();
        } else if ((algorithm == 1) && ((n != y[1] && m != x[2]) ||
                (n != y[2] && m != x[1])) && Box[y[1]][x[1]].equals(" ") && count <= 2) {
            Box[y[1]][x[1]] = "0";
            dumAction();
        } else if ((algorithm == 2) && (Box[y[1]][x[1]].equals(" "))) {
            Box[y[1]][x[1]] = "0";
            dumAction();
        } else if ((Box[y[0]][x[0]].equals(" ") || Box[y[0]][x[2]].equals(" ") ||
                Box[y[2]][x[0]].equals(" ") || Box[y[2]][x[2]].equals(" ")) &&
                Box[y[1]][x[1]].equals("X")) {
            while (true) {
                q = (int) (Math.random() * 4);
                int[] but = c[q];
                if (Box[but[0]][but[1]].equals(" ")) {
                    Box[but[0]][but[1]] = "0";
                    dumAction();
                    break;
                }
            }
        } else if (Box[y[0]][x[1]].equals(" ") || Box[y[1]][x[0]].equals(" ") ||
                Box[y[1]][x[2]].equals(" ") || Box[y[2]][x[1]].equals(" ")) {
            while (true) {
                q = (int) (Math.random() * 4);
                int[] but = b[q];
                if (Box[but[0]][but[1]].equals(" ")) {
                    Box[but[0]][but[1]] = "0";
                    dumAction();
                    break;
                }
            }
        } else {
            while (true) {
                q = (int) (Math.random() * 9);
                int[] but = a[q];
                if (Box[but[0]][but[1]].equals(" ")) {
                    Box[but[0]][but[1]] = "0";
                    dumAction();
                    break;
                }
            }
        }
    }

    private int Defence(int b, int a) {
        //button1
        if (b == y[0] && a == x[0]) {
            return computeAction(0, 3);
        }
        //button2
        if (b == y[0] && a == x[1]) {
            return computeAction(1, 2);
        }
        //button3
        if (b == y[0] && a == x[2]) {
            return computeAction(2, 3);
        }
        //button4
        if (b == y[1] && a == x[0]) {
            return computeAction(3, 2);
        }
        //button5
        if (b == y[1] && a == x[1]) {
            return computeAction(4, 4);
        }
        //button6
        if (b == y[1] && a == x[2]) {
            return computeAction(5, 2);
        }
        //button7
        if (b == y[2] && a == x[0]) {
            return computeAction(6, 3);
        }
        //button8
        if (b == y[2] && a == x[1]) {
            return computeAction(7, 2);
        }
        //button9
        if (b == y[2] && a == x[2]) {
            return computeAction(8, 3);
        }
        return 1;
    }

    private int computeAction(int i, int size) {
        for (int j = 0; j < size; j++) {
            if (Box[Defender[i][j][0][0]][Defender[i][j][0][1]].equals("X")) {
                if (Box[Defender[i][j][1][0]][Defender[i][j][1][1]].equals(" ")) {
                    Box[Defender[i][j][1][0]][Defender[i][j][1][1]] = "0";
                    dumAction();
                    return 0;
                }
            }
            if (Box[Defender[i][j][1][0]][Defender[i][j][1][1]].equals("X")) {
                if (Box[Defender[i][j][0][0]][Defender[i][j][0][1]].equals(" ")) {
                    Box[Defender[i][j][0][0]][Defender[i][j][0][1]] = "0";
                    dumAction();
                    return 0;
                }
            }
        }
        return 1;
    }

    String option() {
        Scanner in = new Scanner(System.in);
        System.out.println("Choose your opponent");
        System.out.println("Opponent option");
        System.out.println("Enter 1 for computer");
        System.out.println("Enter 2 for other");
        int c = in.nextInt();
        while (opt(c).equals("Invalid")) {
            System.out.println(opt(c) + " Option ");
            System.out.println("Please re-enter your choice");
            c = in.nextInt();
        }
        return opt(c);
    }

    private String opt(int x) {
        return switch (x) {
            case 1 -> "Computer";
            case 2 -> "Other";
            default -> "Invalid";
        };
    }

    void move(String name) {
        //Scanner in = new Scanner(System.in);
        count++;
        if (name.equals("Computer")) {
            //System.out.println("Your turn");
            val = "X";
        } else {
            if (val.equals("0")) {
                //System.out.println(name + " turn");
                val = "X";
            } else {
                System.out.println("Your turn");
                val = "0";
            }
        }

        /*int v = 1;
        while (v == 1) {
            while (true) {
                System.out.println("Enter the row ,Number ");
                row = Integer.parseInt(in.nextLine());
                System.out.println("Enter the column , Number ");
                col = Integer.parseInt(in.nextLine());
                if (Box[y[row - 1]][x[col - 1]].equals(" ")) {
                    Box[y[row - 1]][x[col - 1]] = val;
                    v = 0;
                    break;
                } else
                    System.out.println("A number is present in this position so it is invalid");
            }

        }*/
    }

    void box() {
        int x = 5, y = 10;
        for (int i = 0; i < Box.length; i++) {
            for (int j = 0; j < Box[0].length; j++) {
                Box[i][j] = i % 2 == 0 ? "−" : " ";
                if (j == 0 || j == Box[0].length - 1 || j == x || j == y)
                    Box[i][j] = "|";
            }
        }
        Box[0][0] = "+";
        Box[0][Box[0].length - 1] = "+";
        Box[Box.length - 1][0] = "+";
        Box[Box.length - 1][Box[0].length - 1] = "+";
        Box[Box.length - 1][x] = "+";
        Box[Box.length - 1][y] = "+";
        Box[0][x] = "+";
        Box[0][y] = "+";
    }

    void show() {
        for (String[] strings : Box) {
            int bound = Box[0].length;
            for (int i = 0; i < bound; i++) {
                String s = strings[i];
                System.out.print(s);
            }
            System.out.println();
        }
    }

    void play() {
        for (int i = 1; i <= 36; i++) {
            int a = (int) (Math.random() * 3);
            int b = (int) (Math.random() * 3);
            if (Box[y[a]][x[b]].equals(" ")) {
                Box[y[a]][x[b]] = "X";
                break;
            }
        }
    }

    int check() {
        if (Box[1][2].equals("0") && Box[1][7].equals("0") && Box[1][12].equals("0"))
            return 1;
        if (Box[3][2].equals("0") && Box[3][7].equals("0") && Box[3][12].equals("0"))
            return 1;
        if (Box[5][2].equals("0") && Box[5][7].equals("0") && Box[5][12].equals("0"))
            return 1;
        if (Box[1][2].equals("0") && Box[3][2].equals("0") && Box[5][2].equals("0"))
            return 1;
        if (Box[1][7].equals("0") && Box[3][7].equals("0") && Box[5][7].equals("0"))
            return 1;
        if (Box[1][12].equals("0") && Box[3][12].equals("0") && Box[5][12].equals("0"))
            return 1;
        if (Box[1][2].equals("0") && Box[3][7].equals("0") && Box[5][12].equals("0"))
            return 1;
        if (Box[1][12].equals("0") && Box[3][7].equals("0") && Box[5][2].equals("0"))
            return 1;
        if (Box[1][2].equals("X") && Box[1][7].equals("X") && Box[1][12].equals("X"))
            return 2;
        if (Box[3][2].equals("X") && Box[3][7].equals("X") && Box[3][12].equals("X"))
            return 2;
        if (Box[5][2].equals("X") && Box[5][7].equals("X") && Box[5][12].equals("X"))
            return 2;
        if (Box[1][2].equals("X") && Box[3][2].equals("X") && Box[5][2].equals("X"))
            return 2;
        if (Box[1][7].equals("X") && Box[3][7].equals("X") && Box[5][7].equals("X"))
            return 2;
        if (Box[1][2].equals("X") && Box[3][7].equals("X") && Box[5][12].equals("X"))
            return 2;
        if (Box[1][12].equals("X") && Box[3][12].equals("X") && Box[5][12].equals("X"))
            return 2;
        if (Box[1][12].equals("X") && Box[3][7].equals("X") && Box[5][2].equals("X"))
            return 2;
        else
            return 3;
    }

    private boolean checkWinO() {
        for (int k = 0; k < 24; k++) {
            if (Box[Win[k][0][0]][Win[k][0][1]].equals("0") && Box[Win[k][1][0]][Win[k][1][1]].equals("0")) {
                if (Box[Win[k][2][0]][Win[k][2][1]].equals(" ")) {
                    Box[Win[k][2][0]][Win[k][2][1]] = "0";
                    dumAction();
                    return true;
                }
            }
            if (Box[Win[k][1][0]][Win[k][1][1]].equals("0") && Box[Win[k][2][0]][Win[k][2][1]].equals("0")) {
                if (Box[Win[k][0][0]][Win[k][0][1]].equals(" ")) {
                    Box[Win[k][0][0]][Win[k][0][1]] = "0";
                    dumAction();
                    return true;
                }
            }
            if (Box[Win[k][2][0]][Win[k][2][1]].equals("0") && Box[Win[k][0][0]][Win[k][0][1]].equals("0")) {
                if (Box[Win[k][1][0]][Win[k][1][1]].equals(" ")) {
                    Box[Win[k][1][0]][Win[k][1][1]] = "0";
                    dumAction();
                    return true;
                }
            }
        }
        return false;
    }

    void dumAction() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (Box[y[i]][x[j]].equals("0"))
                    board[i][j].setChessToken(new
                            ChessToken(TicMarks.Zero, Colors.RED, Players.BLACK));
            }
        }

    }

    void thank() {
        System.out.println("Well played");
        System.out.println("Thanks for playing");
    }
}

