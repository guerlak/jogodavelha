package com.guerlak.jogodavelha.core;

import java.io.IOException;

import com.guerlak.jogodavelha.Constants;
import com.guerlak.jogodavelha.score.FileScoreManager;
import com.guerlak.jogodavelha.score.ScoreManager;
import com.guerlak.jogodavelha.ui.UI;

public class Game{
	
	private Board board = new Board();
	private Player[] players = new Player[Constants.PLAYERS_SYMBOL.length];
	private boolean isFirstPlayer = true;
	private int currentPlayerIndex = 0;
	private ScoreManager scoreManager;

	public void run() throws IOException{
		
		scoreManager = createScoreManager();
		
		UI.printGameTitle();

		for (int i = 0; i < players.length; i++) {
			this.players[i] = createPlayer(i);;
		}
		
		boolean gameEnded = false;
		Player currentPlayer = nextPlayer();
		Player winner = null;
	
		while(!gameEnded) {
			board.print();
			try {
				boolean winnerFound = currentPlayer.play();
				if(winnerFound) {
					System.out.println("winner");
					gameEnded = true;
					winner = currentPlayer;
				}else if(board.isFull()) {
					System.out.println("full board");
					gameEnded = true;
				}
				currentPlayer = nextPlayer();
			}catch (InvalidMoveException e) {
				System.out.println(e.getMessage() + ", tente de novo.");
			}
		}
		
		if(winner == null) {
			UI.printText("Oh não, deu empate :/");
		}else {
			UI.printText("Parabéns " + winner.getName() + ", você ganhou!");
			try {
				scoreManager.saveScore(winner);
			}catch (IOException e) {
				System.out.println(e.getMessage());
			}
	
		}
		
		board.print();
		UI.printText("");
		UI.printText("FIM do JOGO");
		UI.in.close();
	}
	
	public Player createPlayer(int index) {
		String name  = UI.readInput("Nome do jogador: " + (index + 1) + " => ");
		Player player = new Player(name, board, Constants.PLAYERS_SYMBOL[index]);
		Integer score = scoreManager.getScore(player);
		
		if(score != null) {
			UI.printText("O jogador " + player.getName() + " tem " + score + " vitória(s).");
		}
		
		UI.printText("O jogador " + name + " foi criado e jogará com " + Constants.PLAYERS_SYMBOL[index]);
		return player;
	}
	
	private Player nextPlayer() {

		/*
		 * currentPlayerIndex++; if(currentPlayerIndex >= players.length) {
		 * currentPlayerIndex = 0; }
		 */
		
		if(isFirstPlayer) {
			isFirstPlayer = false;
			return players[0];
		}
		currentPlayerIndex = (currentPlayerIndex + 1) % players.length;
		System.out.println(currentPlayerIndex);
		return players[currentPlayerIndex];
	}
	
	private ScoreManager createScoreManager() throws IOException{
		return new FileScoreManager();
	}
}
