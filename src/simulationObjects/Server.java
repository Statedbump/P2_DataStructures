//Kelvin Garcia Muñiz || Luis Cintrón Zayas
//802142644 || 841141275
//CIIC4020 - 030
package simulationObjects;

import implementations.LinkedQueue;


/**
 * 
 * Class for  managing multiple servers
 * @author Kelvin Garcia & Luis Cintrón
 */
public class Server {
	private LinkedQueue<Customer> serverQueu;
	private WaitingLine line;
	long time;
	/**
	 * Server Constructor
	 */
	public Server(){
		serverQueu = new LinkedQueue<Customer>();
		this.line = new WaitingLine();
	}

	/**
	 * Return true is the server is serving a customer
	 * @return
	 */
	public boolean isServing(){
		return serverQueu.size()==1;
	}

	/**
	 * adds the client to the server
	 * @param client
	 */
	public void add(Customer client){
		// when working with MLMS type lines if the server is attending a costumer
		//the the arriving customer will be sent to the line 
		// if not the server will attend the client immediately
	
		serverQueu.enqueue(client);;
		
	}

	/**
	 * returns the customer being served
	 * @return
	 */
	public Customer attending(){
		return serverQueu.first();
	}

	/**
	 * returns the next customer to be served
	 * @return
	 */
	public Customer nextCustomer(){
		Customer c = serverQueu.dequeue();
		if(!line.isEmpty()) {
			this.add(line.next());
		}
		return c ;
	}

	/**
	 * returns the length of the line
	 * @return
	 */
	public int lineLength(){
		return this.serverQueu.size()+line.lineLength();
	}

	/**
	 * returns true if there are no customers in the line
	 * @return
	 */
	public boolean isEmpty(){
		return serverQueu.isEmpty();
	}

	/**
	 * returns the first customer in the line
	 * @return
	 */
	public Customer peekFirstInLine(){
		
		return serverQueu.first();
		
	}
	public Customer customerToTransfer() {
		return line.CusToTransfer();
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
	public WaitingLine getLineOfServer() {
		return line;
	}
}