
package waitingPolicies;

import customers.Server;
import customers.WaitingLine;

import java.util.ArrayList;

import customers.Customer;
import implementations.ArrayQueue;
import implementations.LinkedQueue;

public class MLMS {


	private LinkedQueue<Customer>serviceCompletedEvent;
	private long totalTime;
	private double sumOfWaiting;

	public MLMS () {

		serviceCompletedEvent = new LinkedQueue<>();
		totalTime =0;

	}

	public void performService(int numOfservers,ArrayList<Customer> ArrivingCustomers) {
		// The simulation will stop when there are no more customers left in both ArrivalQueue and Service Queue
				int numberOfCustomers = ArrivingCustomers.size();
				ArrayList<Server> servers = new ArrayList<>();
				ArrayList<WaitingLine> lines =  new ArrayList<>();
				// Servers can now begin attending Customers one at a Time that is in their line
				this.inititateServersWithLine(servers, lines,numOfservers);

				while(serviceCompletedEvent.size() != numberOfCustomers ) {

					this.addCustomerToLine(totalTime, ArrivingCustomers, lines);


					for(Server s : servers) {
						if(!s.isServing() && !lines.get(servers.indexOf(s)).isEmpty()) {
							//System.out.println("Started Serving Customer at time = " + totalTime);
							Customer c = lines.get(servers.indexOf(s)).next();
							s.add(c);
							sumOfWaiting = sumOfWaiting +(totalTime-s.attending().getArrival());
						}
					}

					if(serviceCompletedEvent.size() != numberOfCustomers) {
						for(Server s: servers) {
							if(s.isServing()) {
								s.attending().setServiceTime(s.attending().getServiceTime()-1);

								if(s.attending().getServiceTime()==0) {
									Customer c = s.nextCustomer();
									c.resetServiceTime();
									//							c.setDeparture(totalTime);
									serviceCompletedEvent.enqueue(c);	
								}
							}
						}
					}
					//System.out.println(totalTime);
					totalTime++;
				}
				//System.out.println(totalTime);

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
	public double getAverageWaiting(){
		return sumOfWaiting/serviceCompletedEvent.size();
	}
	public double getTotalWaitingTime(){
		return sumOfWaiting;
	}
	public long getsTotalTime() {
		return totalTime;
	}

}
