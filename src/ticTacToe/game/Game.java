package ticTacToe.game;

import ticTacToe.players.Player;

import java.util.Random;

/**
 * Created by Jan Brusch on 27.05.2015.
 */
public class Game {

    private Player player1;
    private Player player2;

    private Player currentPlayer = null;
    Board board;
    private boolean toggle;

    private boolean verbose = true;

    public Game(Player player1, Player player2, byte boardSize) {
        this.board = new Board(boardSize);
        this.player1 = player1;
        this.player2 = player2;
        Random r = new Random();
        this.toggle = r.nextBoolean();
    }

    public Game(Player player1, Player player2, byte boardSize, boolean verbose) {
        this(player1, player2, boardSize);
        this.verbose = verbose;
    }

    public void play() {

        while (!doesGameEnd()) {
            currentPlayer = this.choosePlayer();
            currentPlayer.makeMove(this.board);
            if (verbose)
                System.out.println(board.toString());
        }
        if (verbose)
            this.printResult(currentPlayer);
    }

    public boolean doesGameEnd() {
        if (this.board.hasWinner() || this.board.isFull()) {
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

    public Player getWinner() {
        if (this.board.hasWinner()) {
            return this.currentPlayer;
        }
        else {
            return null;
        }
    }

    public boolean isDraw() {
        if (this.board.isFull() && !this.board.hasWinner()) {
            return true;
        }
        return false;
    }

    public void printResult(Player player) {
        if (this.isDraw()) {
            System.out.println("Draw!");
        } else{
            System.out.println("Winner: " + player);
        }
    }


}
