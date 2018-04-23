package testerClasses;

import implementations.LinkedDeque;
import interfaces.Deque;

public class DequeTester {
	public static void main(String[] args) {
		Deque<Integer> deq = new LinkedDeque<>();
		Deque<Integer> deq2 = new LinkedDeque<>();
		deq.addFirst(1);
		deq.addLast(2);
		deq.addLast(3);
		deq.addLast(4);
		deq.addLast(5);
		
		while(!deq.isEmpty()) {
			Integer e = deq.removeFirst();
			deq2.addFirst(e);
			System.out.println(e);
		}
		System.out.println(deq2.last());
		System.out.println(deq2.first());
		System.out.println(deq2.size());
	}

}
