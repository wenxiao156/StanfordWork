package session5_1;

import java.io.*;

public class WordCount {
	public void run() {
		BufferedReader reader = null;
		int lines = 0;
		int words = 0;
		int chars = 0;
		try {
			reader = new BufferedReader(new FileReader("lear.txt"));
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
	}
}
