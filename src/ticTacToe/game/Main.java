package ticTacToe.game;

import ticTacToe.players.GreedyPlayer;
import ticTacToe.players.Player;
import ticTacToe.players.RandomPlayer;

public class Main {

    public static void main(String[] args) {

        System.out.println("Playing one game");

        Player player1 = new RandomPlayer((byte)1);
        Player player2 = new GreedyPlayer((byte)2);

        Game ticTacToe = new Game(player1, player2);
        ticTacToe.play();

        System.out.println("Playing many games");
        GameEvaluation evaluation = new GameEvaluation();
        evaluation.evaluate(player1, player2);
    }
}
