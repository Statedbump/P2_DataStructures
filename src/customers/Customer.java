//Kelvin Garcia Muñiz || Luis Cintrón Zayas
//802142644 || StudentNumberHere
//CIIC4020 - 030
package customers;
/**
 * 
 * @author Kelvin García & Luis Cintron 
 * Class representing the costumers in the simulation
 */
public class Customer {
	private int ID; 			//used to identify the costumer
	private boolean isServed;	//true if costumer is served
	private long arrivalTime;	//sets the time the costumer arrived
	private long serviceTime;	//
	private long waitingTime;
	private long departureTime;	//sets the time the costumer left
	private long old;
	int m, line;
	
	/**
	 * Default Constructor
	 */
	public Customer() {
		arrivalTime = serviceTime =departureTime =waitingTime  = 0;
		isServed = false;
	}
	/**
	 * Constructor
	 * @param iD 
	 * @param arrivalTime
	 * @param serviceTime
	 */
	public Customer(long arrivalTime, long serviceTime){
//		this.ID = ID;
		this.arrivalTime = arrivalTime;
		this.serviceTime = serviceTime;
		this.line = 0;
		waitingTime=0;
		old = serviceTime;
		isServed = false;
	}
	
	/**
	 * returns true if costumer has been served
	 * @return 
	 */
	public boolean isServed() {
		return isServed;
	}
	
	/**
	 * sets the customer service status
	 * @param isServed
	 */
	public void setServed(boolean isServed) {
		this.isServed = isServed;
	}
	
	/**
	 * Getter for costumer ID
	 * @return int ID - different for each costumer
	 */
	public int getID() {
		return ID;
	}
	
	/**
	 * Setter for costumer ID
	 * @param ID - int to differ between costumers
	 */
	public void setiD(int ID) {
		this.ID = ID;
	}
	
	/**
	 * Setter for arrivalTime
	 * @param arrivalTime - simulates the exact time the costumer arrived
	 */
	public void setArrival(long arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	/**
	 * sets the service time of the customer
	 * @param serviceTime
	 */
	public void setServiceTime(long serviceTime) {
		this.serviceTime = serviceTime;
	}

	/**
	 * sets the time of the end of service
	 * @param departureTime
	 */
	public void setDeparture(long departureTime) {
		this.departureTime = departureTime;
	}
	
	/**
	 * returns the arrival time of the customer
	 * @return
	 */
	public long getArrival() {
		return arrivalTime;
	}

	/**
	 * returns the service time of the customer
	 * @return
	 */
	public long getServiceTime() {
		return serviceTime;
	}
	
	/**
	 * returns the departure time of the customer
	 * @return
	 */
	public long getDeparture() {
		return departureTime;
	}
	
	/**
	 * resets the service time to its original value
	 */
	public void resetServiceTime() {
		serviceTime = old;
	}
	
	/**
	 * increases the value of m
	 */
	public void increaseM() {
		m++; 
	}
	
	/**
	 * Returns the value m
	 * @return
	 */
	public long getM(){
		return m;
	}
	
	/**
	 * 
	 * @return
	 */
	public long getTimeWaiting(){
		return waitingTime;
	}
	
	/**
	 * sets the waiting time of the customer
	 * @param currentTime
	 */
	public void setTimeWaiting(long currentTime) {
		this.waitingTime = currentTime ;
		
	}
	public long getOldServiceTime(){
		return this.old;
	}
	
}

