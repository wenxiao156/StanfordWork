package problem1;
/*
 * File: CollectNewspaperKarel.java
 * --------------------------------
 * At present, the CollectNewspaperKarel subclass does nothing.
 * Your job in the assignment is to add the necessary code to
 * instruct Karel to walk to the door of its house, pick up the
 * newspaper (represented by a beeper, of course), and then return
 * to its initial position in the upper left corner of the house.
 */

import stanford.karel.*;

public class CreateBorder extends SuperKarel {
	/*
	 * 1、karel走到第一个需要放置beeper的点 2、放置每一行beeper
	 */
	public void run() {
		moveToFirstPoint();
		for (int i = 0; i < 4; i++) {
			putOneRow();
		}
	}

	/*
	 * 前：karel位于world右下角，朝向东 直走，转左，直走，转右 后：karel位于第一个需要放置beeper的位置，朝向东
	 */
	private void moveToFirstPoint() {
		move();
		turnLeft();
		move();
		turnRight();
	}

	/*
	 * 前：karel位于需要放置边界的拐点处，朝向需要前进的方向 如果前面没有墙，先放置beeper，直走，最后一个位置不会放置beeper
	 * 如果已经有beeper存在，先把beeper捡起再放下一个 后：karel位于需要放置边界的拐点处，朝向需要前进的方向
	 */
	private void putOneRow() {
		while (frontIsClear()) {
			if (beepersPresent()) {
				while (beepersPresent()) {
					pickBeeper();
				}
			}
			putBeeper();
			move();
		}
		turnAround();
		move();
		turnRight();
	}
}
