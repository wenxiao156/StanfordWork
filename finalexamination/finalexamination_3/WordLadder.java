package finalexamination_3;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import acm.program.ConsoleProgram;

public class WordLadder extends ConsoleProgram {

	/**
	 * 初始长度为0，初始单词是“”
	 * 首先判断是不是英文单词，接着判断长度是否一致，最后判断修改的单词个数
	 * 不符合时重新输入，修改length和previous为初始值
	 * 长度为现在的单词长度，previous为现在的单词
	 */
	public void run() {
		println("Program to check a word ladder.");
		println("Enter a sequence of words ending with a blank line.");
		int length = 0;
		String previous = "";
		while(true) {
			String line = readLine();
			if(line.isEmpty()) break;
			if(!isEnglishWord(line) || (length != 0 && line.length()!=length) || (!previous.isEmpty() && !checkDifferNum(previous, line))) {
				println("That word is not legal. Try again.");
				length = 0;
				previous = "";
				continue;
			}
			length = line.length();
			previous = line;
		}
	}
	
	/**
	 * 判断是否全是英文字母
	 */
	public boolean isEnglishWord(String word) {
		if(word.matches("^[a-zA-Z]+")) {
			return true;
		}
		return false;
	}
	
	/**
	 * 对不同的字母进行计数，超过1返回false
	 */
	public boolean checkDifferNum(String previous, String next) {
		int count = 0;
		for(int i = 0; i < previous.length(); i++) {
			if(previous.charAt(i) != next.charAt(i)) {
				count++;
			}
		}
		if(count > 1) {
			return false;
		}
		return true;
	}
}
