//Kelvin Garcia Muñiz || Luis Cintrón Zayas
//802142644 || StudentNumberHere
//CIIC4020 - 030
package implementations;
import interfaces.Queue;

/**
 * A linked list implementation of queues
 * @author Kelvin Garcia & Luis Cintrón
 *
 * @param <E>
 */
public class SLLQueue<E> implements Queue<E> {
	// inner class for nodes in singly linked lists
	private static class Node<E> {
		private E element; 
		private Node<E> next;

		/**
		 * Constructor
		 * @param e
		 */
		private Node(E e){
			element = e;
			next = null;
		}

		/**
		 * returns element at node
		 * @return
		 */
		public E getElement() {
			return element;
		}

		/**
		 * returns the next node
		 * @return
		 */
		public Node<E> getNext() {
			return next;
		}

		/**
		 * sets the next node to the specified value
		 * @param next
		 */
		public void setNext(Node<E> next) {
			this.next = next;
		}

	}	
	private Node<E> first, last;   // references to first and last node
	private int size; 

	/**
	 * Constructor
	 */
	public SLLQueue() {           // initializes instance as empty queue
		first = last = null; 
		size = 0; 
	}

	/**
	 * returns the size of the queue
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * returns true if the queue is empty
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * returns the first element of the queue
	 */
	@Override
	public E first() {
		if (isEmpty()) return null;
		return first.getElement(); 
	}

	/**
	 * removes the element of the queue
	 * @return element removed
	 */
	@Override
	public E dequeue() {
		if (isEmpty()) 
			return null;
		Node<E> ntr = first;
		first = first.getNext();
		if(size == 1 ) {
			last = null;
		}
		size --;
		E etr = ntr.getElement();
		return etr;
	}

	/**
	 * adds an element to the queue
	 */
	@Override
	public void enqueue(E e) {
		if (size == 0) 
			first = last = new Node<>(e); 
		else { 
			Node<E> newest = new Node<>(e);
			last.setNext(newest); 	
			last = newest;
		}
		size++; 
	}

	/**
	 * 
	 */
	public String toString() {
		Node<E> fn = first;
		String s = "{";
		boolean temporary = true;
		if(isEmpty())
			return "{ }";

		while(fn!= null) {
			if(temporary == true) {
				s = s + fn.getElement();
				temporary = false;
			}else {
				s = s + "," +  fn.getElement();
			}
			fn = fn.getNext();
		}
		return s + "}" ;
	}

	/**
	 * Creates a clone of the queue
	 * @throws CloneNotSupportedException
	 */
	public SLLQueue<E> clone() throws CloneNotSupportedException {			

		SLLQueue<E> copy = new SLLQueue<E>();
		Node<E> current = first;
		E etr;

		while(current != null){
			etr = current.element;
			copy.enqueue(etr);
			current = current.next;
		}
		return copy;
	}
}
