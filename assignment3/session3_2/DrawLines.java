package session3_2;

import java.awt.event.MouseEvent;
import acm.graphics.*;
import acm.program.GraphicsProgram;

public class DrawLines extends GraphicsProgram {
	/** 记录鼠标上一次按下所在位置 */
	private GPoint last;

	/** 保存最后鼠标松开画下的线条 */
	private GLine copyLine;

	/** 鼠标拖拽产生的线条 */
	private GLine line;

	/**
	 * 添加鼠标监听事件
	 */
	public void run() {
		addMouseListeners();
	}

	/**
	 * 记录鼠标按下的位置 如果此时画布上的刚好画好了一条线条，在线条移除前把最终的线条画在画布上
	 */
	public void mousePressed(MouseEvent e) {
		last = new GPoint(e.getPoint());
		if (line != null) {
			copyLine = new GLine(line.getX(), line.getY(), line.getEndPoint().getX(), line.getEndPoint().getY());
			add(copyLine);
		}
	}

	/**
	 * 随着鼠标的移动更新线条的终点位置，只留下最后鼠标所在位置与刚开始记录的鼠标按下的位置的连线，因此在画下线条之前先移除之前位置的线条
	 */
	public void mouseDragged(MouseEvent e) {
		if (line != null) {
			remove(line);
		}
		line = new GLine(last.getX(), last.getY(), e.getX(), e.getY());
		add(line);
	}
}
