//Kelvin Garcia Muñiz || Luis Cintrón Zayas
//802142644 || 841141275
//CIIC4020 - 030
package waitingPolicies;

import java.util.LinkedList;

import implementations.SLLQueue;
import simulationObjects.Customer;
import simulationObjects.Server;

//Multiple Lines Multiple Servers
public class MLMS {

	int numOfServers, numOfCustomers; 
	double totalTime = -1; // counts the current time of the simulation (begins in time 0)
	private LinkedList<Customer> arrivingCustomers; // customers to served
	private LinkedList<Customer> waitingLine; // lists of customers waiting in line
	private SLLQueue<Customer> serviceCompleted;
	private Server[]servers; // array of servers
	private LinkedList<Customer> arrivalOrder; // this list at the end will have all customers in order of arriving time

	/**
	 * Constructor
	 * @param customers
	 * @param numberOfServers
	 */
	public MLMS(LinkedList<Customer> customers,int numberOfServers) {
		this.numOfCustomers=customers.size();
		this.arrivingCustomers=copy(customers);
		this.numOfServers=numberOfServers;
		this.servers=new Server[numberOfServers];
		this.waitingLine=new LinkedList<>();

		this.arrivalOrder = new LinkedList<>();

		serviceCompleted = new SLLQueue<>();

		initializeServers(); // run the server init with the specified number	
	}
	
	/**
	 * initializes the servers with the specified number of servers
	 */
	private void initializeServers() {
		for(int i=0;i<numOfServers;i++) {
			servers[i]=new Server(); 
		}
	}
	/**
	 * Begins the simulation
	 */
	public void performService() {
		// while the service has not finished (there are customers to be served)
		while(!isEmpty()) {
			// increase the total time by 1 unit
			totalTime++;
			// for each customer c
			for(Customer c: arrivingCustomers) {
				// if the time of arrival is equal to the current time
				if(c.getArrival()==totalTime) {
					// add that customer to the waiting line
					waitingLine.add(c);
					arrivalOrder.add(c);
				}
			}
			// add the customer to a server with fewer customers
			addToServerAvailable();
			// perform the service
			Service();
		}	
		totalTime++;
		//setWaitingTimeOfallCustomers();
	}
	
	/**
	 * performs the service with a MLMS waiting policy
	 */
	private void Service() {

		//for each server c 
		for(Server c: servers) {
			// if there are servers in line

			if(c.isServing()) {
				// if the servers has not finished with the customer
				
				
				if(c.attending().getServiceTime()!=0) {
					// remove one unit of time from the service time
					c.attending().setServiceTime(c.attending().getServiceTime()-1);
					// increase a unit of time from the departure
					c.attending().setDeparture(c.attending().getDeparture()+1);
				}
				// if the server finished serving the customer
				if(c.attending().getServiceTime()==0) {
					
					//move on to the next customer (tr is customer already served)
					Customer tr=c.nextCustomer();			
					// set the time when the customer left
					tr.setDeparture((long) totalTime);
										
					// set the waiting of the costumer that is next in line time (= current time - the arrival time of that customer)
					tr.setTimeWaiting((tr.getDeparture() - tr.getArrival() - (tr.getOldServiceTime()-1)));
					// add the the costumer to the serviceCompletedqueue
					this.serviceCompleted.enqueue(tr);
					
					// remove the customer from the arriving customers line
					arrivingCustomers.remove(tr);
				}
			}
		}
	}
	


	/**
	 * Adds the customer to an available server
	 */
	private void addToServerAvailable() {
		int index=0;
		
		for(int i=1;i<servers.length;i++) {
			// if the line served by the current server is smaller than the line of the first server
			if(servers[i].lineLength()<servers[0].lineLength())
				// the smallest line is the line of that server
				index=i;
		}
		// if there are customers in line
		if(!waitingLine.isEmpty()){
			if(servers[index].isServing()){
				servers[index].getLineOfServer().add(waitingLine.removeFirst());
			}else {
			Customer c = waitingLine.removeFirst();
			//c.setTimeWaiting((long) (totalTime - c.getArrival()));
			servers[index].add(c);
			}
		}
	}

	/**
	 * Returns a copy of the arriving customers
	 * @param arrivingCustomers
	 * @return
	 */
	private LinkedList<Customer> copy(LinkedList<Customer>arrivingCustomers) {
		LinkedList<Customer> copy= new LinkedList<>();
		// for each customer c
		for(Customer c: arrivingCustomers) {
			//add that customer to the copy
			copy.add(new Customer(c.getArrival(),c.getServiceTime()));
		}
		// return the copy
		return copy;
	}

	
	
	/**
	 * returns the number of Customers to be served
	 * @return
	 */
	public int numberOfCustomer() {
		return numOfCustomers;
	}
	
	/**
	 * returns the total time taken to serve all the customers
	 * @return
	 */
	public double getTotalTime() {
		return totalTime;
	}
	
	/**
	 * returns true if all the customers have been served
	 * @return
	 */
	public boolean isEmpty() {
		return arrivingCustomers.isEmpty();
	}
	
	/**
	 * returns the average time of the MLMS simulation
	 * @return
	 */
	public double getAvgWaitingTime() {
		double avgWaitingTime = 0.0;

		SLLQueue<Customer> serviceCompletedCopy = this.copyOfServiceCompletedQueue();
		while(!serviceCompletedCopy.isEmpty()) {
			Customer c = serviceCompletedCopy.dequeue();

			avgWaitingTime= avgWaitingTime+(c.getTimeWaiting());

		}
		avgWaitingTime = avgWaitingTime/this.numOfCustomers;
		return avgWaitingTime;
	}
	
	/*
	 * returns a copy of the Service Completed Queue
	 * for calculation purposes
	 */
	
	private SLLQueue<Customer> copyOfServiceCompletedQueue(){
		SLLQueue<Customer> copy = new SLLQueue<>();
		
		int j = 0;
		while(!(j==this.serviceCompleted.size())) {
			
			Customer c = serviceCompleted.dequeue();
			Customer cCopy = new Customer(c.getArrival(), c.getOldServiceTime());
			
			cCopy.setDeparture(c.getDeparture());
			cCopy.setTimeWaiting(c.getTimeWaiting());
			serviceCompleted.enqueue(c);
			copy.enqueue(cCopy);
			j++;
			
		}
		
		return copy;

	}
	
	/**
	 * This method checks to see if there was a customer with greater
	 * arrival time that finished before one with less arrival time	
	 * @return
	 */
	public double calculateM() {
		int count = 0;
		double m = 0.00;
		for(Customer c: arrivalOrder) {
			
			for(int i = 0 ; i < serviceCompleted.size();i++) {
				Customer c2 =  serviceCompleted.dequeue();
				if(c2.getArrival()>c.getArrival() && c2.getTimeWaiting()<c.getTimeWaiting()) {
					count ++;
				}
				serviceCompleted.enqueue(c2);
			}
			m = count/this.numOfCustomers;
			
		}
		return m;
		
		
	}

	
	
}
