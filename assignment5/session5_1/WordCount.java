package session5_1;

import java.io.*;

import acm.program.ConsoleProgram;

/**
 * 计算文件中的行数、字符数和单词数
 * 用户输入的文件名出错，重新输入直至正确
 * 单词数为连续的单词或数字的个数
 * 字符数为每一行字符串的长度
 */
public class WordCount extends ConsoleProgram {
	public void run() {
		BufferedReader reader = null;
		int lines = 0;
		int words = 0;
		int chars = 0;
		while (true) {
			try {
				String fileName = readLine("File: ");
				reader = new BufferedReader(new FileReader(fileName));
				if (reader != null)
					break;
			} catch (FileNotFoundException e) {
				println("文件名出错，请重新输入！");
				// e.printStackTrace();
			}
		}
		try {
			while (true) {
				String line = reader.readLine();
				if (line == null)
					break;
				lines++;
				chars += line.length();
				words += countWords(line);
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
		println("Lines: " + lines);
		println("Words: " + words);
		println("Chars: " + chars);
	}
	
	/**
	 * 返回每一行的单词个数
	 * 使用flag判断是否在单词中，在有多个分隔单词的字符存在时只计数一次
	 */
	private int countWords(String line) {
		int count = 0;
		boolean flag = false;
		for (int index = 0; index < line.length(); index++) {
			if (Character.isLetterOrDigit(line.charAt(index))) {
				flag = true;
			} else {
				if(flag) {
					count++;
				}
				flag = false;
			}
		}
		if(flag) { //上面循环中最后一个单词没有计数
			count++;
		}
		return count;
	}
}
