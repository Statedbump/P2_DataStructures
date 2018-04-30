//Kelvin Garcia Muñiz || Luis Cintrón Zayas
//802142644 || StudentNumberHere
//CIIC4020 - 030
package implementations;
import java.util.Arrays;

import interfaces.Queue;

/**
 * Array implementation of Queues
 * @author Kelvin Garcia & Luis Cintrón
 *
 * @param <E>
 */
public class ArrayQueue<E> implements Queue<E> {
	private final static int INITCAP = 4; 
	private E[] elements; 
	private int first, size; 
	@SuppressWarnings("unchecked")
	
	/**
	 * Constructor
	 */
	public ArrayQueue() { 
		elements = (E[]) new Object[INITCAP]; 
		first = 0; 
		size = 0; 
	}
	
	/**
	 * returns the size of the queue
	 */
	public int size() {
		return size;
	}

	/**
	 * true if the queue is empty
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * returns the first element in the queue
	 */
	public E first() {
		if (isEmpty()) return null; 
		return elements[first]; 
	}

	/**
	 * removes the first element in the queue
	 */
	public E dequeue() {
		if (isEmpty()) return null;
		E etr = elements[first]; 
		elements[first] = null;
		first = (first+1)%elements.length;
		// Check if number of available positions in the array exceed 3/4
		// of its total length. If so, and if the current capacity is not
		// less than 2*INITCAP, shrink the internal array to 1/2 of its
		// current length (the capacity of the queue). 
		if (elements.length >= 2*INITCAP && size < elements.length/4)
			changeCapacity(elements.length/2); 
		size--;
		return etr; 
	}

	/**
	 * adds a new element to the queue
	 */
	public void enqueue(E e) {
		if (size == elements.length)   // check capacity, double it if needed
			changeCapacity(2*size); 
		elements[(first+size)%elements.length]=e;
		size++;
	}

	/**
	 * changes the capacity of the queue to the specified parameter
	 * @param newCapacity
	 */
	private void changeCapacity(int newCapacity) { 
		// PRE: newCapacity >= size
		@SuppressWarnings("unchecked")
		E[] newArr = (E[]) new Object[newCapacity];
		for(int i =0;i<size;i++){
			newArr[i]=elements[(first+i)&size];
			elements[(first+i)&size]=null;
		}
		elements = newArr;
		first=0;
	}
	
	/**
	 * used to print the elements in the queue
	 */
	public String toString(){
		return Arrays.toString(elements);
	}
}

