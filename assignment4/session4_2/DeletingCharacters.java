package session4_2;

import acm.program.ConsoleProgram;

public class DeletingCharacters extends ConsoleProgram{
	/**
	 * 如果输入了字符串则使用replaceAll方法代替str中存在的所有deleteStr
	 */
	public void run() {
		while (true) {
			String str = readLine("Enter a string: ");
			String deleteStr = readLine("Enter a string that you want to remove: ");
			if (str.trim().length() == 0 || deleteStr.trim().length() == 0)
				break;
			println(str.replaceAll(deleteStr, ""));
			  
		}
		println("请输入字符串！");
	}
}
