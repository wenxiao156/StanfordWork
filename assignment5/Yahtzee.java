
/*
 * File: Yahtzee.java
 * ------------------
 * This program will eventually play the Yahtzee game.
 */

import java.io.*;
import java.util.*;

import acm.io.*;
import acm.program.*;
import acm.util.*;

public class Yahtzee extends GraphicsProgram implements YahtzeeConstants {

	public static void main(String[] args) {
		new Yahtzee().start(args);
	}

	/**
	 * 由用户输入玩家人数后输入玩家姓名，开始进行游戏
	 */
	public void run() {
		 IODialog dialog = getDialog();
		 nPlayers = dialog.readInt("Enter number of players");
		 playerNames = new String[nPlayers];
		 for (int i = 1; i <= nPlayers; i++) {
		 playerNames[i - 1] = dialog.readLine("Enter name for player " + i);
		 }
		 display = new YahtzeeDisplay(getGCanvas(), playerNames);
		playGame();
	}

	/**
	 * 进行游戏 初始化分数的数组 一共进行N_SCORING_CATEGORIES次游戏，每次游戏玩家按顺序进行
	 * 等待玩家点击摇骰子按钮，随机获取骰子展示的数字，将数字记录进dices数组中，并由display在骰子上展示
	 * 等待玩家选择想要重新摇的骰子，摇完之后等待玩家选择想要分配到的类别，判断类别是否已被选择 根据摇到的骰子的数字计算得分，更新游戏面板上的分数
	 * 最后计算玩家是否可以得到奖励，展示游戏结果
	 */
	private void playGame() {
		 initScoreArray();
		 for (int i = 0; i < N_SCORING_CATEGORIES; i++) {
		 for (int j = 0; j < nPlayers; j++) {
		 display.printMessage(playerNames[j] + "'s turn! Click 'Roll Dice' button to roll the dice.");
		 display.waitForPlayerToClickRoll(j + 1);
		 int[] dices = getDicesNum();
		 display.displayDice(dices);
		 display.printMessage("Select the dice you wish to re-roll and Click 'Roll Again'.");
		 checkDiceSelect(j + 1, dices);
		 display.printMessage("Select a category for this roll.");
		 int category = display.waitForPlayerToSelectCategory();
		 while (!checkCategory(j, category - 1)) {
		 category = display.waitForPlayerToSelectCategory();
		 }
		 int score = getScore(j, dices, category);
		 display.updateScorecard(category, j + 1, score);
		 updateScore(j);
		 }
		 }
		 updateUpperBonus();
		 displayResult();
		try {
			putIntoFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 初始化玩家分数数组，由二维数组进行记录，初始值为-1
	 */
	private void initScoreArray() {
		scores = new int[nPlayers][N_CATEGORIES];
		for (int i = 0; i < nPlayers; i++) {
			for (int j = 0; j < N_CATEGORIES; j++) {
				scores[i][j] = -1;
			}
		}
	}

	/**
	 * 用dices数组记录随机生成每个骰子的数字
	 */
	private int[] getDicesNum() {
		int[] dices = new int[N_DICE];
		for (int i = 0; i < N_DICE; i++) {
			dices[i] = rgen.nextInt(1, 6);
		}
		return dices;
	}

	/**
	 * 对玩家选择的重新摇的骰子重新随机生成数字，记录进dices数组中，重新展示选中的骰子上的数字
	 */
	private void checkDiceSelect(int player, int[] dices) {
		display.waitForPlayerToSelectDice();
		for (int i = 0; i < N_DICE; i++) {
			if (display.isDieSelected(i)) {
				dices[i] = rgen.nextInt(1, 6);
			}
		}
		display.displayDice(dices);
	}

	/**
	 * 检查玩家想要分配的类别是否已经被分配过
	 */
	private boolean checkCategory(int player, int category) {
		if (scores[player][category] == -1)
			return true;
		return false;
	}

	/**
	 * 根据玩家摇到的每个骰子的数字和想要分配的类别计算可以得到的分数
	 */
	private int getScore(int player, int[] dices, int category) {
		int score = 0;
		switch (category) {
		case THREE_OF_A_KIND:
			score = findKinds(dices, 3);
			break;
		case FOUR_OF_A_KIND:
			score = findKinds(dices, 4);
			break;
		case FULL_HOUSE:
			score = findFullHouse(dices);
			break;
		case SMALL_STRAIGHT:
			score = findStraight(dices, 4);
			break;
		case LARGE_STRAIGHT:
			score = findStraight(dices, 5);
			break;
		case YAHTZEE:
			score = findYahtzee(dices);
			break;
		case CHANCE:
			score = chance(dices);
			break;
		default:
			score = findNum(dices, category);
			break;
		}
		scores[player][category - 1] = score;
		return score;
	}

	/**
	 * 找到数组中有几个相同的数字，返回数字和次数的乘积
	 */
	private int findNum(int[] array, int num) {
		int count = 0;
		for (int i = 0; i < array.length; i++) {
			if (array[i] == num) {
				count++;
			}
		}
		return count * num;
	}

	/**
	 * 找到数组中相同的数字，相同的数字的个数大于给定的times，分数才为所有数字的和
	 */
	private int findKinds(int[] array, int times) {
		boolean flag = false;
		int score = 0;
		HashMap<Integer, Integer> map = getHashMap(array);
		for (int num : map.keySet()) {
			if (map.get(num) >= times) {
				flag = true;
			}
			score += num * map.get(num);
		}
		if (flag) {
			return score;
		} else {
			return 0;
		}
	}

	/**
	 * 找到数组中相同的数字，相同的数字的个数大于给定的times，分数才为所有数字的和
	 */
	private HashMap<Integer, Integer> getHashMap(int[] array) {
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < array.length; i++) {
			if (map.containsKey(array[i])) {
				map.put(array[i], map.get(array[i]) + 1);
			} else {
				map.put(array[i], 1);
			}
		}
		return map;
	}

	/**
	 * 当数组中的数字种数等于2时，只要数字出现的次数等于2或者3就证明符合该类别
	 */
	private int findFullHouse(int[] array) {
		HashMap<Integer, Integer> map = getHashMap(array);
		if (map.size() != 2) // 数组中的数字种数不等于2可以直接判断不符合，返回0
			return 0;
		boolean flag = false;
		for (int num : map.keySet()) {
			if (map.get(num) == 2 || map.get(num) == 3) {
				flag = true;
			}
		}
		if (flag) {
			return 25;
		} else {
			return 0;
		}
	}

	/**
	 * 基本数据类型的数组clone之后不会改变原数组，对复制后的数组进行排序，记录相差不等于1的次数count count不能大于N_DICE -
	 * num
	 */
	private int findStraight(int[] array, int num) {
		int[] temp = array.clone();
		int count = 0;
		Arrays.sort(temp);
		for (int i = 0; i < temp.length - 1; i++) {
			if (temp[i + 1] - temp[i] != 1) {
				count++;
			}
		}
		if (num == 4 && count <= N_DICE - num) {
			return 30;
		} else if (num == 5 && count <= N_DICE - num) {
			return 40;
		} else {
			return 0;
		}
	}

	/**
	 * 数组中的数字全部相等，则记录数字和对应次数的HashMap的size为1
	 */
	private int findYahtzee(int[] array) {
		HashMap<Integer, Integer> map = getHashMap(array);
		if (map.size() == 1)
			return 50;
		return 0;
	}

	/**
	 * 将数组中的所有数字相加
	 */
	private int chance(int[] array) {
		int score = 0;
		for (int i = 0; i < array.length; i++) {
			score += array[i];
		}
		return score;
	}

	/**
	 * 更新上部总分，下部总分和全部总分 更新scores数组中的记录分数和游戏面板中展示的分数
	 */
	private void updateScore(int player) {
		int upperScore = 0;
		int lowerScore = 0;
		int total = 0;
		for (int i = 0; i < N_CATEGORIES; i++) {
			if (i == UPPER_SCORE - 1 || i == UPPER_BONUS - 1 || i == LOWER_SCORE - 1 || i == TOTAL - 1) { // 跳过记录总分和奖励的元素
				continue;
			}
			if (scores[player][i] != -1) { // 被分配后的类别才进行分数的相加
				if (i < UPPER_SCORE - 1) {
					upperScore += scores[player][i];
				} else if (i < LOWER_SCORE - 1) {
					lowerScore += scores[player][i];
				}
				total += scores[player][i];
			}
		}
		scores[player][UPPER_SCORE - 1] = upperScore;
		scores[player][LOWER_SCORE - 1] = lowerScore;
		scores[player][TOTAL - 1] = total;
		if (upperScore != 0) {
			display.updateScorecard(UPPER_SCORE, player + 1, upperScore);
		}
		if (lowerScore != 0) {
			display.updateScorecard(LOWER_SCORE, player + 1, lowerScore);
		}
		display.updateScorecard(TOTAL, player + 1, total);
	}

	/**
	 * 更新奖励分和总分，上部总分大于63可以得到奖励分35
	 */
	private void updateUpperBonus() {
		for (int i = 0; i < nPlayers; i++) {
			if (scores[i][UPPER_SCORE - 1] > 63) {
				scores[i][UPPER_BONUS - 1] = 35;
				scores[i][TOTAL - 1] = scores[i][TOTAL - 1] + 35;
				display.updateScorecard(TOTAL, i + 1, scores[i][TOTAL - 1]);
				display.updateScorecard(UPPER_BONUS, i + 1, 35);
			} else {
				scores[i][UPPER_BONUS - 1] = 0;
				display.updateScorecard(UPPER_BONUS, i + 1, 0);
			}
		}
	}

	/**
	 * 展示游戏结果
	 */
	private void displayResult() {
		int winner = 0;
		int maxScore = scores[0][TOTAL - 1];
		for (int i = 0; i < nPlayers; i++) {
			if (scores[i][TOTAL - 1] > maxScore) {
				maxScore = scores[i][TOTAL - 1];
				winner = i;
			}
		}
		display.printMessage("Congratulations, " + playerNames[winner] + ", you're the winner with a total score of "
				+ maxScore + "!");
	}
	
	
	/**
	 * 读取最高分文件中的玩家及分数，保存进ArrayList<Player>中
	 */
	private ArrayList<Player> readFile() {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader("topTenScores.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		ArrayList<Player> list = new ArrayList<Player>();
		try {
			while (true) {
				String line = reader.readLine();
				if (line == null || line.isEmpty())
					break;
				String name = line.split("  ")[0];
				int score = Integer.valueOf(line.split("  ")[1]);
				Player player = new Player(name, score);
				list.add(player);
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 把现在的玩家分数添加到ArrayList<Player>中，对ArrayList进行排序，取前10个分数
	 */
	private void putIntoFile() throws IOException {
		ArrayList<Player> list = readFile();
		try {
			PrintWriter write = new PrintWriter(new FileWriter("topTenScores.txt"));
			int size = list.size();
			for (int i = 0; i < nPlayers; i++) {
//				if(scores[i][TOTAL - 1] > list.get(size).getScore()) {
//					display.printMessage("Congratulations, " + playerNames[i] + ", you update the record of the top ten highest scores  with a total score of "
//							+ scores[i][TOTAL - 1] + "!");
//				}
				Player player = new Player(playerNames[i], scores[i][TOTAL - 1]);
				list.add(player);
			}
			Collections.sort(list);
			for (int i = 0; i < TOP_NUM; i++) {
				write.println(list.get(i));
			}
			write.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/* Private instance variables */
	private int nPlayers;
	private String[] playerNames;
	private YahtzeeDisplay display;
	/** 记录玩家分数的二维数组 */
	private int[][] scores;
	private static final int TOP_NUM = 10;
	private RandomGenerator rgen = new RandomGenerator();

}
