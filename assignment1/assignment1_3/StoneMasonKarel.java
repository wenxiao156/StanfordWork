package assignment1_3;
/*
 * File: StoneMasonKarel.java
 * --------------------------
 * The StoneMasonKarel subclass as it appears here does nothing.
 * When you finish writing it, it should solve the "repair the quad"
 * problem from Assignment 1.  In addition to editing the program,
 * you should be sure to edit this comment so that it no longer
 * indicates that the program does nothing.
 */

import stanford.karel.*;

//public class StoneMasonKarel extends SuperKarel {
///*
//* 只适用每列需要填补的数量一致的情况：
//* 1、先走第一列
//* 2、当右边没有墙循环进行：转右到另一列，走完这一列，转左到另一列，走完这一列
//*/
//	public void run() {
//		turnLeft();
//		putOneRow();
//		while(rightIsClear()) {
//			turnRight();
//			if(toAnotherRow()) {  //可能还没走到需要填补的那一列world就已经结束,转左退出循环
//				turnRight();
//				putOneRow();
//				if(leftIsClear()) { //补完一列之后需判断world是否已经结束，结束即turnAround退出循环
//					turnLeft();
//					if(toAnotherRow()) {
//						turnLeft();
//						putOneRow();
//					} else {
//						turnLeft();
//					}
//					
//				} else {
//					turnAround();
//				}	
//			} else {
//				turnLeft(); 
//			}	
//		}		
//	}
///*
//* 前：karel位于起点，朝向需要前进的方向
//* karel先检查当前位置是否有beeper，没有beeper的格子即放下beeper
//* 当前面没有墙的时候，karel往前走并检查是否需要填补beeper
//* 后：位于终点，朝向前进方向
//*/	
//	private void putOneRow() {
//		if(!beepersPresent()) {
//			putBeeper();
//		}
//		while(frontIsClear()) {
//			move(); 
//			if(!beepersPresent()) {
//				putBeeper();
//			}
//		}
//	}
///*
//* 前：karel朝东
//* 当karel可以前进4个格到达需要填补的另一列返回true，否则返回false
//* 后：karel朝东
//*/		
//	private boolean toAnotherRow() {
//		for(int i = 0 ; i < 4; i++){
//			if(frontIsClear()) {
//				move();
//			} else {
//				return false;
//			}
//		}
//		return true;
//	}
//
//}

public class StoneMasonKarel extends SuperKarel {
	/*
	 * 适用需要填补beeper数量不一致的情况
	 * 1、先填补第一列，返回到起点，turnAround
	 * 2、当右边没有墙循环进行：转右到另一列，填补该列
	 */
	public void run() {
		putOneRowAndReturn();
		while(rightIsClear()) {
			if(toAnotherRow()) {  //可能还没走到需要填补的那一列world就已经结束,转左退出循环
				putOneRowAndReturn();	
			} else {
				turnLeft(); 
			}	
		}		
	}
	/*
	 * 前：karel位于起点，朝东
	 * karel先检查当前位置是否有beeper，没有beeper的格子即放下beeper
	 * 当前面没有墙的时候，karel往前走并检查是否需要填补beeper
	 * karel返回起点，turnAround
	 * 后：位于起点，朝北
	 */	
	private void putOneRowAndReturn() {
		turnLeft();
		if(!beepersPresent()) {
			putBeeper();
		}
		while(frontIsClear()) {
			move(); 
			if(!beepersPresent()) {
				putBeeper();
			}
		}
		turnAround();
		while(frontIsClear()) {
			move(); 
		}
		turnAround();
	}
	/*
	 * 前：karel朝北
	 * 当karel可以前进4个格到达需要填补的另一列返回true，否则返回false
	 * 后：karel朝东
	 */			
	private boolean toAnotherRow() {
		turnRight();
		for(int i = 0 ; i < 4; i++){
			if(frontIsClear()) {
				move();
			} else {
				return false;
			}
		}
		return true;
	}

}
