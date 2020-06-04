package com.guerlak.jogodavelha.score;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.guerlak.jogodavelha.core.Player;

public class FileScoreManager implements ScoreManager {
	
	public FileScoreManager() throws IOException{
		setup();
	}

	private static final Path SCORE_FILE = Paths.get("score.txt");
	private Map<String, Integer> scoreMap = new HashMap<>();

	private void setup() throws IOException {
		if (!Files.exists(SCORE_FILE)) {
			Files.createFile(SCORE_FILE);
		}
		try(BufferedReader reader  = Files.newBufferedReader(SCORE_FILE)){
			String line;
			while((line = reader.readLine()) != null) {
			
				String[] tokens = line.split("\\|");
				String name = tokens[0];
				int score = Integer.parseInt(tokens[1]);
				scoreMap.put(name, score);
			}
		}
	}

	@Override
	public void saveScore(Player player) throws IOException {
		Integer score = getScore(player);
		if(score == null) {
			score = 0;
		}

		scoreMap.put(player.getName().toUpperCase(), score + 1);
		
		try(BufferedWriter writer = Files.newBufferedWriter(SCORE_FILE)){
			
			Set<Map.Entry<String, Integer>> entries = scoreMap.entrySet();
			
			for (Entry<String, Integer> entry : entries) {
				String name = entry.getKey();
				Integer value = entry.getValue();
				writer.write(name + "|" + value);
				writer.newLine();
			}	
		}
	}

	@Override
	public Integer getScore(Player player) {
		return scoreMap.get(player.getName().toUpperCase());
	}

}
