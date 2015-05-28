package ticTacToe.game;

import ticTacToe.players.RandomPlayer;

public class Main {

    public static void main(String[] args) {
	// write your code here
        RandomPlayer player1 = new RandomPlayer((byte)1);
        RandomPlayer player2 = new RandomPlayer((byte)2);

        Game ticTacToe = new Game(player1, player2);
        ticTacToe.play();
    }
}
