package problem3;
/*
 * File: CheckerboardKarel.java
 * ----------------------------
 * When you finish writing it, the CheckerboardKarel class should draw
 * a checkerboard using beepers, as described in Assignment 1.  You
 * should make sure that your program works for all of the sample
 * worlds supplied in the starter folder.
 */

import java.util.ArrayList;
import java.util.Iterator;

import acm.program.ConsoleProgram;

public class FindMax extends ConsoleProgram {

	/** 终点值 */
	private static final int SENTINEL = 0;

	/**
	 * 找出最大和第二大的数 
	 * 使用ArrayList保存输入的数，初始化最大值和第二大的值为0
	 * 如果当前数值大于max时，设置secondLargest为max，max为当前数值
	 * 否则如果当前数值大于secondLargest时，设置secondLargest为当前数值
	 */
	public void run() {
		println("This program finds the two lagest integers in a list. Enter values, one per line, using a" + SENTINEL
				+ " to signal the end of the list.");
		
		int max = 0;
		int secondLargest = 0;
		while (true) {
			int num = readInt("?");
			if (num == SENTINEL)
				break;
			if (max < num) {
				secondLargest = max;
				max = num;
			} else if (secondLargest < num) {
				secondLargest = num;
			}
		}
		println("The largest value is" + max);
		println("The second largest is" + secondLargest);
	}
}
