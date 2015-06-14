package ticTacToe.players;

import ticTacToe.game.Board;
import java.util.Scanner;

/**
 * Created by janbrusch on 03.06.15.
 */
public class HumanPlayer extends Player {

    private Scanner reader;

    public HumanPlayer(byte playerNumber) {
        super(playerNumber, "HumanPlayer");
        this.reader = new Scanner(System.in);
    }

    @Override
    public void makeMove(Board board) {
        //prompt for legal move here...

        System.out.println("Where do you want to play? (Fields are numbered from 1-" + board.getBoardSize()*board.getBoardSize() +", starting top left)");
        int playerMove = reader.nextInt()-1;

        while (!board.getLegalMoves().contains(playerMove)) {
            System.out.println("Not a legal move! Where do you want to play?");
            playerMove = reader.nextInt()-1;

        }

        board.setField(playerMove, this.getPlayerNumber());
    }
}
