package assignment4_1;
/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */

import acm.graphics.*;

public class HangmanCanvas extends GCanvas {
	private GLabel wordPrompt;
	
	private String incorrectGuess = "";
	
	private GLabel incorrectLabel;
	

/** Resets the display so that only the scaffold appears */
	public void reset(int width, int height) {
		removeAll();
		double topX = (double)width / 2 - (double)(BEAM_LENGTH + UPPER_ARM_LENGTH) / 2;
		double topY = (double)height / 2 - (double)SCAFFOLD_HEIGHT / 2 - SHOW_LABEL;
		GLine scaffold = new GLine(topX, topY, topX, topY + SCAFFOLD_HEIGHT);
		add(scaffold);
		double middleOfBody = topX + BEAM_LENGTH;
		GLine beam = new GLine(topX, topY, middleOfBody, topY);
		add(beam);
		GLine rope = new GLine(middleOfBody, topY, middleOfBody, topY + ROPE_LENGTH);
		add(rope);
		
		GOval head = new GOval(middleOfBody - HEAD_RADIUS, topY + ROPE_LENGTH, HEAD_RADIUS * 2, HEAD_RADIUS * 2);
//		head.setVisible(false);
		add(head);
		
		GLine body = new GLine(middleOfBody, topY + ROPE_LENGTH + HEAD_RADIUS * 2, middleOfBody, topY + ROPE_LENGTH + HEAD_RADIUS * 2 + BODY_LENGTH);
//	    body.setVisible(false);
	    add(body);
	    
	    double armY = topY + ROPE_LENGTH + HEAD_RADIUS * 2 + ARM_OFFSET_FROM_HEAD;
	    GLine leftUpperArm = new GLine(middleOfBody - UPPER_ARM_LENGTH, armY, middleOfBody, armY);
	    add(leftUpperArm);
	    GLine leftLowerArm = new GLine(middleOfBody - UPPER_ARM_LENGTH, armY, middleOfBody - UPPER_ARM_LENGTH, armY + LOWER_ARM_LENGTH);
	    add(leftLowerArm);
	    
	    GLine rightUpperArm = new GLine(middleOfBody, armY, middleOfBody + UPPER_ARM_LENGTH, armY);
	    add(rightUpperArm);
	    GLine rightLowerArm = new GLine(middleOfBody + UPPER_ARM_LENGTH, armY, middleOfBody + UPPER_ARM_LENGTH, armY + LOWER_ARM_LENGTH);
	    add(rightLowerArm);
	    
	    double legY = topY + ROPE_LENGTH + HEAD_RADIUS * 2 + BODY_LENGTH;
	    GLine leftHip = new GLine(middleOfBody, legY, middleOfBody - HIP_WIDTH, legY);
	    add(leftHip);
	    GLine leftLeg = new GLine(middleOfBody - HIP_WIDTH, legY, middleOfBody - HIP_WIDTH, legY + LEG_LENGTH);
	    add(leftLeg);
	    
	    GLine rightHip = new GLine(middleOfBody, legY, middleOfBody + HIP_WIDTH, legY);
	    add(rightHip);
	    GLine rightLeg = new GLine(middleOfBody + HIP_WIDTH, legY, middleOfBody + HIP_WIDTH, legY + LEG_LENGTH);
	    add(rightLeg);
	    
	    GLine leftFoot = new GLine(middleOfBody - HIP_WIDTH, legY + LEG_LENGTH, middleOfBody - HIP_WIDTH - FOOT_LENGTH, legY + LEG_LENGTH);
	    add(leftFoot);
	    
	    GLine rightFoot = new GLine(middleOfBody + HIP_WIDTH, legY + LEG_LENGTH, middleOfBody + HIP_WIDTH + FOOT_LENGTH, legY + LEG_LENGTH);
	    add(rightFoot);
	}

/**
 * Updates the word on the screen to correspond to the current
 * state of the game.  The argument string shows what letters have
 * been guessed so far; unguessed letters are indicated by hyphens.
 */
	public void displayWord(String word) {
		if(wordPrompt != null) remove(wordPrompt);
		wordPrompt = new GLabel(word);
		wordPrompt.setFont("SansSerif-50");
		wordPrompt.setLocation(LABEL_LEFT, getHeight() - SHOW_LABEL + wordPrompt.getAscent());
		add(wordPrompt);
	}

/**
 * Updates the display to correspond to an incorrect guess by the
 * user.  Calling this method causes the next body part to appear
 * on the scaffold and adds the letter to the list of incorrect
 * guesses that appears at the bottom of the window.
 */
	public void noteIncorrectGuess(char letter, int times) {
		if(incorrectLabel != null) remove(incorrectLabel);
		incorrectGuess += letter;
        incorrectLabel = new GLabel(incorrectGuess);
        incorrectLabel.setFont("SansSerif-10");
        incorrectLabel.setLocation(LABEL_LEFT, getHeight() - SHOW_LABEL + wordPrompt.getAscent() + incorrectLabel.getAscent() * 2);
		add(incorrectLabel);
		controlVisible(times);
	}
	
	private void controlVisible(int times) {
//		switch times:
//			case 1:  body.setVisible(true);
	}

/* Constants for the simple version of the picture (in pixels) */
	private static final int SCAFFOLD_HEIGHT = 360;
	private static final int BEAM_LENGTH = 144;
	private static final int ROPE_LENGTH = 18;
	private static final int HEAD_RADIUS = 36;
	private static final int BODY_LENGTH = 144;
	private static final int ARM_OFFSET_FROM_HEAD = 28;
	private static final int UPPER_ARM_LENGTH = 72;
	private static final int LOWER_ARM_LENGTH = 44;
	private static final int HIP_WIDTH = 36;
	private static final int LEG_LENGTH = 108;
	private static final int FOOT_LENGTH = 28;
	private static final int SHOW_LABEL = 70;
	private static final int LABEL_LEFT = 20;
}
