package ticTacToe.game;

import ticTacToe.players.Player;

/**
 * Created by Danny on 29.05.2015.
 */
public class GameEvaluation {

    private int numberOfGames = 1000;

    private int wins[] = new int[2];
    private int firstMoves[] = new int[2];
    private int draws = 0;

    public GameEvaluation() {}

    public GameEvaluation(int numberOfGames) {
        this.numberOfGames = numberOfGames;
    }


    public void evaluate(Player player1, Player player2) {
        for (int i=0; i<numberOfGames; i++) {
            Game g = new Game(player1,player2, false);
            g.play();

            if (g.board.hasWinner()) {
                wins[g.getWinner().getPlayerNumber() - 1]++;
            }
            else {
                this.draws++;
            }
        }

        System.out.println("Player1 (" + player1.getName() + ") wins:" + this.wins[0]);
        System.out.println("Player2 (" + player2.getName() + ") wins:" + this.wins[1]);
        System.out.println("Draws: " + this.draws);
    }
}
