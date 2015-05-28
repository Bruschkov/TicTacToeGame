package com.company;

import java.util.Random;

/**
 * Created by Jan Brusch on 27.05.2015.
 */
public class Game {

    private static byte[][] winningStates = new byte[][] {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    private Player player1;
    private Player player2;
    private Board board;
    private boolean toggle;

    public Game(Player player1, Player player2) {
        this.board = new Board();
        this.player1 = player1;
        this.player2 = player2;
        Random r = new Random();
        this.toggle = r.nextBoolean();
    }

    public void play() {
        Player current = null;

        while (!doesGameEnd()) {
            current = this.choosePlayer();
            current.makeMove(this.board);
            System.out.println(board.toString());
        }
        this.printWinner(current);
    }

    public boolean doesGameEnd() {
        if (this.hasWinner() || this.board.isFull()) {
            return true;
        }
        return false;
    }

    public Player choosePlayer() {
        this.toggle = !this.toggle;
        if (toggle) {
            return this.player1;
        }
        return this.player2;
    }

    public boolean hasWinner(){
        for (int i = 0; i < winningStates.length; i++) {

            int ix1 = winningStates[i][0];
            int ix2 = winningStates[i][1];
            int ix3 = winningStates[i][2];

            if (this.board.getFields()[ix1] == 0 || this.board.getFields()[ix2] == 0 || this.board.getFields()[ix3] == 0) {
                continue;
            }

            if (this.board.getFields()[ix1] == this.board.getFields()[ix2] &&  this.board.getFields()[ix2] == this.board.getFields()[ix3]) {
                return true;
            }
        }
        return false;
    }

    public void printWinner(Player player) {
        System.out.println("Winner: " + player);
    }

}
