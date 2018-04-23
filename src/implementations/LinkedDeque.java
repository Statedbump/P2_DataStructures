package implementations;

import interfaces.Deque;

public class LinkedDeque<E> implements Deque<E> {
	private static class Node<E>{
		private Node<E> next,prev;
		private E element;
		
		public Node(E element,Node<E> next, Node<E>prev) {
			this.element = element;
			this.next = next;
			this.prev = prev;
		}
		public Node(E element) {
			this(element,null,null);
		}
		
		public Node() {
			this(null);
		}
		public Node<E> getNext() {
			return next;
		}
		public void setNext(Node<E> next) {
			this.next = next;
		}
		public Node<E> getPrev() {
			return prev;
		}
		public void setPrev(Node<E> prev) {
			this.prev = prev;
		}
		public E getElement() {
			return element;
		}
		public void setElement(E element) {
			this.element = element;
		}
		
	}
	private Node<E> header,trailer;
	private int size;
	
	public LinkedDeque() {
		header = new Node<>(null,null,null);
		trailer = new Node<>(null,null,header);
		header.setNext(trailer);
		size = 0;
	}
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public boolean isEmpty() {
		
		return size==0;
	}

	@Override
	public E first() {
		if(isEmpty())
			return null;
		return header.getNext().getElement();
	}

	@Override
	public E last() {
		if(isEmpty())
			return null;
		return trailer.getPrev().getElement();
	}

	@Override
	public void addFirst(E e) {
		Node<E> first = header.getNext();
		Node<E>nd = new Node<>(e,first,header);
		first.setPrev(nd);
		header.setNext(nd);
		size++;
	}

	@Override
	public void addLast(E e) {
	
			Node<E> last = trailer.getPrev();
			Node<E> nd = new Node<>(e,trailer,last);
			last.setNext(nd);
			trailer.setPrev(nd);
			size++;
	
		
	}

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
