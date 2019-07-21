package session1;

import stanford.karel.*;

public class DefendDemocracyKarel extends SuperKarel{
	/*
	 *1、当karel前面没有阻挡，向前进入投票区
	 *2、判断是否需要清理chad
	 */
	public void run() {
		while (frontIsClear()) {
			move();
			cleanChad();
		}		
	}
	
	/*
	 *前：karel朝东，位于投票矩形中间
	 *如果当前位置存在beeper，不需要进行任何操作
	 *如果当前位置不存在beeper，转左，清理投票矩形最上方beepers，接着清理投票矩形最下方的beepers，清理完成即转右朝东
	 *后：karel朝东，位于投票矩形中间
	 */
	private void cleanChad() {
		if (!beepersPresent()) {
			turnLeft();
			toPickBeepers();
			toPickBeepers();
			turnRight();
		}
	}
	/*
	 *前：karel朝向需要前进的方向，位于投票矩形中间
	 *进入投票矩形最上方/最下方，当有beepers存在就pickBeeper，清理完成转身进入投票矩形中间
	 *后：karel朝南/北，位于投票矩形中间
	 */
	private void toPickBeepers() {
		move();
		while (beepersPresent()) {
			pickBeeper();
		}
		turnAround();
		move();
	}
}
