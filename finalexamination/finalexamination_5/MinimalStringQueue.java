package finalexamination_5;

/**  
 * This interface represents a collection of objects called a  
 * "queue" in which new Strings are added at the end of the  
 * queue and removed from the front, giving rise to a typical  
 * first-come/first-served waiting line.  
 */ 

public interface MinimalStringQueue {
	 
	/** Adds a new String to the end of the queue */  
	public void add(String str); 
	 
	/** Removes and returns the first String (or null if queue is empty) */  
	public String poll(); 
	 
	/** Returns the number of entries in the queue. */  
	public int size(); 
	 

}
