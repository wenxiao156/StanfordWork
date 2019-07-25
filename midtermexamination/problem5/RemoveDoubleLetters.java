package problem5;

import acm.program.ConsoleProgram;

public class RemoveDoubleLetters extends ConsoleProgram{
	
	/**
	 * 获取用户输入的字符串，传进removeDouble方法去重
	 */
	public void run() {
		println("Enter a word!");
		StringBuilder str = new StringBuilder(readLine("?"));
		println(removeDouble(str));
	}
	
	/**
	 * 当当前char与下一个char相同时，删除当前char，并让i减1，从当前位置重新判断，同时让length减1，当i达到length减1时，退出循环，防止越界
	 */
	private StringBuilder removeDouble(StringBuilder word) {
		for(int i = 0; i < word.length()-1; i++) {
			if(word.charAt(i) == word.charAt(i+1)) {
				word.delete(i,i+1);
				i--;
			}
		}
		return word;
	}
}
