
public class Player implements Comparable{
	/** 玩家姓名 */
	private String name;
	/** 玩家分数 */
	private int score;
	
	public Player(String name, int score) {
		this.name = name;
		this.score = score;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	/** 比较的方法*/
	@Override
	public int compareTo(Object o) {
		return   ((Player) o).getScore() - this.score;
	}
	
	public String toString() {
		return name + "  " + score;
	}
}
