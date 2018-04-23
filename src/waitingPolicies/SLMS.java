package waitingPolicies;

import customers.Customer;
import implementations.ArrayQueue;
import implementations.LinkedQueue;
import interfaces.Queue;

public class SLMS {
	
	private LinkedQueue<Customer> arrivalQueue;
	private LinkedQueue<Customer>departureQueue;
	private ArrayQueue<Customer>serviceQueue;
	private int servedTime;

	public SLMS( LinkedQueue<Customer> arrivalQueue, ArrayQueue<Customer>serviceQueue, LinkedQueue<Customer>departureQueue) {
		this.arrivalQueue = arrivalQueue;
		this.departureQueue = departureQueue;
		this.serviceQueue = serviceQueue;
		
		
	}
	
	public void performService(int numOfServ) {
		
		while(!this.arrivalQueue.isEmpty() || !this.serviceQueue.isEmpty()){
			
			if(!this.arrivalQueue.isEmpty()) {
				Customer c = this.arrivalQueue.first();
				if(c.getArrival()>= servedTime && this.serviceQueue.size()!= numOfServ) {
					this.serviceQueue.enqueue(this.arrivalQueue.dequeue());
				}
			}
			
			if(!serviceQueue.isEmpty()) {
				Customer c1 = this.serviceQueue.first();
				c1.setServiceTime(c1.getServiceTime()-1);
				if(c1.getServiceTime()==0) {
					this.departureQueue.enqueue(this.serviceQueue.dequeue());
				}else {
					this.serviceQueue.enqueue(this.serviceQueue.dequeue());
				}

			}
			
		}
		
		servedTime++;
	}
	public Queue<Customer> getServicedEndedQueue(){
		return departureQueue;
	}
	public int getsServedTime() {
		return servedTime;
	}
}
