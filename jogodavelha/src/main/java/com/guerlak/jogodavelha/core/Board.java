package com.guerlak.jogodavelha.core;

import com.guerlak.jogodavelha.Constants;
import com.guerlak.jogodavelha.ui.UI;

public class Board {

	private char[][] mat;

	public Board() {
		mat = new char[Constants.BOARD_SIZE][Constants.BOARD_SIZE];
		clear();
	}

	public void clear() {
		for (int x = 0; x < mat.length; x++) {
			for (int y = 0; y < mat[x].length; y++) {
				this.mat[x][y] = ' ';
			}
		}
	}

	public void print() {
		UI.printNewLine();
		for (int x = 0; x < mat.length; x++) {
			for (int y = 0; y < mat[x].length; y++) {
				UI.printTextWithNoNewLine(String.valueOf(mat[x][y]));
				if (y < mat[x].length - 1) {
					UI.printTextWithNoNewLine(" | ");
				}
			}
			UI.printNewLine();
			if (x < mat.length - 1) {
				UI.printText("---------");
			}
		}
	}

	public boolean isFull() {
		for (int x = 0; x < mat.length; x++) {
			for (int y = 0; y < mat[x].length; y++) {
				if (this.mat[x][y] != ' ') {
					return false;
				}
			}
		}
		return true;
	}

	public boolean play(Player player, Move move) throws InvalidMoveException {
		int x = move.getX();
		int y = move.getY();
		try {
			if(mat[x][y] != ' ') {
				throw new InvalidMoveException("ERRO: Campo já preenchido.");
			}
			mat[x][y] = player.getSymbol();
		}catch (ArrayIndexOutOfBoundsException e) {
			throw new InvalidMoveException("ERRO: Indice maior do que a tabela.");
		}
		
		/*
		 * System.out.println(checkRows(player)); System.out.println(checkCols(player));
		 * System.out.println(checkDiagonalOne(player));
		 * System.out.println(checkDiagonalTwo(player));
		 */

		return checkRows(player) || checkCols(player) || checkDiagonalOne(player) || checkDiagonalTwo(player);
	}

	private boolean checkRow(int x, Player p) {
		for (int i = 0; i < mat.length; i++) {
			if (mat[x][i] != p.getSymbol()) {
				return false;
			}
		}
		return true;
	}

	private boolean checkRows(Player p) {
		for (int i = 0; i < mat.length; i++) {
			if (checkRow(i, p)) {
				return true;
			}
		}
		return false;
	}

	private boolean checkCol(int y, Player p) {
		for (int i = 0; i < mat.length; i++) {
			if (mat[i][y] != p.getSymbol()) {
				return false;
			}
		}
		return true;
	}

	private boolean checkCols(Player p) {
		for (int i = 0; i < mat.length; i++) {
			if (checkCol(i, p)) {
				return true;
			}
		}
		return false;
	}

	private boolean checkDiagonalOne(Player p) {

		for (int i = 0; i < mat.length; i++) {
			if (mat[i][i] != p.getSymbol()) {
				return false;
			}
		}
		return true;
	}

	private boolean checkDiagonalTwo(Player p) {
		char symbol = p.getSymbol();
		int lastLine = Constants.BOARD_SIZE - 1;

		for (int x = 0, y = lastLine; y >= 0; x++, y--) {
			if (mat[x][y] != symbol) {
				return false;
			}
		}
		return true;
	}
}
