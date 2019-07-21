package assignment1_4;
/*
 * File: MidpointFindingKarel.java
 * -------------------------------
 * When you finish writing it, the MidpointFindingKarel class should
 * leave a beeper on the corner closest to the center of 1st Street
 * (or either of the two central corners if 1st Street has an even
 * number of corners).  Karel can put down additional beepers as it
 * looks for the midpoint, but must pick them up again before it
 * stops.  The world may be of any size, but you are allowed to
 * assume that it is at least as tall as it is wide.
 */

import stanford.karel.*;

public class MidpointFindingKarel extends SuperKarel {
	/*
	 * 1、先找到一共有多少列
	 * 2、返回一半
	 * 3、放下beeper
	 */
	public void run() {
		int column = findColumns();
		returnToMid(column);
		putBeeper();
	}
	
	/*
	 * 前：karel面向东，位于第一格
	 * 使用count计数，当前面无阻挡前进一格并将count加1
	 * 后：karel面向东，位于当行最后一格
	 */
	private int findColumns() {
		int count = 1;
		while(frontIsClear()) {
			move();
			count++;
		}
		return count;
	}
	
	/*
	 * 前：karel面向东，位于当行最后一格
	 * karel转身，前进count/2格
	 * 后：karel面向西，位于当行中间格
	 */	
	private void returnToMid(int count) {
		turnAround();
		for(int i = 0 ; i < count/2; i++) {
			move();
		}
	}

}
