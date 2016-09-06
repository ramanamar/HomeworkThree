/**
 *Created by Raman Kryvasheyeu
 *MyFirstProgramm on GB_Java_1 23.08.2016
 */
package com.company;

import java.util.*;

public class Main {

    final char PLAYER_DOT = 'x';
    final char AI_DOT = 'o';
    final char EMPTY_DOT = '.';
    final int FIELD_DIMENSIONS = 5;
    final int WIN_COUNT = 4;
    int dotCounter = 0;
    char[][] field = new char [FIELD_DIMENSIONS][FIELD_DIMENSIONS];

    Scanner sc = new Scanner(System.in);
    Random rand = new Random();

    public static void main(String[] args) {
        new Main().go();
    }

    void go() {
        initField();
        while (true) {
            playerTurn();
            printField();
            if (checkWin(PLAYER_DOT)) {
                println("You WIN!");
                break;
            }
            if (isFieldFull()) {
                println("Sorry, draft");
                break;
            }
            aiTurn();
            printField();
            if (checkWin(AI_DOT)) {
                println("Computer WIN!");
                break;
            }
            if (isFieldFull()) {
                println("Sorry, draft");
                break;
            }
        }
    }

    void initField() {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field.length; j++) {
                field[i][j] = EMPTY_DOT;
            }
        }
    }

    void printField() {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field.length; j++) {
                print(field[i][j]);
            }
            println(" ");
        }
        println(" ");
    }

    void playerTurn() {
        int x, y;
        do {
            System.out.println("Enter X and Y (1-5):");
            x = sc.nextInt();
            y = sc.nextInt();
        } while(!isCellEmpty(x - 1, y - 1));
        field[x - 1][y - 1] = PLAYER_DOT;
    }

    void aiTurn() {
        int x, y;
        do {
            x = rand.nextInt(field.length);
            y = rand.nextInt(field.length);
        } while(!isCellEmpty(x, y));
        field[x][y] = AI_DOT;
    }

    boolean isCellEmpty(int x, int y) {
        if (x < 0 || y < 0 || x > field.length || y > field.length) {
            return false;
        }
        if (field[x][y] == EMPTY_DOT) {
            return true;
        }
        return false;
    }

    boolean checkWin(char dot) {
        int i;
        int j;
        //alternate horizontal and vertical check
        for (i = 0; i < field.length; i++) {
            for (j = 0; j < field.length; j++) {
                if ((field[i][j] == dot) || (field[j][i] == dot)) {
                    dotCounter++;
                } else {
                    dotCounter = 0;
                }
                if (dotCounter == WIN_COUNT) {
                    return true;
                }
            }
        }
        //alternate diagonal check  from left to right
        for (i = 0; i < field.length; i++) {
            for (j = 0; j < field.length; j++) {
                for (int x = i, y = j; x < field.length && y < field.length; x++, y++) {
                    if (field[x][y] == dot) {
                        dotCounter++;
                    } else {
                        dotCounter = 0;
                    }
                    if (dotCounter == WIN_COUNT) {
                        return true;
                    }
                }
                dotCounter = 0;
            }
        }
        //alternate diagonal check  from right to left
        for (i = 0; i < field.length; i++) {
            for (j = 0; j < field.length; j++) {
                for (int x = i, y = j; x < field.length && y >= 0; x++, y--) {
                    if (field[x][y] == dot) {
                        dotCounter++;
                    } else {
                        dotCounter = 0;
                    }
                    if (dotCounter == WIN_COUNT) {
                        return true;
                    }
                }
                dotCounter = 0;
            }
        }
        return false;
    }

    boolean isFieldFull() {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field.length; j++) {
                if (field[i][j] == EMPTY_DOT) {
                    return false;
                }
            }
        }
        return true;
    }

    public void print(char field) {
        System.out.print(field);
    }

    public void println(String text) {
        System.out.println(text);
    }
}
