package assignment4_1;
/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */

import acm.graphics.*;

public class HangmanCanvas extends GCanvas {
	 /** 提示单词*/
	private GLabel wordPrompt;
	/** 猜错的单词*/
	private String incorrectGuess = "";
	/** 猜错的单词label*/
	private GLabel incorrectLabel;
	/** 面板的高*/
	private int canvasHeight;
	/** 头*/
	private GOval head;
	/** 身体*/
	private GLine body;
	/** 左手的上面*/
	private GLine leftUpperArm;
	/** 左手的下面*/
	private GLine leftLowerArm;
	/** 右手的上面*/
	private GLine rightUpperArm;
	/** 右手的下面*/
	private GLine rightLowerArm;
	/** 左臀*/
	private GLine leftHip;
	/** 左腿*/
	private GLine leftLeg;
	/** 右臀*/
	private GLine rightHip;
	/** 右腿*/
	private GLine rightLeg;
	/** 左脚*/
	private GLine leftFoot;
	/** 右脚*/
	private GLine rightFoot;

	/** 
	 * 重置面板，清空面板，绘制所有部位
	 */
	public void reset(int width, int height) {
		canvasHeight = height;
		removeAll();
		double topX = (double) width / 2 - (double) (BEAM_LENGTH + UPPER_ARM_LENGTH) / 2;
		double topY = (double) height / 2 - (double) SCAFFOLD_HEIGHT / 2 - TO_MIDDLE;
		GLine scaffold = new GLine(topX, topY, topX, topY + SCAFFOLD_HEIGHT);
		add(scaffold);
		double middleOfBody = topX + BEAM_LENGTH;
		GLine beam = new GLine(topX, topY, middleOfBody, topY);
		add(beam);
		GLine rope = new GLine(middleOfBody, topY, middleOfBody, topY + ROPE_LENGTH);
		add(rope);

		drawHead(middleOfBody,topY);
		drawBody(middleOfBody,topY);
		double armY = topY + ROPE_LENGTH + HEAD_RADIUS * 2 + ARM_OFFSET_FROM_HEAD;
		drawLeftArm(middleOfBody,armY);
		drawRightArm(middleOfBody,armY);

		double legY = topY + ROPE_LENGTH + HEAD_RADIUS * 2 + BODY_LENGTH;
		drawLeftLeg(middleOfBody,legY);
		drawRightLeg(middleOfBody,legY);
		drawLeftFoot(middleOfBody,legY);
		drawRightFoot(middleOfBody,legY);
	}
	
	/** 
	 * 绘制头部，设置可见性为false
	 */
	private void drawHead(double middleOfBody,double topY) {
		head = new GOval(middleOfBody - HEAD_RADIUS, topY + ROPE_LENGTH, HEAD_RADIUS * 2, HEAD_RADIUS * 2);
		head.setVisible(false);
		add(head);
	}

	/** 
	 * 绘制身体，设置可见性为false
	 */
	private void drawBody(double middleOfBody,double topY) {
		body = new GLine(middleOfBody, topY + ROPE_LENGTH + HEAD_RADIUS * 2, middleOfBody,
				topY + ROPE_LENGTH + HEAD_RADIUS * 2 + BODY_LENGTH);
		body.setVisible(false);
		add(body);
	}
	
	/** 
	 * 绘制左臂，设置可见性为false
	 */
	private void drawLeftArm(double middleOfBody,double armY) {
		leftUpperArm = new GLine(middleOfBody - UPPER_ARM_LENGTH, armY, middleOfBody, armY);
		add(leftUpperArm);
		leftUpperArm.setVisible(false);
		leftLowerArm = new GLine(middleOfBody - UPPER_ARM_LENGTH, armY, middleOfBody - UPPER_ARM_LENGTH,
				armY + LOWER_ARM_LENGTH);
		add(leftLowerArm);
		leftLowerArm.setVisible(false);
	}

	/** 
	 * 绘制右臂，设置可见性为false
	 */
	private void drawRightArm(double middleOfBody,double armY) {
		rightUpperArm = new GLine(middleOfBody, armY, middleOfBody + UPPER_ARM_LENGTH, armY);
		add(rightUpperArm);
		rightUpperArm.setVisible(false);
		rightLowerArm = new GLine(middleOfBody + UPPER_ARM_LENGTH, armY, middleOfBody + UPPER_ARM_LENGTH,
				armY + LOWER_ARM_LENGTH);
		add(rightLowerArm);
		rightLowerArm.setVisible(false);
	}
	
	/** 
	 * 绘制左腿，设置可见性为false
	 */
	private void drawLeftLeg(double middleOfBody,double legY) {
		leftHip = new GLine(middleOfBody, legY, middleOfBody - HIP_WIDTH, legY);
		add(leftHip);
		leftHip.setVisible(false);
		leftLeg = new GLine(middleOfBody - HIP_WIDTH, legY, middleOfBody - HIP_WIDTH, legY + LEG_LENGTH);
		add(leftLeg);
		leftLeg.setVisible(false);
	}
	
