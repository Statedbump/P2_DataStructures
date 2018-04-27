package waitingPolicies;

import customers.Server;
import customers.Customer;
import implementations.ArrayQueue;
import implementations.LinkedQueue;

public class MLMS {
	private LinkedQueue<Customer> arrivalQueue;
	private LinkedQueue<Customer>departureQueue;
	private LinkedQueue<Customer>serviceQueue;
	private Server[] clerks;
	private int servedTime;
	
	public MLMS (LinkedQueue<Customer> arrivalQueue, LinkedQueue<Customer>serviceQueue, 
			LinkedQueue<Customer>departureQueue) {
		this.arrivalQueue = arrivalQueue;
		this.departureQueue = departureQueue;
		this.serviceQueue = serviceQueue;
		
	}
	
public void performService(int numOfServ) {
		
		clerks = new Server[numOfServ];
		
		while(!this.arrivalQueue.isEmpty() || !this.serviceQueue.isEmpty()){
			
			if(!this.arrivalQueue.isEmpty()) {
				this.assignToLine(clerks);
				Customer[] cAtLine = new Customer[numOfServ];
				for(int i = 0; i< numOfServ; i ++) {
					cAtLine[i] = clerks[i].peekFirstInLine();
				if(cAtLine[i].getArrival()>= servedTime && this.serviceQueue.size()!= this.numOfValidLines(clerks)) {
					this.serviceQueue.enqueue(clerks[i].nextCustomer());
				}
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
	
		// this method assigns a customer to the line with the lowest amount of customers
	    public void assignToLine(Server[] line){
	    	int index = 0;
	    	if(!arrivalQueue.isEmpty()){
	    		
	        	
	        	for(int i=1;i<line.length;i++){
	        		if(line[i].lineLength() < line[i-1].lineLength()){
	        			index = i;
	        		}
	        	}
	        	
	        	line[index].add(arrivalQueue.dequeue());
	    	}
	}
		
	

	
	public int numOfValidLines(Server[] clerks) {
		int counter = 0;
		for(int i = 0; i< clerks.length;i++) {
			if(clerks[i].isThereLine()) {
				counter ++;
			}
		}
		return counter;
		
	}
	public int getsServedTime() {
		return servedTime;
	}


}
