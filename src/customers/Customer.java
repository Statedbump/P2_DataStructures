
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
	private long departureTime;	//sets the time the costumer left
	int m;
	
	/**
	 * Default Constructor
	 */
	public Customer() {
		arrivalTime = serviceTime =departureTime  = 0;
		isServed = false;
	}
	/**
	 * Constructor
	 * @param iD 
	 * @param arrivalTime
	 * @param serviceTime
	 */
	public Customer(int ID, long arrivalTime, long serviceTime){
		this.ID = ID;
		this.arrivalTime = arrivalTime;
		this.serviceTime = serviceTime;
		isServed = false;
	}
	
	/**
	 * 
	 * @return boolean isServed - true if costumer has been served
	 */
	public boolean isServed() {
		return isServed;
	}
	
	/**
	 * 
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
	 * 
	 * @param serviceTime
	 */
	public void setServiceTime(long serviceTime) {
		this.serviceTime = serviceTime;
	}

	/**
	 * 
	 * @param departureTime
	 */
	public void setDeparture(long departureTime) {
		this.departureTime = departureTime;
	}
	
	/**
	 * 
	 * @return
	 */
	public long getArrival() {
		return arrivalTime;
	}

	/**
	 * 
	 * @return
	 */
	public long getServiceTime() {
		return serviceTime;
	}
	
	/**
	 * 
	 * @return
	 */
	public long getDeparture() {
		return departureTime;
	}
	
	/**
	 * 
	 */
	public void increaseM() {
		m++; 
	}
	
	/**
	 * 
	 * @return
	 */
	public long getTimeWaiting(){
		long time = arrivalTime - departureTime;
		return time;
	}


}

