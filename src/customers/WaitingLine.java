//Kelvin Garcia Muñiz || Luis Cintrón Zayas
//802142644 || StudentNumberHere
//CIIC4020 - 030
package customers;
import implementations.LinkedDeque;
import interfaces.Deque;

/**
 * Represents the lines of customers
 * @author Kelvin Garcia & Luis Cintrón
 *
 */
public class WaitingLine {
	private Deque<Customer> line;
	
	/**
	 * Constructor
	 */
	public WaitingLine() {
		this.line= new LinkedDeque<>();
	}
	
	/**
	 * Adds the customer to the line
	 * @param c
	 */
	public void add(Customer c) {
		line.addLast(c);
	}
	
	/**
	 * returns the first customer in line
	 * @return
	 */
	public Customer first() {
	 return line.first();
	}
	
	/**
	 * returns the number of customer in the line
	 * @return
	 */
	public int numOfCustomersWaiting() {
		return this.line.size();
	}
	
	/**
	 * returns the last customer in the line
	 * @return
	 */
	public Customer last() {
		return this.line.last();
	}
	
	/**
	 * returns the first customer in the line and removes it from it
	 * @return
	 */
	public Customer next() {
		return this.line.removeFirst();
	}
	
	/**
	 * returns true if the line is empty
	 * @return
	 */
	public boolean isEmpty() {
		return line.isEmpty();
	}
	

}
