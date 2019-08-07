package finalexamination_5;

public class TestStringQueue {
	public static void main(String[] args) {
		StringQueue queue = new StringQueue();
		queue.add("Christmas Past"); 
		queue.add("Christmas Present"); 
		queue.add("Christmas Future");
		System.out.println(queue.size());
		System.out.println(queue.poll());
		System.out.println(queue.poll());
		System.out.println(queue.poll());
		System.out.println(queue.poll());
		System.out.println(queue.size());
	}
}
