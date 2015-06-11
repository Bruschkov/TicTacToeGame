package ticTacToe.game;

import java.util.ArrayList;

/**
 * Created by Jan Brusch on 27.05.2015.
 */
public class Board implements Cloneable {

    private static byte[][] winningStates = null;

    private byte [] fields;
    private byte boardSize;

    public Board() {
        this((byte)3);
    }

    public Board(byte boardSize) {
        this.boardSize = boardSize;
        this.fields = new byte[boardSize*boardSize];

        if (winningStates == null) {
            winningStates = new byte[2 + boardSize * 2][boardSize];
            int zz = 0;
            for (byte zeile = 0; zeile < boardSize; zeile++) {
                winningStates[zz] = getZeile(zeile);
                zz++;
            }
            for (byte spalte =0; spalte < boardSize; spalte++) {
                winningStates[zz] = getSpalte(spalte);
                zz++;
            }
            for (byte diag=0; diag<boardSize; diag++) {
                winningStates[zz][diag] = (byte) (diag * boardSize + diag);
            }
            zz++;
            for (byte diag=0; diag<boardSize; diag++) {
                winningStates[zz][diag] = (byte) (diag * boardSize + (boardSize - (diag+1)));
            }
        }

    }

    public Board(Board another) {
        this.boardSize = another.getBoardSize();
        this.fields = another.getFields().clone();
    }


    public byte[] getZeile(byte zeile) {
        byte[] zeilenIndizes = new byte[this.getBoardSize()];
        byte startWert = (byte) (zeile * this.getBoardSize());
        for (byte index = 0; index < this.getBoardSize(); index++) {
            zeilenIndizes[index] = (byte) (startWert + index);
        }
        return zeilenIndizes;
    }


    public byte[] getSpalte(byte spalte) {
        byte[] spaltenIndizes = new byte[this.getBoardSize()];
        byte startWert = spalte;
        for (byte index = 0; index < this.getBoardSize(); index++) {
            spaltenIndizes[index] = (byte) (startWert + index * this.getBoardSize());
        }
        return spaltenIndizes;
    }


    public void setField(int index, byte move) {
        fields[index] = move;
    }

    public byte[] getFields() {
        return fields;
    }

    public boolean isFull()
    {
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


    public int getWinnerPerspectiveForPlayer(int playerNumber) {

        for (int state = 0; state < getWinningStates().length; state++) {

            boolean matched = true;
            for (int field = 0; field < getWinningStates()[state].length-1; field++) {
                matched = matched && (getFields()[getWinningStates()[state][field]] == getFields()[getWinningStates()[state][field+1]]);
            }
            matched = matched && (getFields())[getWinningStates()[state][0]] != 0;

            if (matched) {
                if ((getFields())[getWinningStates()[state][0]] == playerNumber) {
                    return 1;
                }
                else {
                    return -1;
                }
            }
        }

        if (isFull())
            return 0;

        return Integer.MIN_VALUE;
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

    public byte getBoardSize() {
        return this.boardSize;
    }

    public boolean hasWinner() {
        return (this.getWinnerPerspectiveForPlayer(1)!=0 && this.getWinnerPerspectiveForPlayer(1)!=Integer.MIN_VALUE);
    }
}
