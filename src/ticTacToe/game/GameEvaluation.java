package ticTacToe.game;

import ticTacToe.players.MinMaxPlayer;
import ticTacToe.players.Player;

/**
 * Created by Danny on 29.05.2015.
 */
public class GameEvaluation {

    private int numberOfGames = 1000;

    private int wins[] = new int[2];
    private int firstMoves[] = new int[2];
    private int draws = 0;

    private Player player1;
    private Player player2;

    private byte boardSize;
    private int[] player1NodesGenerated;
    private int[] player2NodesGenerated;

    public GameEvaluation() {}

    public GameEvaluation(int numberOfGames, Player player1, Player player2 , byte boardSize) {
        this.player1 = player1;
        this.player2 = player2;
        this.boardSize = boardSize;
        this.numberOfGames = numberOfGames;
        this.player1NodesGenerated = new int[numberOfGames];
        this.player2NodesGenerated = new int[numberOfGames];
    }


    public void evaluate() {
        for (int i=0; i<numberOfGames; i++) {
            this.player1.reset();
            this.player2.reset();

            Game g = new Game(this.player1,this.player2, this.boardSize, false);
            g.play();

            this.player1NodesGenerated[i] = this.player1.getNodesGenerated();
            this.player2NodesGenerated[i] = this.player2.getNodesGenerated();

            if (g.board.hasWinner()) {
                wins[g.getWinner().getPlayerNumber() - 1]++;
            }
            else {
                this.draws++;
            }
        }
    }

    public float avgNodesGenerated(int[] playerNodesGenerated) {
        int sum = 0;
        for (int i: playerNodesGenerated) {
            sum += i;
        }

        return (float)sum / playerNodesGenerated.length;
    }

    @Override
    public String toString() {
        String player1Wins = "Player1 (" + this.player1.getName() + ") wins:" + this.wins[0] + "\n";
        String player2Wins = "Player2 (" + this.player2.getName() + ") wins:" + this.wins[1] + "\n";
        String draws = ("Draws: " + this.draws);

        String player1NodesGenerated ="Player1 Nodes Generated (Avg):"  + this.avgNodesGenerated(this.player1NodesGenerated) + "\n";
        String player2NodesGenerated ="Player2 Nodes Generated (Avg):"  + this.avgNodesGenerated(this.player2NodesGenerated) + "\n";

        return player1Wins + player1NodesGenerated + player2Wins + player2NodesGenerated + draws;
    }
}
