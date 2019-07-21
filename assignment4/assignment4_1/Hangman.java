package assignment4_1;
/*
 * File: Hangman.java
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.awt.*;

public class Hangman extends ConsoleProgram {
	
	private RandomGenerator rgen = RandomGenerator.getInstance();
	
	private static final int GUESS = 8;
	
	private String wordPrompt = "";
	
	private String word;
	
	private HangmanCanvas canvas;
	
	public void init() {
		canvas = new HangmanCanvas();
		add(canvas);
		System.out.println(getWidth());
		System.out.println(getHeight());
		canvas.reset(getWidth() / 2,getHeight());
		println("Welcome to Hangman!");
		HangmanLexicon lexicon = new HangmanLexicon();
		word = lexicon.getWord(rgen.nextInt(0, lexicon.getWordCount()));
		println(word);
		initWordPrompt(word.length());
	}
	
    public void run() {
		int guessCount = 0;
		while(guessCount < GUESS && !word.equals(wordPrompt)) {
			println("The word now looks like this: " + wordPrompt);
			println("You have " + (GUESS - guessCount) + " guesses left.");
			String guessWord = readLine("You guess: ");
			while(!guessWord.matches("\\w")) {
				println("You should guess one word!");
				guessWord = readLine("You guess: ");
			}
			if(!updateWordPrompt(word,guessWord)) {
				guessCount++;
			}
		}
		displayResult();
	}
    
    private void initWordPrompt(int length) {
		for(int i = 0; i < length; i++) {
			wordPrompt += "-";
		}
	}
    
    private boolean updateWordPrompt(String word, String guessWord) {
    	if(word.indexOf(guessWord.toUpperCase()) != -1) {
    		println("That guess is corrent!");
    		wordPrompt = wordPrompt.substring(0, word.indexOf(guessWord.toUpperCase())) + guessWord.toUpperCase() + wordPrompt.substring(word.indexOf(guessWord.toUpperCase()) + 1);
    		return true;
    	} else {
    		println("There no " + guessWord + "'s in the word.");
    	}
    	return false;
    }
    
    private void displayResult() {
    	if(word.equals(wordPrompt)) {
			println("You guessed the word: " + word);
			println("You win!");
		} else {
			println("You're completely hung.");
			println("The word was: " + word);
			println("You lose!");
		}
    }
}
