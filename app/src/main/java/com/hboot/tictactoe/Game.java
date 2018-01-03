package com.hboot.tictactoe;
/**
 * Created by hboot on 1/2/2018.
 */

// Implemented Game as a singleton class 

public class Game {

    public static final int EMPTY = 0;
    public static final int CROSS = 1;
    public static final int NOUGHT = 2;

    public int board[][];

    private static Game oneInstance = null;

    private Game(){
        board = new int[3][3];
    }

    public static Game getInstance(){
        return oneInstance == null ? new Game() : oneInstance;
    }

    public void mark(int currentRow, int currentCol, int play){
        if(board[currentRow][currentCol] == EMPTY)
            board[currentRow][currentCol] = play;
    }

    public boolean hasWon(int currentRow, int currentCol, int play) {
        return (board[currentRow][0] == play
                && board[currentRow][1] == play
                && board[currentRow][2] == play

                || board[0][currentCol] == play
                && board[1][currentCol] == play
                && board[2][currentCol] == play

                || currentRow == currentCol
                && board[0][0] == play
                && board[1][1] == play
                && board[2][2] == play

                || currentRow + currentCol == 2
                && board[0][2] == play
                && board[1][1] == play
                && board[2][0] == play);
    }


    public void reset() {
        board = new int[3][3];
    }

    public int valueAt(int x, int y){
        return board[x][y];
    }
}
