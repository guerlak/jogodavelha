package com.guerlak.jogodavelha.core;

public class Move {

	private int x;
	private int y;

	public Move(String moveStr) throws InvalidMoveException {
		try {
			String[] tokens = moveStr.split(",");
			this.x = Integer.parseInt(tokens[0]);
			this.y = Integer.parseInt(tokens[1]);
		} catch (Exception e) {
			throw new InvalidMoveException("Jogada inválida");
		}
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

}
