package ticTacToe.game;

import ticTacToe.players.GreedyPlayer;
import ticTacToe.players.Player;
import ticTacToe.players.RandomPlayer;
import ticTacToe.players.SmartGreedyPlayer;

public class Main {

    public static void main(String[] args) {

        //System.out.println("Playing one game");

        Player player1 = new GreedyPlayer((byte)1);
        Player player2 = new SmartGreedyPlayer((byte)2);

        //Game ticTacToe = new Game(player1, player2, true);
        //ticTacToe.play();


        int numberOfGames = 5000;
        System.out.println("Playing " + numberOfGames + " games");
        GameEvaluation evaluation = new GameEvaluation(numberOfGames);
        evaluation.evaluate(player1, player2);
    }
}
