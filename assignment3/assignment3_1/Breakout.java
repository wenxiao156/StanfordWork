package assignment3_1;

/*
 * File: Breakout.java
 * -------------------
 * Name:
 * Section Leader:
 * 
 * This file will eventually implement the game of Breakout.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.*;

public class Breakout extends GraphicsProgram {

	/** Width and height of application window in pixels */
	public static final int APPLICATION_WIDTH = 400;
	public static final int APPLICATION_HEIGHT = 600;

	/** Dimensions of game board (usually the same) */
	private static final int WIDTH = APPLICATION_WIDTH;
	private static final int HEIGHT = APPLICATION_HEIGHT;

	/** Dimensions of the paddle */
	private static final int PADDLE_WIDTH = 60;
	private static final int PADDLE_HEIGHT = 10;

	/** Offset of the paddle up from the bottom */
	private static final int PADDLE_Y_OFFSET = 50;

	/** Number of bricks per row */
	private static final int NBRICKS_PER_ROW = 10;

	/** Number of rows of bricks */
	private static final int NBRICK_ROWS = 10;

	/** Separation between bricks */
	private static final int BRICK_SEP = 4;

	/** Width of a brick */
	private static final int BRICK_WIDTH = (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

	/** Height of a brick */
	private static final int BRICK_HEIGHT = 8;

	/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 10;

	/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;

	/** Number of turns */
	private static final int NTURNS = 3;

	/** 重力加速度 */
	private static final double GRAVITY = 3;

	/** 延迟时间 */
	private static final int DELAY = 150;

	/** 可用球的速度 */
	private static final int BALL_NUM = 3;

	/** 鼠标按下时所在位置的对象 */
	private GObject gobj;

	/** 用于鼠标拖拽时move计算绝对位置 */
	private GPoint last;

	/** 游戏面板 */
	private GRect board;

	/** 弹板 */
	private GRect paddle;

	/** 游戏面板左上角坐标 */
	private double boardX, boardY;

	/** 弹球 */
	private GOval ball;

	/** 弹球速度 */
	private double vx, vy = 0;

	/** 固定弹球向上的速度 */
	private final double UP_VY = -15;

	/** 随机设置vx的值 */
	private RandomGenerator rgen = RandomGenerator.getInstance();

	/** 触碰到底壁的次数 */
	private int trunCount;

	/** 剩余砖的数量，初始值为砖的行数乘以每行个数 */
	private int bricksNum = NBRICKS_PER_ROW * NBRICK_ROWS;

	/** 声音 */
	AudioClip bounceClip;

	/** 每个板砖的分数 */
	private static final int EACH_BRICK_SCORE = 10;

	/** 展示分数的label */
	GLabel score;

	/** 击中弹板次数 */
	private int hitTimes = 0;

	/** 击中弹板次数为7的倍数时增加的速度 */
	private static final int KICKER = 10;

	/**
	 * 先创建游戏面板，弹板，砖和弹球，加载声音文件，游戏循环进行BALL_NUM次，每次弹到底壁次数达到NTURNS或者消灭完所有砖块，一次循环结束
	 * 当板砖个数为0，游戏结束，否则游戏重新开始，移除当前弹球，创建新的弹球并重新得到随机的vx，初始vy设置为0
	 * ，击中次数重新设置为0，最后根据板砖个数展示游戏结果
	 */
	public void run() {
		setUp();
		bounceClip = MediaTools.loadAudioClip("bounce.au");
		displayScore(0);
		for (int ballCount = 0; ballCount < BALL_NUM; ballCount++) {
			while (trunCount < NTURNS && bricksNum > 0) {
				moveBall();
				checkForCollision();
				pause(DELAY);
			}
			if (bricksNum == 0)
				break;
			pause(2000);
			restart();
			trunCount = 0;
			vy = 0;
			hitTimes = 0;
		}
		displayResult();
	}

	/**
	 * 创建游戏面板，板砖，弹板和弹球，添加鼠标监听事件
	 */
	private void setUp() {
		drawApplicationAndGameBoard();
		setUpBricks();
		createPaddle();
		addMouseListeners();
		createBall();
	}

	/**
	 * 创建游戏面板使之在画布居中
	 */
	private void drawApplicationAndGameBoard() {
		GRect applicationBoard = new GRect(((double) getWidth() / 2 - (double) APPLICATION_WIDTH / 2),
				((double) getHeight() / 2 - (double) APPLICATION_HEIGHT / 2), APPLICATION_WIDTH, APPLICATION_HEIGHT);
		add(applicationBoard);
		board = applicationBoard;
		boardX = (double) getWidth() / 2 - (double) WIDTH / 2;
		boardY = (double) getHeight() / 2 - HEIGHT / 2;
	}

	/**
	 * 绘制板砖，得到第一个板砖坐标后根据行列数得到每个板砖的坐标 板砖颜色由colors控制，colors[i % 10]为第i行板砖的颜色
	 */
	private void setUpBricks() {
		double x = (double) getWidth() / 2
				- (double) (BRICK_WIDTH * NBRICKS_PER_ROW + BRICK_SEP * (NBRICKS_PER_ROW - 1)) / 2;
		double y = (double) getHeight() / 2 - (double) HEIGHT / 2 + BRICK_Y_OFFSET;
		Color[] colors = { Color.RED, Color.RED, Color.ORANGE, Color.ORANGE, Color.YELLOW, Color.YELLOW, Color.GREEN,
				Color.GREEN, Color.CYAN, Color.CYAN };
		for (int i = 0; i < NBRICK_ROWS; i++) {
			for (int j = 0; j < NBRICKS_PER_ROW; j++) {
				GRect brick = new GRect(x + j * BRICK_WIDTH + j * BRICK_SEP, y + i * BRICK_HEIGHT + i * BRICK_SEP,
						BRICK_WIDTH, BRICK_HEIGHT);
				brick.setFilled(true);
				brick.setFillColor(colors[i % 10]);
				brick.setColor(colors[i % 10]);
				add(brick);
			}
		}
	}

	/**
	 * 绘制弹板
	 */
	private void createPaddle() {
		GRect pad = new GRect((double) getWidth() / 2 - (double) PADDLE_WIDTH / 2,
				(double) getHeight() / 2 - (double) HEIGHT / 2 + HEIGHT - PADDLE_Y_OFFSET, PADDLE_WIDTH, PADDLE_HEIGHT);
		add(pad);
		paddle = pad;
	}

	/**
	 * 鼠标按下时记录按下的点和点所对应的对象
	 */
	public void mousePressed(MouseEvent e) {
		last = new GPoint(e.getPoint());
		gobj = getElementAt(last);
	}

	/**
	 * 鼠标拖拽需结合鼠标按下事件，如果鼠标按下所在位置的对象为弹板则将弹板x坐标设置为鼠标x坐标，即移动现在鼠标所在位置的x坐标减去之前记录的鼠标所在位置的x坐标
	 * 由于弹板不能移出游戏面板，当鼠标移除游戏面板时，弹板需移回游戏面板的边缘 最后需要更新记录的鼠标所在位置
	 */
	public void mouseDragged(MouseEvent e) {
		if (gobj == paddle) {
			if (gobj.getX() < boardX) {
				gobj.move(boardX - gobj.getX(), 0);
			} else if (gobj.getX() > boardX + WIDTH - PADDLE_WIDTH) {
				gobj.move((boardX + WIDTH - PADDLE_WIDTH) - gobj.getX(), 0);
			} else {
				gobj.move(e.getX() - last.getX(), 0);
			}
			last = new GPoint(e.getPoint());
		}
	}

	/**
	 * 创建弹球，弹球位置为游戏面板中心，vx由RandomGenerator随机给出，再通过rgen.nextBoolean(0.5)随机控制vx的正负
	 */
	private void createBall() {
		ball = new GOval(boardX + (double) WIDTH / 2 - BALL_RADIUS, boardY + (double) HEIGHT / 2 - BALL_RADIUS,
				BALL_RADIUS * 2, BALL_RADIUS * 2);
		ball.setFilled(true);
		add(ball);
		vx = rgen.nextDouble(1.0, 3.0);
		if (rgen.nextBoolean(0.5)) {
			vx = -vx;
		}
	}

	/**
	 * 游戏重新开始时移除当前弹球，并创建新的弹球
	 */
	private void restart() {
		remove(ball);
		createBall();
	}

	/**
	 * 移动弹球，当弹球向下，vy每次增加GRAVITY 如果弹球即将碰到弹板，设置弹球移动到会碰撞的位置
	 * 否则当弹球下，弹球移动到下一个位置，当弹球向上，设置弹球向上移动的位移为UP_VY 
	 * 扩展：当击中次数为7的倍数时，速度增加KICKER
	 * 当碰撞点为弹板端点，改变vx方向
	 */
	private void moveBall() {
		if (vy >= 0) {
			vy += GRAVITY;
		}
		if (hitTimes % 7 == 0 && vy >= 0)
			vy += KICKER;
		double point = checkForPaddle();
		if (point != 0) {
			ball.move(point - ball.getX(), paddle.getY() - ball.getY() - 2 * BALL_RADIUS);
			if(point == paddle.getX() || point == paddle.getX() + PADDLE_WIDTH) {
				vx = -vx;
			}
			bounceClip.play();
			hitTimes++;
		} else {
			if (vy > 0) {
				ball.move(vx, vy);
			} else {
				ball.move(vx, UP_VY);
			}

		}
	}

	/**
	 * 判断弹球是否会与弹板相碰撞
	 * 计算弹球按照正常移动下一次会出现的位置，计算两点之间线段的斜率，求出该线段如果与弹板相交的交点，交点的y坐标一定等于弹板的y坐标
	 * 如果交点的横坐标在弹板并且在弹球两个位置连线的线段上，弹球会与弹板碰撞
	 */
	private double checkForPaddle() {
		double startX1 = ball.getX();
		double endX1 = ball.getX() + vx;
		double startY1 = ball.getY();
		double endY1 = ball.getY() + vy;
		double startX2 = paddle.getX();
		double endX2 = paddle.getX() + PADDLE_WIDTH;
		double startY2 = paddle.getY();
		double k = (startY1 - endY1) / (startX1 - endX1);
		double pointX = (startY2 - endY1) / k + endX1;
		if (startX2 <= pointX && pointX <= endX2) {
			if (vx > 0 && startX1 <= pointX && pointX <= endX1) {
				return pointX;
			}
			if (vx < 0 && endX1 <= pointX && pointX <= startX1) {
				return pointX;
			}
		}
		return 0;
	}

	/**
	 * 判断弹球是否会弹出游戏面板和触碰到弹板和板砖
	 * 当弹球弹出底壁和顶壁时，改变vy的方向，并且让弹球移回游戏面板边缘，弹球触碰到底壁，trunCount加1
	 * 当弹球弹出左壁和右壁时，改变vx的方向，并且让弹球移回游戏面板边缘 当弹球触碰到弹板，改变vy方向
	 * 当弹球触碰到板砖，移除板砖，减少剩余板砖数量，改变vy方向
	 */
	private void checkForCollision() {
		if (ball.getY() > boardY + HEIGHT - BALL_RADIUS * 2) {
			trunCount++;
			vy = -vy;
			ball.move(0, -(ball.getY() - (boardY + HEIGHT - BALL_RADIUS * 2)));
		} else if (ball.getY() < boardY) {
			vy = -vy;
			ball.move(0, boardY - ball.getY());
		} else if (ball.getX() > boardX + WIDTH - BALL_RADIUS * 2) {
			vx = -vx;
			ball.move(-(ball.getX() - (boardX + WIDTH - BALL_RADIUS * 2)), 0);
		} else if (ball.getX() < boardX) {
			vx = -vx;
			ball.move(boardX - ball.getX(), 0);
		} else if (getCollidingObject() == paddle) {
			vy = -vy;
		} else if (getCollidingObject() != paddle && getCollidingObject() != board) {
			bounceClip.play();
			remove(getCollidingObject());
			bricksNum--;
			displayScore((NBRICKS_PER_ROW * NBRICK_ROWS - bricksNum) * EACH_BRICK_SCORE);
			vy = -vy;
		}
	}

	/**
	 * 返回弹球四个角点触碰到物体，由于弹球在游戏面板中运动，弹球一定会触碰到游戏面板，因此需要判断弹球触碰到的物体为非游戏面板
	 */
	private GObject getCollidingObject() {
		if (getElementAt(ball.getX(), ball.getY() + 2 * BALL_RADIUS) != board) {
			return getElementAt(ball.getX(), ball.getY() + 2 * BALL_RADIUS);
		}
		if (getElementAt(ball.getX() + 2 * BALL_RADIUS, ball.getY() + 2 * BALL_RADIUS) != board) {
			return getElementAt(ball.getX() + 2 * BALL_RADIUS, ball.getY() + 2 * BALL_RADIUS);
		}
		if (getElementAt(ball.getX(), ball.getY()) != board) {
			return getElementAt(ball.getX(), ball.getY());
		}
		if (getElementAt(ball.getX() + 2 * BALL_RADIUS, ball.getY()) != board) {
			return getElementAt(ball.getX() + 2 * BALL_RADIUS, ball.getY());
		}
		return board;
	}

	/**
	 * 根据剩余板砖个数展示游戏结果
	 */
	private void displayResult() {
		GLabel result = null;
		if (bricksNum == 0) {
			result = new GLabel("WIN");
		}
		if (bricksNum > 0) {
			result = new GLabel("LOSE");
		}
		result.setColor(Color.RED);
		result.setFont("SansSerif-50");
		result.setLocation((double) getWidth() / 2 - (double) result.getWidth() / 2,
				(double) getHeight() / 2 - (double) result.getAscent() / 2 + result.getAscent());
		add(result);
	}

	/**
	 * 根据剩余板砖个数展示游戏分数
	 */
	private void displayScore(int num) {
		if (score != null)
			remove(score);
		score = new GLabel("score: " + num);
		score.setColor(Color.RED);
		score.setFont("SansSerif-20");
		score.setLocation(boardX + WIDTH - (double) score.getWidth(), boardY + score.getAscent() + score.getAscent());
		add(score);
	}
}
