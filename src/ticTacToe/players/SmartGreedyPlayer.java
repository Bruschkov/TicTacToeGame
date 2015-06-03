package ticTacToe.players;

import ticTacToe.game.Board;

import java.util.ArrayList;

/**
 * Created by janbrusch on 03.06.15.
 */
public class SmartGreedyPlayer extends Player {

    public SmartGreedyPlayer(byte playerNumber) {
        super(playerNumber, "SmartGreedyPlayer");
    }

    @Override
    public void makeMove(Board board) {
        if(this.checkIfFirstMove(board)) {
            int firstMove = this.makeFirstMove(board);
            board.setField(firstMove, this.getPlayerNumber());
        } else {
            int nextMove = this.determineBestMove(board);
            board.setField(nextMove, this.getPlayerNumber());
        }
    }

    public boolean checkIfFirstMove(Board board) {
        int nonNulls = this.checkBoardForOccupiedFields(board);

        if (nonNulls > 1) {
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

    public int makeFirstMove(Board board) {
        if (this.isHomePlayer(board) || !this.doesOpponentStartInCorner(board)) {
            //start in a random corner
            return this.startRandomCorner();
        }

        int centralPoint = 4; // for variable boards there needs to be a function that determines the central Point
        return centralPoint;
    }

    public int startRandomCorner() {
        int startingPosition = 4 ;

        while (startingPosition == 4) {
            startingPosition = super.r.nextInt(4) * 2;
        }
        return startingPosition;
    }

    public boolean doesOpponentStartInCorner(Board board) {
        return !(board.getFields()[0] == 0 && board.getFields()[2] == 0 && board.getFields()[6] == 0 && board.getFields()[8] == 0);
    }

    public boolean isHomePlayer(Board board) {
        if (this.checkBoardForOccupiedFields(board) == 0) {
            return true;
        }

        return false;
    }

    public int determineBestMove(Board board) {
        // which winning states are still possible (aka completely unoccupied by opponent)?
        ArrayList<byte[]> possibleWinningStates =  this.findPossibleWinningStates(board);
        if (possibleWinningStates.isEmpty()) {
            return this.randomMove(board);
        }

        // In which ones do I have the most stones already occupied?
        byte[] bestBet = this.findBestWinningState(possibleWinningStates, board);

        // Select random field from best bet
        for (int i = 0; i < bestBet.length; i++) {
            if (board.getFields()[bestBet[i]] == 0){
                return bestBet[i];
            }
        }
        return -1;
    }

    public ArrayList<byte[]> findPossibleWinningStates(Board board) {
        ArrayList<byte[]> possibleWinningStates = new ArrayList<byte[]>();

        for (byte[] state: board.getWinningStates()) {
            boolean occupiedByOpponent = false;
            for (int i = 0; i < state.length; i++) {
                if (board.getFields()[state[i]] != 0 && board.getFields()[state[i]] != this.getPlayerNumber()) {
                    occupiedByOpponent = true;
                }
            }
            if (!occupiedByOpponent) {
                possibleWinningStates.add(state);
            }
        }

        return possibleWinningStates;
    }

    public byte[] findBestWinningState(ArrayList<byte[]> possibleWinningStates, Board board) {
        int[] occupiedFields = new int[possibleWinningStates.size()];

        for (int i = 0; i < possibleWinningStates.size(); i++) {
            for (int j = 0; j < possibleWinningStates.get(i).length; j++) {
                if (board.getFields()[possibleWinningStates.get(i)[j]] == this.getPlayerNumber()) {
                    occupiedFields[i]++;
                }
            }
        }

        for (int i = 0; i < occupiedFields.length; i++) {
            if (occupiedFields[i] == 2) {
                return possibleWinningStates.get(i);
            }
        }

        for (int i = 0; i < occupiedFields.length; i++) {
            if (occupiedFields[i] == 1) {
                return possibleWinningStates.get(i);
            }
        }

        return possibleWinningStates.get(0);
    }

    public int randomMove(Board board) {
        int maxLegalMoves = board.getLegalMoves().size();
        int nextMove = super.getNextMove(maxLegalMoves);
        return board.getLegalMoves().get(nextMove);
    }
}

