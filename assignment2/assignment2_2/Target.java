package assignment2_2;
/*
 * File: Target.java
 * Name: 
 * Section Leader: 
 * -----------------
 * This file is the starter file for the Target problem.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Target extends GraphicsProgram {
	
	/**保存1英寸等于72像素 */
	private static final int INCH = 72;
	
	/**按照顺序依次画圆 */
	public void run() {
		drawCircle(INCH, Color.RED);
		drawCircle(0.65 * INCH, Color.WHITE);
		drawCircle(0.3 * INCH, Color.RED);
	}
	
	/**r为圆形半径，color为需要填充的颜色
	 * 计算圆形所在正方形的左上角的点的位置，x坐标为画板宽度的一半减去半径，y坐标为画板高度的一半减去半径
	 * 新建GOval对象设置填充为true，并设置填充颜色和边框颜色，将circle添加到画板中
	 */
	private void drawCircle(double r , Color color) {
		double x = (double)getWidth() / 2 - r;
		double y = (double)getHeight() / 2 - r;
		GOval circle = new GOval(x, y, r * 2, r * 2);
		circle.setFilled(true);
		circle.setFillColor(color);
		circle.setColor(color);
		add(circle);
	}
}
