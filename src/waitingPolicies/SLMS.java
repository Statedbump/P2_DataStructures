//Kelvin Garcia Muñiz || Luis Cintrón Zayas
//802142644 || 841141275
//CIIC4020 - 030
package waitingPolicies;
import java.util.ArrayList;
import java.util.LinkedList;

import implementations.SLLQueue;
import simulationObjects.Customer;
import simulationObjects.Server;
import simulationObjects.WaitingLine;

/**
 * 
 * Simulates and event where there are multiple servers and one line of customers
 * @author Kelvin García & Luis Cintrón
 */
public class SLMS {

	private WaitingLine line; //contains all the customers waiting to be served after arrival
	private SLLQueue<Customer>serviceCompletedEvent; // contains all the customers that have been served
	private long totalTime; //the total time taken to serve all customers
	private double sumOfWaiting; //the sum of the times waited by each customer
	int numberOfCustomers; // the number of customers to be served
	public SLMS() {
		serviceCompletedEvent = new SLLQueue<>();
	}

	/**
	 * performs the action according to the waiting policy established for Single lines and Multiple servers
	 * @param numOfservers - number of servers in the operation
	 * @param ArrivingCustomers - the customers to be served
	 */
	public void performService(int numOfservers, LinkedList<Customer> ArrivingCustomers) {
		// The simulation will stop when there are no more customers being served or to be served
		this.numberOfCustomers = ArrivingCustomers.size(); //sets the number of customers for this simulation
		ArrayList<Server> servers = new ArrayList<>();
		// Servers can now begin attending Customers one at a Time
		this.inititateServers(servers,numOfservers);
		//Customers are then added to the line in order from least to greatest arrival Time
		line = this.addCustomersToLine(ArrivingCustomers); //adds the arriving customers to the line in order of arrival
		totalTime=0; //sets the time running 

		//as long as there are customers in-line or the number of customers served does not equal the number of customers then
		while(!line.isEmpty() || serviceCompletedEvent.size() != numberOfCustomers ) {
			if(!line.isEmpty()) { // if the line is not empty
				for(Server s: servers) { // for each server s 
					// if that server is not serving and the total time is greater than the 
					//arrival time of the first in line then
					if(!s.isServing() &&  totalTime >= line.first().getArrival()){ 
						//add the waiting time of that customer to the sum of all waiting times
						//where waiting time is the current time (totalTime) - the arrival time of that customer
						sumOfWaiting = sumOfWaiting +(totalTime-line.first().getArrival());
						s.add(line.next()); // add that customer to the service
						if(!line.isEmpty() && s.attending().getArrival() == line.first().getArrival() && s != null) {
						}
						else {
							break;
						}
					}
				}
			}
			//if the number of customers served does not equal the number of customers then
			if(serviceCompletedEvent.size() != numberOfCustomers) { 
				//for each server s
				for(Server s : servers) {
					// if s is serving
					if(s.isServing()) {
						//then reduce the serving time by 1 unit of time
						s.attending().setServiceTime(s.attending().getServiceTime()-1);
						// if the service is done
						if(s.attending().getServiceTime()==0) {
							// move to the next customer
							Customer c = s.nextCustomer();
							// reset the service time of the customer
							c.resetServiceTime();
							// add the customer to the queue of served customers
							serviceCompletedEvent.enqueue(c);	
						}
					}
				}
			}
			//increase the actual time
			totalTime++;
		}
	}
	/**
	 * getter for the total time of the services
	 * @return totalTime
	 */
	public long getsTotalTime() {
		return totalTime;
	}
	
	/**
	 * adds the customer to a line according to their arrival time
	 * @param ArrivingCustomers - the customers to be served
	 * @return line containing all the customers to be served
	 */
	private WaitingLine addCustomersToLine(LinkedList<Customer> ArrivingCustomers) {
		WaitingLine line = new WaitingLine();
		LinkedList<Customer> temp = new LinkedList<>(); //used to keep the original ArrivingCustomers list
		// while there are customers
		while( !ArrivingCustomers.isEmpty()) {
			Customer e = ArrivingCustomers.get(0);
			for(Customer c: ArrivingCustomers ) {

				if(e.getArrival() > c.getArrival()) {
					e = c;
				}
			}
			// add that customer to the line
			line.add(e);
			// add that customer to the copy
			temp.add(e);
			// remove that customer from the ArrivingCustomers
			ArrivingCustomers.remove(e);
		}
		// reset the arriving customers
		ArrivingCustomers = temp;
		// return the line with all the customers
		return line;
	}
	
	/**
	 * getter for the sum of all waiting times of the customers
	 * @return 
	 */
	public double getTotalWaitingTime(){
		return sumOfWaiting;
	}
	
	/**
	 * getter for the average waiting time of the customers
	 * @return
	 */
	public double getAverageWaiting(){
		//averageWaiting time = (sum of all waiting times) / (number of customers served)
		return sumOfWaiting/serviceCompletedEvent.size();
	}
	
	/**
	 * sets the servers according to the number available
	 * @param servers - perform the service 
	 * @param n - number of servers available
	 */
	private void inititateServers(ArrayList<Server> servers, int n) {
		int i = 0; //counter
		//while i is less than the number of servers
		while(i<n) {
			//add a new server
			servers.add(new Server());
			//increase counter
			i++;
		}
	}
	
	/**
	 * getter for the number of customers
	 * @return
	 */
	public int getTotalOfCustomers(){
		return numberOfCustomers;
	}
}