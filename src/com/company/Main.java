package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Game ticTacToe = new Game(new Player((byte)1), new Player((byte)2));
        ticTacToe.play();
    }
}
