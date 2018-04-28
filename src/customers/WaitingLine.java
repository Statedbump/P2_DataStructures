package customers;

import implementations.LinkedDeque;
import interfaces.Deque;

public class WaitingLine {
	private Deque<Customer> line;
	
	public WaitingLine() {
		this.line= new LinkedDeque<>();
	}
	public void add(Customer c) {
		line.addLast(c);
	}
	public Customer first() {
	 return line.first();
	}
	public int numOfCustomersWaiting() {
		return this.line.size();
	}
	public Customer last() {
		return this.line.last();
	}
	public Customer next() {
		return this.line.removeFirst();
	}
	public boolean isEmpty() {
		return line.isEmpty();
	}
	

}
