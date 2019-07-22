package assignment4_1;
/*
 * File: Hangman.java
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */

//import acm.graphics.*;
import acm.program.*;
import acm.util.*;

//import java.awt.*;

public class Hangman extends ConsoleProgram {
    /** 随机获取单词*/
	private RandomGenerator rgen = RandomGenerator.getInstance();
	
	/** 最多猜错次数*/
	private static final int GUESS = 8;
	
	/** 提示单词*/
	private String wordPrompt = "";
	
	/**需要猜测的单词 */
	private String word;

	/** 显示hangman的面板*/
	private HangmanCanvas canvas;
	
	/** 
	 * 初始化游戏
	 * 加入显示hangman的面板，获取需要猜测的单词，显示单词的提示
	 */
	public void init() {
		canvas = new HangmanCanvas();
		add(canvas);
		canvas.reset(getWidth() / 2, getHeight());
		println("Welcome to Hangman!");
		HangmanLexicon lexicon = new HangmanLexicon();
		word = lexicon.getWord(rgen.nextInt(0, lexicon.getWordCount() - 1));
		println(word);
		initWordPrompt(word.length());
		canvas.displayWord(wordPrompt);
	}

	/** 
	 * 开始进行游戏
	 * 设置猜错次数为0，当猜错次数小于GUESS并且还没猜对单词时，根据用户输入的字母匹配需要猜测的单词，更新提示单词
	 * 猜测失败则在canvas展示用户猜错了的单词，猜错次数加1
	 * 最后展示游戏结果
	 */
	public void run() {
		int guessCount = 0;
		while (guessCount < GUESS && !word.equals(wordPrompt)) {
			println("The word now looks like this: " + wordPrompt);
			println("You have " + (GUESS - guessCount) + " guesses left.");
			String guessWord = readLine("You guess: ");
			while (!guessWord.matches("\\w")) {  //输入的字母不合法，重新获取字母直到单词合法
				println("You should guess one word!");
				guessWord = readLine("You guess: ");
			}
			if (!updateWordPrompt(word, guessWord.charAt(0))) {
				canvas.noteIncorrectGuess(guessWord.charAt(0), guessCount + 1);
				guessCount++;	
			}
		}
		displayResult();
	}
	
	/** 
	 * 根据需要猜测的单词的长度拼接提示单词
	 */
	private void initWordPrompt(int length) {
		for (int i = 0; i < length; i++) {
			wordPrompt += "-";
		}
	}
	
	/** 
	 * 更新提示单词
	 * 如果用户猜测错误，直接返回
	 * 如果用户猜对，分为单词中只有一个该字母和单词中有一个以上该字母
	 * 只有一个该字母时，直接拼接提示单词
	 * 否则需要循环找出单词中字母所在位置，再一个个地进行拼接
	 * 最后更新展示hangman面板中的提示单词
	 */
	private boolean updateWordPrompt(String word, char guessWord) {
		guessWord = Character.toUpperCase(guessWord);
		if (word.indexOf(guessWord) < 0) {
			println("There no " + guessWord + "'s in the word.");
			return false;
		}
		println("That guess is corrent!");
		int firstIndex = word.indexOf(guessWord);
		int lastIndex = word.lastIndexOf(guessWord);
		char[] wordPromptCharArr = wordPrompt.toCharArray();
		if (firstIndex != lastIndex) { //当字母在单词中出现第一次和最后一次的位置不相同时，单词中包含不止一个该字母 
			for (int i = firstIndex; i <= lastIndex; i++) {
				if (guessWord == word.charAt(i)) {
					wordPromptCharArr[i] = guessWord;
				}
			}
		} else {
			wordPromptCharArr[firstIndex] = guessWord;
		}
		wordPrompt = String.valueOf(wordPromptCharArr);
		canvas.displayWord(wordPrompt);
		return true;

	}
	

	/** 
	 * 展示游戏结果
	 * 提示单词与需要猜测的单词相同则成功，否则失败
	 */
	private void displayResult() {
		if (word.equals(wordPrompt)) {
			println("You guessed the word: " + word);
			println("You win!");
		} else {
			println("You're completely hung.");
			println("The word was: " + word);
			println("You lose!");
		}
	}
}
