package customers;

import implementations.LinkedDeque;
import implementations.LinkedQueue;
import interfaces.Deque;

public class Clerk {
private LinkedQueue<Customer> lineQueue;
	
	public Clerk(){
		lineQueue = new LinkedQueue<Customer>();
	}
	
	public boolean isThereLine(){
		return !lineQueue.isEmpty();
	}
	
	public void add(Customer client){
		lineQueue.enqueue(client);
	}
	
	public Customer peekFirstInLine(){
		return lineQueue.first();
	}
	
	public Customer nextCustomer(){
		return lineQueue.dequeue();
	}
	
	public int lineLength(){
		return lineQueue.size();
	}


}
