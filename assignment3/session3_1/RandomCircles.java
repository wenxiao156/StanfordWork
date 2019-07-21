package session3_1;

import java.awt.Color;
import acm.graphics.*;
import acm.program.*;
import acm.util.RandomGenerator;

public class RandomCircles extends GraphicsProgram {
	/** 用于产生随机的圆 */
	private RandomGenerator rgen = RandomGenerator.getInstance();

	/** 圆的个数 */
	private static final int CIRCLE_NUM = 10;
	
	/** 最大半径 */
	private static final int MAX_RADIUS = 50;
	
	/** 最小半径 */
	private static final int MIN_RADIUS = 5;

	/**
	 * 循环CIRCLE_NUM次画圆
	 */
	public void run() {
		for (int i = 0; i < CIRCLE_NUM; i++) {
			drawCircle();
		}
	}

	/**
	 * 绘制圆 由于限制圆只能在画板中，因此圆的左上角的x坐标不能大于画板宽减去直径的差,y坐标不能大于画板高减去直径的差
	 */
	private void drawCircle() {
		Color color = rgen.nextColor();
		double radius = rgen.nextDouble(MIN_RADIUS, MAX_RADIUS);
		double x = rgen.nextDouble(0, getWidth() - 2 * radius);
		double y = rgen.nextDouble(0, getHeight() - 2 * radius);
		GOval circle = new GOval(x, y, radius * 2, radius * 2);
		circle.setFilled(true);
		circle.setFillColor(color);
		circle.setColor(color);
		add(circle);
	}
}
