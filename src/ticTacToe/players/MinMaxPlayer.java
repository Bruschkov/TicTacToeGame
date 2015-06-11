package ticTacToe.players;


import ticTacToe.game.Board;

import java.util.ArrayList;

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
       return board.getWinnerPerspectiveForPlayer(this.getPlayerNumber());
    }
}