	/** 
	 * 绘制右腿，设置可见性为false
	 */
	private void drawRightLeg(double middleOfBody,double legY) {
		rightHip = new GLine(middleOfBody, legY, middleOfBody + HIP_WIDTH, legY);
		add(rightHip);
		rightHip.setVisible(false);
		rightLeg = new GLine(middleOfBody + HIP_WIDTH, legY, middleOfBody + HIP_WIDTH, legY + LEG_LENGTH);
		add(rightLeg);
		rightLeg.setVisible(false);
	}
	
	/** 
	 * 绘制左脚，设置可见性为false
	 */
	private void drawLeftFoot(double middleOfBody,double legY) {
		leftFoot = new GLine(middleOfBody - HIP_WIDTH, legY + LEG_LENGTH, middleOfBody - HIP_WIDTH - FOOT_LENGTH,
				legY + LEG_LENGTH);
		add(leftFoot);
		leftFoot.setVisible(false);
	}
	
	/** 
	 * 绘制右脚，设置可见性为false
	 */
	private void drawRightFoot(double middleOfBody,double legY) {
		rightFoot = new GLine(middleOfBody + HIP_WIDTH, legY + LEG_LENGTH, middleOfBody + HIP_WIDTH + FOOT_LENGTH,
				legY + LEG_LENGTH);
		add(rightFoot);
		rightFoot.setVisible(false);
	}

	/**
	 * 展示提示单词
	 * 更新提示单词需要先把之前的提示单词移除，再重新展示
	 */
	public void displayWord(String word) {
		if (wordPrompt != null)
			remove(wordPrompt);
		wordPrompt = new GLabel(word);
		wordPrompt.setFont("SansSerif-40");
		wordPrompt.setLocation(LABEL_LEFT, canvasHeight - SHOW_LABEL + wordPrompt.getAscent());
		add(wordPrompt);
	}

	/**
	 * 展示至今为止用户输入的不正确的字母
	 * 更新不正确的字母需要先把之前的不正确的字母移除，再重新展示
	 * 展示相应的部位
	 */
	public void noteIncorrectGuess(char letter, int times) {
		if (incorrectLabel != null)
			remove(incorrectLabel);
		incorrectGuess += letter;
		incorrectLabel = new GLabel(incorrectGuess);
		incorrectLabel.setFont("SansSerif-15");
		incorrectLabel.setLocation(LABEL_LEFT,
				canvasHeight - SHOW_LABEL + incorrectLabel.getAscent() * 4);
		add(incorrectLabel);
		controlVisible(times);
	}
	
	/**
	 * 根据猜错的字母的个数展示相应的部位
	 */
	private void controlVisible(int times) {
		switch (times) {
		case 1:
			head.setVisible(true);
			break;
		case 2:
			body.setVisible(true);
			break;
		case 3:
			leftUpperArm.setVisible(true);
			leftLowerArm.setVisible(true);
			break;
		case 4:
			rightUpperArm.setVisible(true);
			rightLowerArm.setVisible(true);
			break;
		case 5:
			leftHip.setVisible(true);
			leftLeg.setVisible(true);
			break;
		case 6:
			rightHip.setVisible(true);
			rightLeg.setVisible(true);
			break;
		case 7:
			leftFoot.setVisible(true);
			break;
		case 8:
			rightFoot.setVisible(true);
			break;
		default:
			System.out.println("传入的猜错次数出错！");
			
		}
	}

	/* Constants for the simple version of the picture (in pixels) */
	/** 脚手架高度*/
	private static final int SCAFFOLD_HEIGHT = 360;
	/** 光束的长度*/
	private static final int BEAM_LENGTH = 144;
	/** 绳子的长度*/
	private static final int ROPE_LENGTH = 18;
	/** 头的半径*/
	private static final int HEAD_RADIUS = 36;
	/** 身体的长度*/
	private static final int BODY_LENGTH = 144;
	/** 手距离头的距离*/
	private static final int ARM_OFFSET_FROM_HEAD = 28;
	/** 手的上面的长度*/
	private static final int UPPER_ARM_LENGTH = 72;
	/** 手的下面的长度*/
	private static final int LOWER_ARM_LENGTH = 44;
	/** 臀的宽度*/
	private static final int HIP_WIDTH = 36;
	/** 腿的长度*/
	private static final int LEG_LENGTH = 108;
	/** 脚的长度*/
	private static final int FOOT_LENGTH = 28;
	/** label距离底部的距离*/
	private static final int SHOW_LABEL = 150;
	/** label距离左边的距离*/
	private static final int LABEL_LEFT = 20;
	/** 使hangman居中需要向上的距离*/
	private static final int TO_MIDDLE = 70;
}
