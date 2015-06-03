package ticTacToe.game;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Jan Brusch on 27.05.2015.
 */
public class Board {

    private static byte[][] winningStates = new byte[][] {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    private byte [] fields;
    private ArrayList<Integer> legalMoves;

    public Board() {
        this.fields = new byte[9];
        this.legalMoves = new ArrayList<Integer>();
        for (int i = 0; i < fields.length; i++) {
            this.legalMoves.add(i);
        }
    }

    public void setField(int index, byte move) {
        fields[index] = move;
        this.legalMoves.remove(this.legalMoves.indexOf(index));
    }

    public byte[] getFields() {
        return fields;
    }

    public boolean isFull() {
        int numberZeros = 0;
        for (int i = 0; i < fields.length; i++) {
            if (fields[i] == 0) {
                numberZeros++;
            }
        }

        if (numberZeros == 0) {
            return true;
        }

        return false;
    }

    public ArrayList<Integer> getLegalMoves() {
        return this.legalMoves;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i<3; i++) {
            for (int j = 0; j<3; j++) {
                sb.append(this.fields[i*3+j]);
                sb.append(" ");
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    public static byte[][] getWinningStates() {
        return winningStates;
    }
}
