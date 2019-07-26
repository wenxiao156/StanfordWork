package session6_2;

import java.util.HashMap;

import acm.program.ConsoleProgram;

public class NameCounts extends ConsoleProgram {
	/** hashmap实例变量，记录名字和出现的次数 */
	private HashMap<String, Integer> map = new HashMap<String, Integer>();

	/**
	 * 记录用户输入的名字，展示名字和出现的次数
	 */
	public void run() {
		readNames();
		displayResult();
	}

	/**
	 * 记录用户输入的名字，当用户输入的名字为空时退出循环 如果hashmap中包含这个名字，即取出名字的次数+1
	 * 否则将名字和次数1保存进hashmap中
	 */
	private void readNames() {
		while (true) {
			String name = readLine("Enter name: ");
			if (name.isEmpty())
				break;
			if (map.containsKey(name)) {
				map.put(name, map.get(name) + 1);
			} else {
				map.put(name, 1);
			}
		}
	}

	/**
	 * 使用foreach循环读取hashmap中记录的名字和出现的次数
	 */
	private void displayResult() {
		for (String name : map.keySet()) {
			println("Entry [" + name + "] has count " + map.get(name));
		}
	}
}
