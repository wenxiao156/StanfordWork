package assignment2_6;
/*
 * File: Hailstone.java
 * Name: 
 * Section Leader: 
 * --------------------
 * This file is the starter file for the Hailstone problem.
 */

import acm.program.*;

public class Hailstone extends ConsoleProgram {

	/**
	 * 用count计数，当n不为1时，判断n的奇偶，偶数除以2，计数乘以3加1，并让n等于计算后的值
	 */
	public void run() {
		int n = readInt("Enter a number: ");
		int count = 0;
		while (n != 1) {
			if (n % 2 == 0) {
				println(n + " is even so I take a half: " + n / 2);
				n = n / 2;
			} else {
				println(n + " is odd, so I make 3n+1: " + (3 * n + 1));
				n = 3 * n + 1;
			}
			count++;
		}
		println("The process took " + count + " to reach 1");
	}
}
