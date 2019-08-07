package finalexamination_4;

import java.util.Arrays;

import acm.program.ConsoleProgram;

/**
 * 循环获取用户输入的数字，赋值给三个3*3的二维数组
 * 当3个二维数组均被填满时，判断一次是否符合9个不同的数字，不符合直接退出循环
 */
public class Sudoku extends ConsoleProgram{
	public void run() {
		println("Enter the array you want to check!(from left to right, top to bottom)");
		int[][] temp1 = new int[3][3];
		int[][] temp2 = new int[3][3];
		int[][] temp3 = new int[3][3];
		boolean flag = true;
		for(int j = 0; j < 6; j++) {
			for(int k = 0; k < 9; k++) {
				if(j != 0 && j % 3 == 0 && k == 0) {
					if(!checkUpperLeftCorner(temp1) || !checkUpperLeftCorner(temp2) || !checkUpperLeftCorner(temp3)) {
						flag = false;
						println("The array is illegal!");
						break;
					}
				}
				int num = readInt();
				if(k > 5) {
					temp3[j % 3][k % 3] = num;
				} else if(k > 2) {
					temp2[j % 3][k % 3] = num;
				} else {
					temp1[j % 3][k % 3] = num;
				}
			}
			if(!flag) {
				break;
			}
		}
		if(flag) {
			println("The array is legal!");
		}		
	}
	
	/**
	 * 对数组里的数字按照count数组的下标进行计数，如果数组中的有大于1或者为0的元素直接返回false
	 */
	private boolean checkUpperLeftCorner(int[][] matrix) {
		int[] count = new int[9];
		for(int i = 0; i < 9; i++) {
			count[i] = 0;
		}
		for(int j = 0; j < 3; j++) {
			for(int k = 0; k < 3; k++) {
				count[matrix[j][k] - 1]++;
			}
		}
		for(int i = 0; i < 9; i++) {
			if(count[i] > 1 || count[i] == 0) {
				return false;
			}
		}
		return true;
	}
}
