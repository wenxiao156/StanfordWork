package assignment2_1;
/*
 * File: Pyramid.java
 * Name: 
 * Section Leader: 
 * ------------------
 * This file is the starter file for the Pyramid problem.
 * It includes definitions of the constants that match the
 * sample run in the assignment, but you should make sure
 * that changing these values causes the generated display
 * to change accordingly.
 */

import acm.graphics.*;
import acm.program.*;

public class Pyramid extends GraphicsProgram {

/** Width of each brick in pixels */
	private static final int BRICK_WIDTH = 30;

/** Width of each brick in pixels */
	private static final int BRICK_HEIGHT = 12;

/** Number of bricks in the base of the pyramid */
	private static final int BRICKS_IN_BASE = 11;
	
	
	/** 从底层开始一行行地放长方形 */	
	public void run() {
		for (int i = 0; i < BRICKS_IN_BASE; i++) {
			putOneRow(i);
		}
	}
	
	/** 参数num为位于第几行，从0到BRICKS_IN_BASE-1，BRICKS_IN_BASE - num为该行的长方形个数
	 *  先计算每一行第一个长方形的坐标，x坐标是画板的宽的一半减去该行长方形个数的一半乘以长方形的宽，y坐标是画板的高减去行数+1乘以长方形的高 
	 *  接着画每一行的每一个长方形，同一行的y坐标相同，x坐标会增加count个BRICK_WIDTH，count为该行第几个长方形
	 * */		
	private void putOneRow(int num) {
		double x = (double) getWidth() / 2 - ((double)(BRICKS_IN_BASE - num) / 2) * BRICK_WIDTH ;
        double y = (double) getHeight() - (num + 1) * BRICK_HEIGHT;
        int count = 0;
        for (int i = BRICKS_IN_BASE - num ; i > 0; i--) {
        	GRect rect = new GRect(x + (count++) * BRICK_WIDTH, y, BRICK_WIDTH, BRICK_HEIGHT);
        	add(rect);
        }    
	}
}

