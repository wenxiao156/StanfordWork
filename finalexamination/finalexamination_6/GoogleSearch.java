package finalexamination_6;

import java.util.ArrayList;

public class GoogleSearch {

	/**
	 * googleSearch未定义所以报错 
	 * 搜索的两个字符串得到的url地址是否只有一个相同
	 */
	public boolean isGooglewhack(String w1, String w2) {
		ArrayList<String> list1 = googleSearch(w1);
		ArrayList<String> list2 = googleSearch(w2);
		int count = 0;
		for (int i = 0; i < list1.size(); i++) {
			for (int j = 0; j < list2.size(); j++) {
				if (list1.get(i).equals(list2.get(j))) {
					count++;
				}
			}
		}
		return (count == 1);
	}
}
