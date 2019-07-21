package section2;

import acm.program.ConsoleProgram;

public class FibonacciSequence extends ConsoleProgram {

	/**
	 * 设置最终值
	 */
	private static final int MAX_TERM_VALUE = 2;

	/**
	 * 首先给定初始值0和1，当n1，n2的和小于MAX_TERM_VALUE时，循环让n1等于n2，n2等于n1+n2，输出n1
	 * 
	 */
	public void run() {
		println("This program lists the Fibonacci sequence.");
		int n1 = 0;
		int n2 = 1;
		int n = n1 + n2;
		/* 输出第一个值 */
		println(n1);
		while (n < MAX_TERM_VALUE) {
			n1 = n2;
			n2 = n;
			n = n1 + n2;
			println(n1);
		}
		/* 当MAX_TERM_VALUE小于1或者等于1时，不能输出n2（n2初始值为1） */
		if (n2 < MAX_TERM_VALUE) {
			println(n2);
		}
	}
}
