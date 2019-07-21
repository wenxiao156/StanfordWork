package assignment1_1;
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

public class CollectNewspaperKarel extends SuperKarel {
	/* 
	 * 1、移至beeper处
	 * 2、捡起报纸
	 * 3、返回原地
	 */
	public void run(){
		moveToNewsPaper();
		pickUPNewsPaper();
		returnBack();
	}
	/* 
	 * 前：karel位于world左上角，朝向东
	 * 直走，直走，转右，直走，转左，直走
	 * 后：karel位于beeper处，朝向东
	 */
	private void moveToNewsPaper(){
		move();
		move();
		turnRight();
		move();
		turnLeft();
		move();
	}
	/* 
	 * 前：karel位于beeper处，朝向东
	 * 捡起beeper，转身
	 * 后：karel位于beeper处，朝向西
	 */
	private void pickUPNewsPaper(){
		pickBeeper();
		turnAround();
	}
		/* 
	 * 前：karel位于beeper处，朝向西
	 * 直走，直走，转右，直走，转左，直走，转身
	 * 后：karel位于world左上角，朝向东
	 */
	private void returnBack() {
		moveToNewsPaper();
		turnAround();
	}
}
