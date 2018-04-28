package waitingPolicies;

import customers.Server;
import customers.WaitingLine;

import java.util.ArrayList;

import customers.Customer;
import implementations.ArrayQueue;
import implementations.LinkedQueue;

public class MLMS {

	private WaitingLine line;
	private LinkedQueue<Customer>serviceCompletedEvent;
	private long totalTime;
	private double sumOfWaiting;
	
	public MLMS () {
		
		serviceCompletedEvent = new LinkedQueue<>();
		
		
	}
	
public void performService(int numOfservers,ArrayList<Customer> ArrivingCustomers) {
	// The simulation will stop when there are no more customers left in both ArrivalQueue and Service Queue
			int numberOfCustomers = ArrivingCustomers.size();
			ArrayList<Server> servers = new ArrayList<>();
			ArrayList<WaitingLine> lines =  new ArrayList<>();
			// Servers can now begin attending Customers one at a Time that is in their line
			this.inititateServersWithLine(servers, lines,numOfservers);
			
			totalTime=0;
			while(serviceCompletedEvent.size() != numberOfCustomers ) {
				this.addCustomerToLine(totalTime, ArrivingCustomers, lines);
			}

	
	
}

//This method Adds The customer to the Line with the lowest amount of people when they arrived
private void reachedLine(ArrayList<WaitingLine> lines,Customer c, long time) {
	WaitingLine l0 = lines.get(0);
	if(c.getArrival()<= time) {
	for(WaitingLine l : lines) {
		if(l.numOfCustomersWaiting()<l0.numOfCustomersWaiting()) {
			l0 = l;
		}
	}
	l0.add(c);
	}
	
}
private void addCustomerToLine(long time,ArrayList<Customer> ArrivingCustomers,ArrayList<WaitingLine> lines) {
	for(Customer c: ArrivingCustomers) {
		this.reachedLine(lines, c, time);
	}
}
private void inititateServersWithLine(ArrayList<Server> servers,ArrayList<WaitingLine> lines, int n) {
	int i = 0;
	while(i<n) {
		servers.add(new Server());
		lines.add(new WaitingLine());
		i++;
	}
}

}
