package assignment1_2;
/*
 * File: CheckerboardKarel.java
 * ----------------------------
 * When you finish writing it, the CheckerboardKarel class should draw
 * a checkerboard using beepers, as described in Assignment 1.  You
 * should make sure that your program works for all of the sample
 * worlds supplied in the starter folder.
 */

import stanford.karel.*;

/**
* @author huangwenxiao
*/
public class CheckerboardKarel extends SuperKarel {
	
	/**
	 * 1、先放第一行
	 * 2、如果左边没遇到墙，就循环执行：左转向上，放一行，当右边没有墙则右转向上，放一行，否则转身使循环结束
	 * 3、转左使karel朝北
	 */
	@Override
	public void run() {
		putOneRow();
		while(leftIsClear()) {
			goLeftUp();
			putOneRow();
			if(rightIsClear()) {
				goRightUp();
				putOneRow();
			} else {
				turnAround();
			}
		}
		turnLeft();
	}
	/**
	 * 前：karel位于需要放beeper的第一格，朝向需要前进的方向
	 * karel先放置第一个beeper，使用count进行计数，空一个位置放一个beeper，直到遇到墙停止
	 * 后：karel位于该行最后一格，朝向前进的方向
	 */
	private void putOneRow() {
		int count = 1;
		putBeeper();
		while(frontIsClear()) {			
			move();
			if(count%2 == 0) {
				putBeeper();
			}
			count++;
		}
	}
	/**
	 * 前：karel朝东，位于当行最后一个位置
	 * 转左，需要分两种情况，当前位置是否有beeper。
	 * 如果有，当前面没有墙阻挡时向前，转左，前进一格，这是因为putOneRow方法会从需要放置beeper的第一个位置开始
	 * 如果没有，只需当前面没有墙阻挡时向前，转左
	 * 后：karel位于上一行需要放置beeper的第一个位置，朝向需要前进的方向
	 */
	private void goLeftUp(){
		turnLeft();
		if(beepersPresent()) {
			if(frontIsClear()) {
				move();
			}
			turnLeft();
			if(frontIsClear()) {
				move();
			} else {  //如果只有一列，需要转回原本方向向上一格
				turnRight();
				if(frontIsClear()) {
					move();
				}
			}
		} else {
			if(frontIsClear()) {
				move();
			}
			turnLeft();
		}
	}
	/**
	 * 前：karel朝西，位于当行第一个位置
	 * 与goLeftUp方法类似
	 * 后：karel位于上一行需要放置beeper的第一个位置，朝向需要前进的方向
	 */
	private void goRightUp() {
		turnRight();
		if(beepersPresent()) {			
			if(frontIsClear()) {
				move();
			}
			turnRight();
			if(frontIsClear()) {	
				move();
			} else { //如果只有一列，需要转回原本方向向上一格
				turnLeft();
				if(frontIsClear()) {
					move();
				}
			}
		} else {
			if(frontIsClear()) {
				move();
			}
			turnRight();
		}
	}

}
