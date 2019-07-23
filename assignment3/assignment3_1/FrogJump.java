package assignment3_1;

import acm.graphics.*;
import acm.program.*;
import java.awt.event.*;

public class FrogJump extends GraphicsProgram {

	/** 一格的大小 */
	public static final int SQSIZE = 75;
	/** 列数 */
	public static final int NCOLS = 7;
	/** 行数 */
	public static final int NROWS = 3;
	/** 青蛙图片 */
	GImage frog;

	/**
	 * 绘制青蛙图片 添加鼠标监听事件
	 */
	public void run() {
		drawImage();
		addMouseListeners();
	}

	/**
	 * 绘制青蛙图片，位于面板底部中间
	 */
	private void drawImage() {
		frog = new GImage("frog.gif");
		frog.setLocation((double) getWidth() / 2 - frog.getWidth() / 2, getHeight() - frog.getHeight());
		add(frog);
	}

	/**
	 * 点击鼠标，移动青蛙，每次移动青蛙都需要判断移动是否会超出面板 先计算鼠标点击位置与青蛙中心位置的在x坐标和y坐标上的距离
	 * 如果diffX和diffY同时小于0，表明鼠标位于图片左上角，分为两种情况，距离青蛙在x上更近还是y更近，青蛙移向距离更远的方向
	 * 如果diffX小于0并且diffX的绝对值大于diffY（此时diffY大于0），表明鼠标位于图片左边稍下一点的位置，青蛙移向左边
	 * 如果diffY小于0并且diffY的绝对值大于diffX（此时diffX大于0），表明鼠标位于图片上方稍右一点的位置，青蛙移向上方
	 * 如果diffX和diffY同时大于0，表明鼠标位于图片右下角，分为两种情况，距离青蛙在x上更近还是y更近，青蛙移向距离更远的方向
	 * 如果都不满足前面的情况，并且diffX大于
	 * 0（此时diffY小于0，diffY的绝对值小于diffX），表明鼠标位于图片右边稍上一点的位置，青蛙移向右边
	 * 如果都不满足前面的情况，并且diffY大于
	 * 0（此时diffX小于0，diffX的绝对值小于diffY），表明鼠标位于图片下方稍左一点的位置，青蛙移向下方
	 */
	public void mouseClicked(MouseEvent e) {
		double diffX = e.getX() - frog.getX() - frog.getWidth() / 2;
		double diffY = e.getY() - frog.getY() - frog.getHeight() / 2;
		if (diffX < 0 && diffY < 0) {
			if (Math.abs(diffX) > Math.abs(diffY)) {
				canMove(-SQSIZE, 0);
			} else {
				canMove(0, -SQSIZE);
			}
		} else if (diffX < 0 && Math.abs(diffX) > diffY) {
			canMove(-SQSIZE, 0);
		} else if (diffY < 0 && Math.abs(diffY) > diffX) {
			canMove(0, -SQSIZE);
		} else if (diffX > 0 && diffY > 0) {
			if (diffX > diffY) {
				canMove(SQSIZE, 0);
			} else {
				canMove(0, SQSIZE);
			}
		} else if (diffX > 0) {
			canMove(SQSIZE, 0);
		} else if (diffY > 0) {
			canMove(0, SQSIZE);
		}
	}

	/**
	 * 判断青蛙的一次移动会不会超出面板，不超出则移动
	 */
	private void canMove(int x, int y) {
		if (frog.getX() + frog.getWidth() + x <= getWidth() && frog.getY() + frog.getHeight() + y <= getHeight()) {
			frog.move(x, y);
		}
	}
}

