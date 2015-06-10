package ticTacToe.game;

import ticTacToe.players.*;

public class Main {

    public static void main(String[] args) {

        System.out.println("Playing one game");

        Player player1 = new HumanPlayer((byte)1);
        Player player2 = new MinMaxPlayer((byte)2);

        Game ticTacToe = new Game(player1, player2, true);
        ticTacToe.play();
        /*
        System.out.println("Playing many games");
        GameEvaluation evaluation = new GameEvaluation();

        player1 = new MinMaxPlayer((byte)1);
        player2 = new SmartGreedyPlayer((byte)2);

        evaluation.evaluate(player1, player2);
        */
    }
}
