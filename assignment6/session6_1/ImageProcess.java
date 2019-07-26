package session6_1;

import acm.graphics.GImage;
import acm.program.GraphicsProgram;

public class ImageProcess extends GraphicsProgram {
	/**
	 * 将图片左右对调 添加图片，将图片左右两边对调，展示新的图片
	 */
	public void run() {
		GImage image = new GImage("worker.jpg");
		add(image, 30, 30);
		GImage afterChange = flipHorizontal(image);
		add(afterChange, 100 + image.getWidth(), 30);
	}

	/**
	 * 将图片左右两边对调 获取图片的像素矩阵，将图片每行的像素左右两边对调，使用temp作为保存第一个位置的像素
	 */
	private GImage flipHorizontal(GImage image) {
		int[][] pixelArray = image.getPixelArray();
		int height = pixelArray.length;
		int width = pixelArray[0].length;
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width / 2; j++) { // j不能到width，否则会重新将图片的左右对调，即图片最终不变
				int temp = pixelArray[i][j];
				pixelArray[i][j] = pixelArray[i][width - j - 1];
				pixelArray[i][width - j - 1] = temp;
			}
		}
		GImage afterChage = new GImage(pixelArray);
		return afterChage;
	}
}
