package waitingPolicies;

import java.util.ArrayList;

import customers.Server;
import customers.Customer;
import customers.WaitingLine;
import implementations.LinkedQueue;

public class SLMS {

	private WaitingLine line;
	private LinkedQueue<Customer>serviceCompletedEvent;
	private long totalTime;
	private double sumOfWaiting;
	public SLMS() {
		serviceCompletedEvent = new LinkedQueue<>();
	}

	public void performService(int numOfservers, ArrayList<Customer> ArrivingCustomers) {
		// The simulation will stop when there are no more customers left in both ArrivalQueue and Service Queue
		int numberOfCustomers = ArrivingCustomers.size();
		ArrayList<Server> servers = new ArrayList<>();
		// Servers can now begin attending Customers one at a Time
		this.inititateServers(servers,numOfservers);
		//Customers are then added to the line in order
		//from least to greatest arrival Time
		line = this.addCustomersToLine(ArrivingCustomers);
		totalTime=0;

		while(!line.isEmpty() || serviceCompletedEvent.size() != numberOfCustomers ) {

			if(!line.isEmpty()) {

				for(Server s: servers) {
					//System.out.println(totalTime);
					if(!s.isServing() &&  totalTime >= line.first().getArrival()) {
//						System.out.println("Started Serving Customer at time = " + totalTime);
						sumOfWaiting = sumOfWaiting +(totalTime-line.first().getArrival());
						s.add(line.next());
						if(!line.isEmpty()){
							if(s.attending().getArrival()==line.first().getArrival()){
								s.add(line.next());
							}
						}
						break;
					}
				}
			}

			if(serviceCompletedEvent.size() != numberOfCustomers) {
				for(Server s : servers) {
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
			totalTime++;
		}
	}
	public long getsTotalTime() {
		return totalTime;
	}
	private WaitingLine addCustomersToLine(ArrayList<Customer> ArrivingCustomers) {
		WaitingLine line = new WaitingLine();
		ArrayList<Customer> temp = new ArrayList<>(); 
		while( !ArrivingCustomers.isEmpty()) {
			Customer e = ArrivingCustomers.get(0);
			for(Customer c: ArrivingCustomers ) {

				if(e.getArrival() > c.getArrival()) {
					e = c;
				}
			}
//			System.out.println(e.getArrival()+" " + e.getServiceTime());
			line.add(e);
			temp.add(e);
			ArrivingCustomers.remove(e);
		}
		ArrivingCustomers = temp;
		return line;
	}
	public double getTotalWaitingTime(){
		return sumOfWaiting;
	}
	public double getAverageWaiting(){
		return sumOfWaiting/serviceCompletedEvent.size();
	}
	private void inititateServers(ArrayList<Server> servers, int n) {
		int i = 0;
		while(i<n) {
			servers.add(new Server());
			i++;
		}
	}
}

