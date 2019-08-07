package finalexamination_5;

import java.util.*;

public class StringQueue implements MinimalStringQueue{
	
	private ArrayList<String> queue = new ArrayList<String>();
	
	/**
	 * 往队尾增加元素
	 */
	@Override
	public void add(String str) {
		queue.add(str);
	}
	
	/**
	 * 返回删除的queue中的第一个元素,queue中的元素个数为0时返回null
	 */
	@Override
	public String poll() {
		if(queue.size() >= 1) {
			return queue.remove(0);
		}
		return null;
	}
	
	/**
	 * 返回queue的size
	 */
	@Override
	public int size() {
		return queue.size();
	}

}
