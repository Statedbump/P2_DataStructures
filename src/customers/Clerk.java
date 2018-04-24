package customers;

import implementations.LinkedDeque;
import interfaces.Deque;

public class Clerk {
	private Deque <Customer> clerks;
	
	public Clerk() {
		clerks = new LinkedDeque<>();
	}
	
	public boolean isValidLine() {
		return !clerks.isEmpty();
	}
	
	public void addToClerkLine(Customer c) {
		clerks.addLast(c);
	}
	public int customersInLine() {
		return clerks.size();
	}
	public Customer finishAttending() {
		return clerks.removeFirst();
	}
	public Customer transfer() {
		return clerks.removeLast();
	}
	public Customer beingAttended() {
		return clerks.first();
	}
	

}
