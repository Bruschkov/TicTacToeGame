package ticTacToe.players;

import ticTacToe.game.Board;

/**
 * Created by janbrusch on 03.06.15.
 */
public class HumanPlayer extends Player {

    public HumanPlayer(byte playerNumber, String name) {
        super(playerNumber, "HumanPlayer");
    }

    @Override
    public void makeMove(Board board) {
        //prompt for legal move here...
    }
}
