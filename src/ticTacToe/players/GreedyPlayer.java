package ticTacToe.players;

import ticTacToe.game.Board;

import java.util.Random;

/**
 * Created by Danny on 28.05.2015.
 */
public class GreedyPlayer extends Player {

    Random rand;

    public GreedyPlayer(byte playerNumber) {
        super(playerNumber, "GreedyPlayer");
        rand = new Random();
    }

    public int lastMarkedField = -1;

    public void makeMove(Board board) {

        if (this.lastMarkedField == -1) {
            boolean fieldFound = false;
            while (!fieldFound) {
                int move = this.getNextMove(board.getLegalMoves().size());
                this.lastMarkedField = board.getLegalMoves().get(move);
                board.setField(board.getLegalMoves().get(move),this.getPlayerNumber());
                fieldFound = true;
            }
        } else {
            int up = this.oneStepUp(this.lastMarkedField);
            if (board.getLegalMoves().contains(up)) {
                this.lastMarkedField = up;
                board.setField(up, this.getPlayerNumber());
            }
            else {
                int right = this.oneStepRight(this.lastMarkedField);
                if (board.getLegalMoves().contains(right)) {
                    this.lastMarkedField = right;
                    board.setField(right, this.getPlayerNumber());
                }
                else {
                    int down = this.oneStepDown(this.lastMarkedField);
                    if (board.getLegalMoves().contains(down)) {
                        this.lastMarkedField = down;
                        board.setField(down, this.getPlayerNumber());
                    }
                    else {

                        int left = this.oneStepLeft(this.lastMarkedField);
                        if (board.getLegalMoves().contains(left)) {
                            this.lastMarkedField = left;
                            board.setField(left, this.getPlayerNumber());
                        }
                        else {
                            this.lastMarkedField = board.getLegalMoves().get(0);
                            board.setField(lastMarkedField, this.getPlayerNumber());
                        }
                    }
                }

            }
        }

    }

    /**
     * 0 1 2
     * 3 4 5
     * 6 7 8
     **/
    public int lastXPos() {
        return this.lastMarkedField % 3;
    }

    public int lastYPos() {
        return this.lastMarkedField / 3;
    }

    public int oneStepLeft(int lastMove) {
        if (this.lastXPos() > 0) {
            return lastMove - 1;
        }
        else {
            return lastMove + 2 ;
        }
    }

    public int oneStepRight(int lastMove) {
        if (this.lastXPos() < 2) {
            return lastMove + 1;
        } else {
            return lastMove - 2;
        }
    }

    public int oneStepDown(int lastMove) {
        if (this.lastYPos() < 2) {
            return lastMove + 3;
        }
        else {
            return lastMove - 6;
        }
    }

    public int oneStepUp(int lastMove) {
        if (this.lastYPos() > 0) {
            return lastMove - 3;
        }
        else {
            return lastMove + 6;
        }
    }
}
