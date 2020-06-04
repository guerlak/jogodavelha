package com.guerlak.jogodavelha.score;

import java.io.IOException;

import com.guerlak.jogodavelha.core.Player;

public interface ScoreManager {
	
	public void saveScore(Player player) throws IOException;
	public Integer getScore(Player player) ;
	
}
