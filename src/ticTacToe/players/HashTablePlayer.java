package ticTacToe.players;

/**
 * Created by janbrusch on 03.07.15.
 *
 * Player uses HashTable to save results of previously looked up nodes. If the
 */

import ticTacToe.game.Board;

import java.util.ArrayList;
import java.util.Hashtable;

public class HashTablePlayer extends Player{

    private Hashtable MaxHashTable;
    private Hashtable MinHashTable;

    public HashTablePlayer(byte playerNumber) {
        this(playerNumber, "HashTablePlayer");
    }

    public HashTablePlayer (byte playerNumber, String playerName) {
        super(playerNumber, playerName);
        this.MaxHashTable = new Hashtable();
        this.MinHashTable = new Hashtable();
    }


    @Override
    public void makeMove(Board board) {
        int move = -1;
        if(this.checkIfFirstMove(board)) {
            move = 0;
        } else {
            int nodesgenerated = this.getNodesGenerated();
            move = initialMove(board);
            //System.out.println("Nodes generated: " + (this.getNodesGenerated() - nodesgenerated));
        }
        board.setField(move, this.getPlayerNumber());
    }

    public boolean checkIfFirstMove(Board board) {
        int nonNulls = this.checkBoardForOccupiedFields(board);

        if (nonNulls > 0) {
            return false;
        }

        return true;
    }

    public int checkBoardForOccupiedFields(Board board) {
        int occupiedFields = 0;

        for (int i = 0; i < board.getFields().length; i++) {
            if (board.getFields()[i] != 0) {
                occupiedFields++;
            }
        }

        return occupiedFields;
    }

    public int initialMove(Board board) {
        int maxValue = Integer.MIN_VALUE;
        int bestMove = -2;
        ArrayList<Integer> sortedMoves = this.sortMoves(board);
        for (int i: sortedMoves) {
            this.nodesGenerated++;
            Board newBoard = new Board(board);
            newBoard.setField(i, this.getPlayerNumber());
            int myValue = this.alphaBeta(newBoard, Integer.MIN_VALUE, Integer.MAX_VALUE, false);
            //System.out.println(myValue);

            //Greedy Option: If there is a way to win, take it immediately
            if (myValue == 1) {
                return i;
            }

            if (myValue > maxValue) {
                maxValue = myValue;
                bestMove = i;
            }
        }
        return bestMove;
    }

    public int alphaBeta(Board board, int alpha, int beta, boolean isMaxPlayer) {
        //this is the Wikipedia Version of the Algorithm (https://en.wikipedia.org/wiki/Alpha%E2%80%93beta_pruning#Pseudocode)
        this.nodesGenerated++;
        int localUtility = this.utility(board);
        if (localUtility != Integer.MIN_VALUE)
            return localUtility;

        if (isMaxPlayer){
            int localValue = Integer.MIN_VALUE;

            for (int i: board.getLegalMoves()) {
                Board newBoard = new Board(board);
                newBoard.setField(i, this.getPlayerNumber());
                localValue = Math.max(localValue, this.alphaBeta(newBoard, alpha, beta, false));
                if (localValue >= beta) {
                    return localValue;
                }
                alpha = Math.max(alpha, localValue);
            }
            return localValue;
        } else {
            int localValue = Integer.MAX_VALUE;

            for (int i: board.getLegalMoves()) {
                Board newBoard = new Board(board);
                newBoard.setField(i, this.opposingPlayer());
                localValue = Math.min(localValue, this.alphaBeta(newBoard, alpha, beta, true));
                if (localValue <= alpha){
                    return localValue;
                }
                beta = Math.min(beta, localValue);
            }
            return localValue;
        }
    }
}
