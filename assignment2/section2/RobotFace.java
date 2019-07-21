package section2;
import java.awt.Color;

import acm.graphics.*;
import acm.program.GraphicsProgram;

public class RobotFace extends GraphicsProgram{
	
	/** 头部的宽度 */
	private static final int HEAD_WIDTH = 150;
	
	/** 头部的高度 */
	private static final int HEAD_HEIGHT = 300;
	
	/** 眼睛的半径 */
	private static final int EYE_RADIUS = 15;
	
	/** 嘴巴的宽度 */
	private static final int MOUTH_WIDTH = 80;
	
	/** 嘴巴的高度 */
	private static final int MOUTH_HEIGHT = 30;
		
	/**
	 * 需要先找到头部的坐标，绘制头部，眼睛，嘴巴
	 */
	public void run() {
		double headX = (double)getWidth() / 2 - (double)HEAD_WIDTH / 2;
		double headY = (double)getHeight() / 2 - (double)HEAD_HEIGHT / 2;
		drawHead(headX, headY);
		drawEyes(headX, headY);
		drawMouth(headX, headY);
	}
	
	/**
	 * 绘制头部
	 */
	private void drawHead(double x, double y) {
		GRect head = new GRect(x, y, HEAD_WIDTH, HEAD_HEIGHT);
		head.setFilled(true);
		head.setFillColor(Color.GRAY);
		add(head);
	}
	
	/**
	 * 绘制眼睛
	 * 先计算眼睛中心的坐标，得到GOval左上角的坐标为中心坐标减去半径，长度为半径乘以2
	 */
	private void drawEyes(double headX, double headY) {
		double x1 = headX + (double)HEAD_WIDTH / 4; 
		double y1 = headY + (double)HEAD_HEIGHT / 4;
		double x2 = headX + (double)HEAD_WIDTH / 4 * 3; 
		double y2 = y1;
		GOval eye1 = new GOval((x1 - EYE_RADIUS), (y1 - EYE_RADIUS), EYE_RADIUS * 2, EYE_RADIUS * 2);
		GOval eye2 = new GOval((x2 - EYE_RADIUS), (y2 - EYE_RADIUS), EYE_RADIUS * 2, EYE_RADIUS * 2);
		eye1.setColor(Color.YELLOW);
		eye1.setFilled(true);
		eye1.setFillColor(Color.YELLOW);
		eye2.setColor(Color.YELLOW);
		eye2.setFilled(true);
		eye2.setFillColor(Color.YELLOW);
		add(eye1);
		add(eye2);
	}
	
	/**
	 * 绘制嘴巴
	 * 先计算嘴巴左上角坐标，x坐标为头部x坐标加上头部宽度减去嘴巴宽度的一半，使嘴巴在头部居中，y坐标为头部y坐标加上3/4的头部高度
	 */
	private void drawMouth(double headX, double headY) {
		double x = headX + (double)(HEAD_WIDTH - MOUTH_WIDTH) / 2;
		double y = headY + (double)HEAD_HEIGHT / 4 * 3;
		GRect mouth = new GRect(x, y, MOUTH_WIDTH, MOUTH_HEIGHT);
		mouth.setColor(Color.WHITE);
		mouth.setFilled(true);
		mouth.setFillColor(Color.WHITE);
		add(mouth);
	}
	
}
