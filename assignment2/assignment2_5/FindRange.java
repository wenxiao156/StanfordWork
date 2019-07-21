package assignment2_5;
/*
 * File: FindRange.java
 * Name: 
 * Section Leader: 
 * --------------------
 * This file is the starter file for the FindRange problem.
 */

import acm.program.*;

public class FindRange extends ConsoleProgram {
	
	/** 终止标志*/
	private static final int SENTINEL = 0;
	
	/**
	 * 判断输入的第一个值是否等于SENTINEL，等于则程序结束，输出提示
	 * 让最初的最大值和最小值等于用户输入的第一个值，当输入的值不为SENTINEL时不断地比较刷新最大值和最小值
	 */
	public void run() {
		println("This program finds the largest and smallest numbers.");
		int num = readInt("? ");
		int max = num;
		int min = num;
		if (num == SENTINEL){
			println("You enter the sentinel on the very first input line, then no values have been entered");
		} else {
			while (num != SENTINEL) {
				num = readInt("? ");
				/*让输入的终止标志不参与最大值最小值计算 */
				if (num != SENTINEL) { 
					max = Math.max(max, num);
					min = Math.min(min, num);
				}
			}
			println("smallest: " + min);
			println("largest: " + max);
		}
		
	}
}

