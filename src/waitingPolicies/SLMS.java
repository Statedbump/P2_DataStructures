package waitingPolicies;

import customers.Customer;
import implementations.ArrayQueue;
import implementations.LinkedQueue;
import interfaces.Queue;

public class SLMS {
	
	private LinkedQueue<Customer> arrivalQueue;
	private LinkedQueue<Customer>serviceQueue;
	private ArrayQueue<Customer>departureQueue;
	private int servedTime;

	public SLMS(LinkedQueue<Customer> arrivalQueue, LinkedQueue<Customer>serviceQueue, ArrayQueue<Customer>departureQueue) {
		this.arrivalQueue = arrivalQueue;
		this.departureQueue = departureQueue;
		this.serviceQueue = serviceQueue;
		this.servedTime = 0;
	}
	
	public void performService(int numOfServ) {	
		while(!this.arrivalQueue.isEmpty() || !this.serviceQueue.isEmpty()){
			if(!serviceQueue.isEmpty()) {
				Customer c1 = this.serviceQueue.first();
				c1.setServiceTime(c1.getServiceTime()-1);
				if(c1.getServiceTime()==0) {
					this.departureQueue.enqueue(this.serviceQueue.dequeue());
				}else {
					this.serviceQueue.enqueue(this.serviceQueue.dequeue());
				}
			}
			if(!this.arrivalQueue.isEmpty()) {
				Customer c = this.arrivalQueue.first();
				if(c.getArrival()>= servedTime && this.serviceQueue.size()!= numOfServ) {
					c.setDeparture(servedTime);
					this.serviceQueue.enqueue(this.arrivalQueue.dequeue());
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
