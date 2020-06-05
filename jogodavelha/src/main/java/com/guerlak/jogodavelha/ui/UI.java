package com.guerlak.jogodavelha.ui;
import java.util.Scanner;

public class UI {
	
	public static Scanner in = new Scanner(System.in);
	
	public static void printText(String text) {
		System.out.println(text);
	}
	
	public static void printTextWithNoNewLine(String text) {
		System.out.print(text);
	}
	
	public static void printNewLine() {
		System.out.println();
	}
	
	public static void printGameTitle() {
		System.out.println("####################");
		System.out.println(" | JOGO DA VELHA |");
		System.out.println("####################");
		System.out.println();
	}
	
	public static String readInput(String text) {
		System.out.println();
		printTextWithNoNewLine(text + " "); 
		String str = in.nextLine();
		return str;
	}
	
}
