package ticTacToe.game;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Jan Brusch on 27.05.2015.
 */
public class Board implements Cloneable {

    private static byte[][] winningStates = new byte[][] {{0,1,2,3},{4,5,6,7},{8,9,10,11},{12,13,14,15},{0,4,8,12},{1,5,9,13},{2,6,10,14},{3,7,1,15}, {0,5,10,15}, {3,6,9,12}};
    private byte [] fields;
    private int boardSize;


    public Board() {
        this(4);
    }

    public Board(int boardSize) {
        this.boardSize = boardSize;
        this.fields = new byte[boardSize*boardSize];
    }

    public Board(Board another) {
        this.fields = another.getFields().clone();
    }


    public void setField(int index, byte move) {
        fields[index] = move;
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
        ArrayList<Integer> legal = new ArrayList<>();
        for (int i=0; i<this.getFields().length; i++) {
            byte valueAtPosition = this.getFields()[i];
            if (valueAtPosition == 0) {
                legal.add(i);
            }
        }
        return legal;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i<this.boardSize; i++) {
            for (int j = 0; j<this.boardSize; j++) {
                sb.append(this.fields[i*this.boardSize+j]);
                sb.append(" ");
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    public static byte[][] getWinningStates() {
        return winningStates;
    }

    public int getBoardSize() {
        return this.boardSize*this.boardSize;
    }
}
