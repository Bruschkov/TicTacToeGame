package ticTacToe.players;


import ticTacToe.game.Board;

import java.util.ArrayList;
import java.util.Random;

public class MinMaxPlayer extends Player {

    public MinMaxPlayer(byte playerNumber) {
        this(playerNumber, "MinMaxPlayer");
    }
    public MinMaxPlayer(byte playerNumber, String name) {
        super(playerNumber, name);
    }

    @Override
    public void makeMove(Board board) {
        this.minmaxDecision(board);
    }

    public void minmaxDecision(Board board) {
        ArrayList<Integer> goodMoves = new ArrayList<Integer>();

        int max = Integer.MIN_VALUE;
        int bestMove = -2;
        for (int i: board.getLegalMoves()){
            Board newBoard = new Board(board);
            newBoard.setField(i, this.getPlayerNumber());
            int myValue = min(newBoard);
            //System.out.println(myValue);
            if (myValue > max) {
                max = myValue;
                bestMove = i;
                //goodMoves.add(i);
               //break;
            }
        }
        //board.setField(goodMoves.get(r.nextInt(goodMoves.size())), this.getPlayerNumber());
        board.setField(bestMove, this.getPlayerNumber());
    }

    public int max(Board board) {
        int localUtility = utility(board);
        if (localUtility != Integer.MIN_VALUE)
            return localUtility;

        int localValue = Integer.MIN_VALUE;
        for (int i: board.getLegalMoves()) {
            Board newBoard = new Board(board);
            newBoard.setField(i, this.getPlayerNumber());
            localValue = Math.max(localValue, min(newBoard));
        }

        return localValue;
    }

    public int min(Board board) {
        int localUtility = utility(board);
        if (localUtility != Integer.MIN_VALUE) {
            return localUtility;
        }

        int localValue = Integer.MAX_VALUE;

        for (int i: board.getLegalMoves()) {
            Board newBoard = new Board(board);
            newBoard.setField(i, this.opposingPlayer());
            localValue = Math.min(localValue, max(newBoard));
        }

        return localValue;
    }

    public byte opposingPlayer() {
        if (this.getPlayerNumber() == 1) {
            return 2;
        }
        return 1;
    }

    public int utility(Board board) {

        for (int i = 0; i < board.getWinningStates().length; i++) {

            int ix1 = board.getWinningStates()[i][0];
            int ix2 = board.getWinningStates()[i][1];
            int ix3 = board.getWinningStates()[i][2];

            if (board.getFields()[ix1] == 0
                    || board.getFields()[ix2] == 0
                    || board.getFields()[ix3] == 0) {
                continue;
            }

            if (board.getFields()[ix1] == board.getFields()[ix2]
                    &&  board.getFields()[ix2] == board.getFields()[ix3]) {

                if (board.getFields()[ix1]==this.getPlayerNumber())
                    return 1;
                else
                    return -1;
            }
        }

        if (board.isFull())
            return 0;

        return Integer.MIN_VALUE;
    }
}
