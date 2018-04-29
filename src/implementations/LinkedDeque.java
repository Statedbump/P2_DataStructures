//Kelvin Garcia Muñiz || Luis Cintrón Zayas
//802142644 || StudentNumberHere
//CIIC4020 - 030
package implementations;

import interfaces.Deque;

/**
 * 
 * @author Kelvin Garcia & Luis Cintrón
 *
 * @param <E>
 */
public class LinkedDeque<E> implements Deque<E> {
	private static class Node<E>{
		private Node<E> next,prev;
		private E element;

		/**
		 * Node constructor
		 * @param element
		 * @param next
		 * @param prev
		 */
		public Node(E element,Node<E> next, Node<E>prev) {
			this.element = element;
			this.next = next;
			this.prev = prev;
		}

		/**
		 * returns the next node in the queue
		 * @return
		 */
		public Node<E> getNext() {
			return next;
		}

		/**
		 * sets the specified node in the next position
		 * @param next
		 */
		public void setNext(Node<E> next) {
			this.next = next;
		}

		/**
		 * returns the previous node
		 * @return
		 */
		public Node<E> getPrev() {
			return prev;
		}

		/**
		 * sets the specified node in the previous position
		 * @param prev
		 */
		public void setPrev(Node<E> prev) {
			this.prev = prev;
		}

		/**
		 * returns the element in the node
		 * @return
		 */
		public E getElement() {
			return element;
		}

		/**
		 * sets the element in the node to the specified parameter
		 * @param element
		 */
		public void setElement(E element) {
			this.element = element;
		}

	}
	private Node<E> header,trailer;
	private int size;

	/**
	 * Linked Dequeue Constructor
	 */
	public LinkedDeque() {
		header = new Node<>(null,null,null);
		trailer = new Node<>(null,null,header);
		header.setNext(trailer);
		size = 0;
	}

	/**
	 * returns the size of the queue
	 */
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	/**
	 * true if the queue is empty
	 */
	@Override
	public boolean isEmpty() {

		return size==0;
	}

	/**
	 * returns the first element in the queue
	 */
	@Override
	public E first() {
		if(isEmpty())
			return null;
		return header.getNext().getElement();
	}

	/**
	 * returns the last element in the queue
	 */
	@Override
	public E last() {
		if(isEmpty())
			return null;
		return trailer.getPrev().getElement();
	}

	/**
	 * adds the first element to the queue
	 */
	@Override
	public void addFirst(E e) {
		Node<E> first = header.getNext();
		Node<E>nd = new Node<>(e,first,header);
		first.setPrev(nd);
		header.setNext(nd);
		size++;
	}

	/**
	 * adds the last element to the queue
	 */
	@Override
	public void addLast(E e) {
		Node<E> last = trailer.getPrev();
		Node<E> nd = new Node<>(e,trailer,last);
		last.setNext(nd);
		trailer.setPrev(nd);
		size++;
	}

	/**
	 * removes and returns the first element in the queue
	 */
	@Override
	public E removeFirst() {
		if(isEmpty()) {
			return null;
		}
		Node<E> first = header.getNext();
		Node<E> after = first.getNext();
		header.setNext(after);
		after.setPrev(header);
		E etr = first.getElement();
		first.setNext(null);
		first.setPrev(null);
		size--;
		return etr;
	}

	/**
	 * removes and returns the last element in the queue
	 */
	@Override
	public E removeLast() {
		if(isEmpty()) {
			return null;
		}
		Node<E> last = trailer.getPrev();
		Node<E> before = last.getPrev();
		trailer.setPrev(before);
		before.setNext(trailer);
		E etr = last.getElement();
		last.setNext(null);
		last.setPrev(null);
		size--;
		return etr;
	}

}
