package assignment2_3;
/*
 * File: ProgramHierarchy.java
 * Name: 
 * Section Leader: 
 * ---------------------------
 * This file is the starter file for the ProgramHierarchy problem.
 */

import acm.graphics.*;
import acm.program.*;

public class ProgramHierarchy extends GraphicsProgram {	
	
	/** 长方形框的宽度 */
	private static final int RECT_WIDTH = 120;
	
	/** 长方形框的高度 */
	private static final int RECT_HEIGHT = 40;
	
	/** 两个长方形框在竖直方向上的距离 */
	private static final int HEIGHT_DISTANCE = 40;
	
	/** 两个长方形框在水平方向上的距离 */
	private static final int WIDTH_DISTANCE = 40;
	
	/** 
	 * 计算每个长方形框的坐标，传给drawRect方法进行绘制
	 * 计算连线的起点和终点坐标，传给drawLine方法进行绘制
	 */
	public void run() {
		double programX = (double)getWidth() / 2 - (double)RECT_WIDTH / 2;
		double programY = (double)getHeight() / 2 - (double)HEIGHT_DISTANCE / 2 - RECT_HEIGHT;
		drawRect(programX, programY, "Program");
		
		double graphicsProgramX = programX - RECT_WIDTH - WIDTH_DISTANCE;
		double graphicsProgramY = (double)getHeight() / 2 + (double)HEIGHT_DISTANCE / 2;
		drawRect(graphicsProgramX, graphicsProgramY, "GraphicsProgram");
		
		double consoleProgramX = programX;
		double consoleProgramY = graphicsProgramY;
		drawRect(consoleProgramX, consoleProgramY, "ConsoleProgram");
		
		double dialogProgramX = programX + RECT_WIDTH + WIDTH_DISTANCE;
		double dialogProgramY = graphicsProgramY;
		drawRect(dialogProgramX, dialogProgramY, "DialogProgram");
		
		double startPointX = programX + (double)RECT_WIDTH / 2;
		double startPointY = programY + RECT_HEIGHT;
		drawLine(startPointX, startPointY, graphicsProgramX + (double)RECT_WIDTH / 2 ,graphicsProgramY);
		
		drawLine(startPointX, startPointY, startPointX ,graphicsProgramY);
		
		drawLine(startPointX, startPointY, dialogProgramX + (double)RECT_WIDTH / 2 ,graphicsProgramY);
	}
	
	/** 
	 * 根据坐标绘制长方形框和label
	 * label的x坐标为长方形框的x坐标加上 长方形框的宽度减去label宽度除以2的值
	 * label的y坐标为长方形框的y坐标加上 长方形框的高度减去label基线以上高度除以2的值 再加上 label基线以上高度（字体的坐标起点在右下角！）
	 */
	private void drawRect(double x, double y, String programType) {	
		GRect rect = new GRect(x, y,RECT_WIDTH,RECT_HEIGHT);
		add(rect);
		GLabel programLabel = new GLabel(programType);
		programLabel.setLocation(x + (double)(RECT_WIDTH - programLabel.getWidth()) / 2, y + (double)(RECT_HEIGHT - programLabel.getAscent()) / 2 + programLabel.getAscent());
		add(programLabel);
	}
	
	/** 
	 * 根据坐标绘制连线
	 */
	private void drawLine(double x1, double y1, double x2, double y2) {
		GLine line = new GLine(x1, y1, x2, y2);
		add(line);
	}
	
}

