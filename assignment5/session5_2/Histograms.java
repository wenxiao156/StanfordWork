package session5_2;

import java.io.*;

import acm.program.ConsoleProgram;

/**
 * 柱形图输出考试成绩范围 获取考试范围文件，用counts数组记录每个范围的个数 最后输出柱形图
 */
public class Histograms extends ConsoleProgram {
	public void run() {
		BufferedReader reader = readFile();

		int[] counts = new int[11];
		for (int i = 0; i < counts.length; i++) {
			counts[i] = 0;
		}

		countScores(reader, counts);

		try {
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		display(counts);
	}

	/**
	 * 获取文件
	 */
	private BufferedReader readFile() {
		BufferedReader reader = null;
		while (true) {
			try {
				String fileName = readLine("请输入文件名字：");
				reader = new BufferedReader(new FileReader(fileName));
				if (reader != null)
					break;
			} catch (FileNotFoundException e) {
				println("请输入有效的文件名字：");
			}
		}
		return reader;
	}

	/**
	 * 根据文件中分数的范围改变counts数组中相应的记录
	 */
	private void countScores(BufferedReader reader, int[] counts) {
		try {
			while (true) {
				String scoreStr = reader.readLine();
				if (scoreStr == null)
					break;
				int score = Integer.valueOf(scoreStr);
				if (score > 100 || score < 0) { // 从后往前记录可以减少输入的范围
					println("分数超过取值范围，请检查！");
				} else {
					counts[score / 10]++; // 分数除以10就是counts的数组下标
				}

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 用rang数组保存成绩范围方便输出 根据counts数组的记录输出相应个数的*
	 */
	private void display(int[] counts) {
		String[] range = { "00-09: ", "10-19: ", "20-29: ", "30-39: ", "40-49: ", "50-59: ", "60-69: ", "70-79: ",
				"80-89: ", "90-99: ", "100: " };
		for (int j = 0; j < counts.length; j++) {
			print(range[j]);
			String star = "";
			for (int k = 0; k < counts[j]; k++) {
				star += "*";
			}
			print(star);
			println("");
		}
	}
}
