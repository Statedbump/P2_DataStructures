//Kelvin Garcia Muñiz || Luis Cintrón Zayas
//802142644 || StudentNumberHere
//CIIC4020 - 030
package customers;
import implementations.LinkedQueue;

/**
 * 
 * Class for  managing multiple servers
 * @author Kelvin Garcia & Luis Cintrón
 */
public class Server {
private LinkedQueue<Customer> lineQueue;
	
	/**
	 * Server Constructor
	 */
	public Server(){
		lineQueue = new LinkedQueue<Customer>();
	}
	
	/**
	 * Return true is the server is serving a customer
	 * @return
	 */
	public boolean isServing(){
		return lineQueue.size()==1;
	}
	
	/**
	 * adds the client to the server
	 * @param client
	 */
	public void add(Customer client){
		lineQueue.enqueue(client);
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
		return lineQueue.dequeue();
	}
	
	/**
	 * returns the length of the line
	 * @return
	 */
	public int lineLength(){
		return lineQueue.size();
	}
}