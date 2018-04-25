//package waitingPolicies;
//
//import customers.Clerk;
//import customers.Customer;
//import implementations.ArrayQueue;
//import implementations.LinkedQueue;
//
//public class MLMS {
//	private LinkedQueue<Customer> arrivalQueue;
//	private LinkedQueue<Customer>departureQueue;
//	private ArrayQueue<Customer>serviceQueue;
//	private Clerk[] clerks;
//	private int servedTime;
//	
//	public MLMS (LinkedQueue<Customer> arrivalQueue, ArrayQueue<Customer>serviceQueue, 
//			LinkedQueue<Customer>departureQueue) {
//		this.arrivalQueue = arrivalQueue;
//		this.departureQueue = departureQueue;
//		this.serviceQueue = serviceQueue;
//		
//	}
//	
//public void performService(int numOfServ) {
//		
//		clerks = this.waitingLineofClerk(numOfServ);
//		
//		while(!this.arrivalQueue.isEmpty() || !this.serviceQueue.isEmpty()){
//			
//			if(!this.arrivalQueue.isEmpty()) {
//				this.getInLine(clerks);
//				
//				if(c.getArrival()>= servedTime && this.serviceQueue.size()!= numOfServ) {
//					this.serviceQueue.enqueue(this.arrivalQueue.dequeue());
//				}
//			}
//			
//			if(!serviceQueue.isEmpty()) {
//				Customer c1 = this.serviceQueue.first();
//				c1.setServiceTime(c1.getServiceTime()-1);
//				if(c1.getServiceTime()==0) {
//					this.departureQueue.enqueue(this.serviceQueue.dequeue());
//				}else {
//					this.serviceQueue.enqueue(this.serviceQueue.dequeue());
//				}
//
//			}
//			
//		}
//		
//		servedTime++;
//	}
//	public void getInLine(Clerk[] clerks) {
//		// this method assigns a customer to the line with the lowest amount of customers
//		int position = 0;
//		while(!arrivalQueue.isEmpty()) {
//			for(int i = 1 ;i< clerks.length;i++) {
//				if(clerks[i].customersInLine() < clerks[i-1].customersInLine()) {
//					position = i;
//				}
//			}
//			clerks[position].addToClerkLine(arrivalQueue.dequeue());
//		}
//		
//	}
//	public Clerk[] waitingLineofClerk(int numOfServ) {
//		Clerk[] servers = (Clerk[])new Object[numOfServ];
//		return servers;
//	}
//
//}
