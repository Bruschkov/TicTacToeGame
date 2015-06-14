package ticTacToe.game;

import ticTacToe.players.*;

public class Main {

    public static void main(String[] args) {

        //System.out.println("Playing one game");

        Player player1 = new HumanPlayer((byte)1);
        Player player2 = new MinMaxPlayer((byte)2);

        byte boardSize = 4;
        Game ticTacToe = new Game(player1, player2, boardSize, true);
        ticTacToe.play();

        /*
        int numberOfGames = 50;
        //byte boardSize = 3;
        System.out.println("Playing " + numberOfGames + " games");
        GameEvaluation evaluation = new GameEvaluation(numberOfGames, player1, player2, boardSize);
        evaluation.evaluate();
        System.out.println(evaluation);
        */
    }
}
