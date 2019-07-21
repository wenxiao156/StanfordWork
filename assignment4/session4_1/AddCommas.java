package session4_1;

import acm.program.ConsoleProgram;

public class AddCommas extends ConsoleProgram {
	/**
	 * 当输入的字符串不是数字或者长度为0，退出循环
	 */
	public void run() {
		while (true) {
			String num = readLine("Enter a numeric string: ");
			if (num.trim().length() == 0 || !num.trim().matches("^\\d+$"))
				break;
			println(addCommasToNumericString(num.trim()));
		}
		println("请输入合法的数字！！！");
	}

	/**
	 * 从最后一位开始算起，当可以被3整除并且不是第一位时，增加一个“，”
	 */
	private String addCommasToNumericString(String digits) {
		if (digits.length() <= 3) {
			return digits;
		}
		String afterStr = "";
		int count = 1;
		for (int i = digits.length(); i > 0; i--) {
			afterStr = digits.charAt(i - 1) + afterStr;
			if (count % 3 == 0 && count != digits.length()) {
				afterStr = "," + afterStr;
			}
			count++;
		}
		return afterStr;
	}
}
