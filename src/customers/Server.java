//Kelvin Garcia Muñiz || Luis Cintrón Zayas
//802142644 || StudentNumberHere
//CIIC4020 - 030
package customers;
import implementations.LinkedDeque;


/**
 * 
 * Class for  managing multiple servers
 * @author Kelvin Garcia & Luis Cintrón
 */
public class Server {
	private LinkedDeque<Customer> lineQueue;
	long time;
	/**
	 * Server Constructor
	 */
	public Server(){
		lineQueue = new LinkedDeque<Customer>();
	}

	/**
	 * Return true is the server is serving a customer
	 * @return
	 */
	public boolean isServing(){
		return lineQueue.size()>0;
	}

	/**
	 * adds the client to the server
	 * @param client
	 */
	public void add(Customer client){
		lineQueue.addLast(client);
	}

	/**
	 * returns the customer being served
	 * @return
	 */
	public Customer attending(){
		return lineQueue.first();
	}

	/**
	 * returns the next customer to be served
	 * @return
	 */
	public Customer nextCustomer(){
		return lineQueue.removeFirst();
	}

	/**
	 * returns the length of the line
	 * @return
	 */
	public int lineLength(){
		return lineQueue.size();
	}

	/**
	 * returns true if there are no customers in the line
	 * @return
	 */
	public boolean isEmpty(){
		return !lineQueue.isEmpty();
	}

	/**
	 * returns the first customer in the line
	 * @return
	 */
	public Customer peekFirstInLine(){
		return lineQueue.first();
	}
	public Customer customerToTransfer() {
		return lineQueue.removeLast();
	}

	/**
	 * sets the wainting time of the server's line
	 * @param time
	 */
	public void setWaitingCustTime(long time){
		this.time += time;
	}

	/**
	 * returns the sum of all waiting times of all customers
	 * @return
	 */
	public long getWaitingCustTime() {
		return time;
	}
}