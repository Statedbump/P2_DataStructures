package interfaces;

public interface Queue<E> {

	//Returns the size of the current instance

	int size();


	//Returns true if the current instance is empty; false, if not.  

	boolean isEmpty();

	//Returns the element that has been in the queu for the longest time
	E first();


	//Adds a new element to the queue.  
	void enqueue(E element);

	//Removes and returns the element that has been in the queu for the longest time Returns null if the queue is empty.  

	E dequeue();


}
