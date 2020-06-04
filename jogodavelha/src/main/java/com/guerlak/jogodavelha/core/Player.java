package com.guerlak.jogodavelha.core;

import com.guerlak.jogodavelha.ui.UI;

public class Player {

	private String name;
	private Board board;
	private char symbol;
	
	public Player(String name, Board board, char symbol) {
		this.name = name;
		this.board = board;
		this.symbol = symbol;
	}

	private Move inputMove() throws InvalidMoveException {
		String moveStr = UI.readInput("Jogador '" + name + " => ");
		Move m = new Move(moveStr);
		return m;
	}

	public boolean play() throws InvalidMoveException {
		Move move = inputMove();
		return board.play(this, move);
	}
	
	public char getSymbol() {
		return symbol;
	}
	
	public Board getBoard() {
		return board;
	}
	
	public String getName() {
		return name;
	}
}
