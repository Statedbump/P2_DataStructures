package implementations;

import interfaces.Queue;

public class LinkedQueue<E> implements Queue<E> {
	// inner class for nodes in singly linked lists
		private static class Node<E> {
			private E element; 
			private Node<E> next; 
			private Node(E e){
				element = e;
				next = null;
			}
			public E getElement() {
				return element;
			}
			public Node<E> getNext() {
				return next;
			}
			public void setNext(Node<E> next) {
				this.next = next;
			}
			public void clean() { 
				element = null; 
				next = null; 
			}
			
		}	
		private Node<E> first, last;   // references to first and last node
		private int size; 
		
		public LinkedQueue() {           // initializes instance as empty queue
			first = last = null; 
			size = 0; 
		}
		@Override
		public int size() {
			return size;
		}
		@Override
		public boolean isEmpty() {
			return size == 0;
		}
		@Override
		public E first() {
			if (isEmpty()) return null;
			return first.getElement(); 
		}
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

}
