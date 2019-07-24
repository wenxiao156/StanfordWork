package session5_3;

import java.util.*;

import acm.program.ConsoleProgram;
/**
 * 用户不输入名字时退出循环，输出用户输入的不相同的名字
 * 使用set保存名字
 */
public class UniqueNames extends ConsoleProgram{
	public void run() {
		Set<String> nameList = new LinkedHashSet<String>(); //LinkedHashSet可以保存用户输入的顺序
		while(true) {
			String name = readLine("Enter name: ");
			if(name.isEmpty()) break;
			nameList.add(name);
		}
		Iterator<String> iter = nameList.iterator();
		while(iter.hasNext()) {
			println(iter.next());
		}
	}
}
