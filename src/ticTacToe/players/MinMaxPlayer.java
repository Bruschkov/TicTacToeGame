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
        int nodesgenerated = this.getNodesGenerated();
        System.out.println("nodes generated so far: " + nodesgenerated);
        int move = this.minmaxDecision(board);
        board.setField(move, this.getPlayerNumber());
        System.out.println("Nodes generated for this move: " + (this.getNodesGenerated() - nodesgenerated));
        System.out.println("Nodes generated total: " + this.getNodesGenerated());
    }

    public int minmaxDecision(Board board) {
        ArrayList<Integer> goodMoves = new ArrayList<Integer>();

        int maxValue = Integer.MIN_VALUE;
        int bestMove = -2;
        for (int i: board.getLegalMoves()){
            this.nodesGenerated++;
            Board newBoard = new Board(board);
            newBoard.setField(i, this.getPlayerNumber());
            int myValue = min(newBoard);
            System.out.println(myValue);

            //Greedy Option: If there is a way to win, take the first one
            if (myValue == 1) {
                return i;
            }

            if (myValue > maxValue) {
                maxValue = myValue;
                bestMove = i;
                //goodMoves.add(i);
               //break;
            }
        }
        return bestMove;
    }

    public int max(Board board) {
        this.nodesGenerated++;
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
        this.nodesGenerated++;
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

