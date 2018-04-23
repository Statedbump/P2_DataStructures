package customers;

public class Customer {
	private int iD; 			//used to identify the costumer
	private boolean isServed;	//true if costumer is served
	private long arrivalTime;	//sets the time the costumer arrived
	private long serviceTime;	//departureTime - arrivalTime
	private long departureTime;	//sets the time the costumer left
	
	public Customer() {
		arrivalTime = serviceTime =departureTime  = 0;
		isServed = false;
	}
	
	public Customer(int iD, long arrivalTime, long serviceTime){
		this.iD = iD;
		this.arrivalTime = arrivalTime;
		this.serviceTime = serviceTime;
		isServed = false;
	}
	
}
