package section9;

/**
 * This class provides methods for working with an array that expands * to
 * include any positive index value supplied by the caller.
 */

public class ExpandableArray {
	
	private Object[] array;
	
	private static final int INITIAL_LENGTH = 10;

	/** 
	 * 初始化对象数组的长度为INITIAL_LENGTH
	 */
	public ExpandableArray() {
		array = new Object[INITIAL_LENGTH];
	}

	/**
	 * 当设置的下标大于INITIAL_LENGTH时，复制数组，重新创建原本的数组并对数组进行赋值
	 */
	public void set(int index, Object value) {
		if(index > array.length) {
			Object[] copy = array.clone();
			array = new Object[index + 1];
			for(int i = 0; i < copy.length; i++) {
				array[i] = copy[i];
			}
		}
		array[index] = value;
	}

	/**
	 * 返回数组元素，超过数组长度的返回null
	 */
	public Object get(int index) {
		if(index > array.length) return null;
		return array[index];
	}
}
