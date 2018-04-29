package waitingPolicies;

import java.util.LinkedList;

import customers.Customer;
import customers.Server;

//Multiple Lines Multiple Servers
public class MLMS {

	int numOfServers, numOfCustomers;
	double totalTime=-1;
	private double averageTime=0;
	private LinkedList<Customer> arrivingCustomers;
	private LinkedList<Customer> waitingList;
	private Server[]servers;

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
		this.waitingList=new LinkedList<>();
		 initializeServers();	
	}
	
	/**
	 * initializes the servers with the specified number of servers
	 */
	public void initializeServers() {
		for(int i=0;i<numOfServers;i++) {
			servers[i]=new Server();
		}
	}
	
	/**
	 * performs the service with a MLMS waiting policy
	 */
	public void Service() {
		for(Server c: servers) {
			if(c.lineLength()!=0) {
				if(c.attending().getServiceTime()!=0) {
					c.attending().setServiceTime(c.attending().getServiceTime()-1);
					c.attending().setDeparture(c.attending().getDeparture()+1);
				}
				if(c.attending().getServiceTime()==0) {
					Customer tr=c.nextCustomer();
					tr.setDeparture((int)totalTime-tr.getArrival());
					averageTime=averageTime+tr.getDeparture();
					arrivingCustomers.remove(tr);
				}
			}
		}
	}
	
	/**
	 * Begins the simulation
	 */
	public void performService() {
		while(!isEmpty()) {
			totalTime++;
			for(Customer c: arrivingCustomers) {
				if(c.getArrival()==totalTime) {
					waitingList.add(c);
				}
			}
			addToServerAvailable();
			Service();
		}	
	}

	/**
	 * Adds the customer to an available server
	 */
	public void addToServerAvailable() {
		int index=0;

		for(int i=1;i<servers.length;i++) {
			if(servers[i].lineLength()<servers[0].lineLength())
				index=i;
		}
		if(!waitingList.isEmpty()){
			servers[index].add(waitingList.removeFirst());
		}
	}

	/**
	 * Returns a copy of the arriving customers
	 * @param arrivingCustomers
	 * @return
	 */
	private LinkedList<Customer> copy(LinkedList<Customer>arrivingCustomers) {
		LinkedList<Customer> copy= new LinkedList<>();
		for(Customer c: arrivingCustomers) {
			copy.add(new Customer(c.getArrival(),c.getServiceTime()));
		}
		return copy;
	}

	/**
	 * returns the average time of the MLMS simulation
	 * @return
	 */
	public double getAverageTime() {
		return averageTime/numOfCustomers;
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
}
