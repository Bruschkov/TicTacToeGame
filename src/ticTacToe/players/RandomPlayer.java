package ticTacToe.players;

import ticTacToe.game.Board;

/**
 * Created by Jan Brusch on 28.05.2015.
 */
public class RandomPlayer extends Player{

    public RandomPlayer(byte playerNumber) {
        super(playerNumber);
    }

    public void makeMove(Board board) {
        int maxLegalMoves = board.getLegalMoves().size();
        int nextMove = super.getNextMove(maxLegalMoves);
        board.setField(board.getLegalMoves().get(nextMove), super.getPlayerNumber());
    }
}
