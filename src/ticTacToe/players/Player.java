package ticTacToe.players;

import ticTacToe.game.Board;
import java.util.Random;

/**
 * Created by Jan Brusch on 27.05.2015.
 */
public abstract class Player {

    private byte playerNumber;
    public Random r;
    private String name;
    public int nodesGenerated;

    public Player(byte playerNumber, String name) {
        this.r = new Random();
        this.playerNumber = playerNumber;
        this.name = name;
        this.nodesGenerated = 0;
    }

    @Override
    public String toString() {
        return "Player " + playerNumber + "(" + this.getName() + ")";
    }

    public String getName() {
        return this.name;
    }
    public byte getPlayerNumber() {
        return playerNumber;
    }

    public int getNextMove(int maxLegalMoves) {
        return this.r.nextInt(maxLegalMoves);
    }

    public abstract void makeMove(Board board);

    public int getNodesGenerated() {
        return nodesGenerated;
    }

    public void reset() {
        this.nodesGenerated = 0;
    }
}

