package assignment4_1;
/*
 * File: HangmanLexicon.java
 * -------------------------
 * This file contains a stub implementation of the HangmanLexicon
 * class that you will reimplement for Part III of the assignment.
 */

import java.io.*;
import java.util.ArrayList;

public class HangmanLexicon {
	/** 需要猜测单词的list*/
	ArrayList<String> wordList = new ArrayList();
	
	/** 读取HangmanLexicon.txt的单词，当该行不为空时存进wordList中*/
	public HangmanLexicon() {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader("HangmanLexicon.txt"));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String word = "";
		try {
			while((word = reader.readLine()) !=null) {
				wordList.add(word);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

/** Returns the number of words in the lexicon. */
	public int getWordCount() {
		return wordList.size();
	}

/** Returns the word at the specified index. */
	public String getWord(int index) {
		return wordList.get(index);
	};
}
