package com.company;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Jan Brusch on 27.05.2015.
 */
public class Player {

    private byte playerNumber;
    private Random r;

    public Player(byte playerNumber) {
        this.r = new Random();
        this.playerNumber = playerNumber;
    }

    public void makeMove(Board board){
        int maxLegalMoves = board.getLegalMoves().size();

        int nextMove = r.nextInt(maxLegalMoves);

        board.setField(board.getLegalMoves().get(nextMove), this.playerNumber);
    }

    @Override
    public String toString() {
        return "Player" + playerNumber;
    }
}
