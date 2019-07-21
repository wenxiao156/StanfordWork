package assignment2_4;
/*
 * File: PythagoreanTheorem.java
 * Name: 
 * Section Leader: 
 * -----------------------------
 * This file is the starter file for the PythagoreanTheorem problem.
 */

import acm.program.*;

public class PythagoreanTheorem extends ConsoleProgram {
	/**
	 * 获取用户输入的a、b值进行开平方运算，输出结果
	 */
	public void run() {
		println("Enter values to compute Pythagorean theorem.");
		int a = readInt("a: ");
		int b = readInt("b: ");
		double c = Math.sqrt(a * a + b * b);
		println("c = " + c);
	}
}
