package finalexamination_7;

import java.util.HashMap;

public class FindCommonKeyValuePairs {
	public  static void main(String[] args) {
		HashMap<String,String> map1 = new HashMap<String,String>();
		HashMap<String,String> map2 = new HashMap<String,String>();
		map1.put("Alice", "Healthy");
		map1.put("Mary", "Ecstatic");
		map1.put("Bob", "Happy");
		map1.put("Chuck", "Fine");
		map1.put("Felix", "Sick");
		map2.put("Mary", "Ecstatic");
		map2.put("Felix", "Healthy");
		map2.put("Tam", "Fine");
		map2.put("Bob", "Happy");
		map2.put("Ricardo", "Superb");
		System.out.println(commonKeyValuePairs(map1,map2));
	}
	
	/**
	 * 遍历map1中的key值，如果map2中也包含该key值，再判断value是否相等，相等则让count加1
	 */
	private static int commonKeyValuePairs(HashMap<String,String> map1,HashMap<String,String> map2) {
		int count = 0;
		for(String key1 : map1.keySet()) {
			if(map2.containsKey(key1) && map1.get(key1).equals(map2.get(key1))) {
				count++;
			}
		}
		return count;
	}
}
