//Kelvin Garcia Muñiz || Luis Cintrón Zayas
//802142644 || StudentNumberHere
//CIIC4020 - 030
package customers;

/**
 * Class to manage the transfer of customers between lines according
 * to their specified policy
 * @author Kelvin Garcia & Luis Cintrón
 *
 */
public class Monitor {
	
	private Server[] servers;
	
	/**
	 * Constructor
	 * @param servers
	 */
	public Monitor(Server[] servers) {
		this.servers = servers;
	}
	
	/**
	 * Returns the values of the smallest and largest lines
	 * @return
	 * Return an int array containing the smallest and largest lines among the servers
	 * where the value at 0 is the line with the greatest size and the value 1 is the 
	 * line with the smallest size
	 */
	private int[] checkLinesSize() {
		// initialize an array of size 2 to contain the lines
		int[] lines = new int[2];
		// initialize an array containing the sizes of the lines of each server
		int[] sizes = new int[servers.length];
		for(int i=0;i<sizes.length;i++) {
			// initialize the size of each line as an element of the array of sizes
			sizes[i]=servers[i].lineLength();
		}
		
		// set the max value to the first size in the array
		int maxValue = sizes[0];
		for (int i = 1; i < sizes.length; i++) {
			// if the size at position i is greater than the max value
			if (sizes[i] >= maxValue) {
				// then the new max value is the size at position i
				maxValue = i;
			}
		}
		// the first value in the array of lines will be equal to the greatest size
		lines[0]= maxValue;
		// set the min value to the first size in the array
		int minValue = sizes[0];
		for (int i = 1; i < sizes.length; i++) {
			// if the size at position i is smaller than the min value
			if (sizes[i] <= minValue) {
				// then the new min value is the size at position i
				minValue = i;
			}
		}
		// the second value in the array of lines will be equal to the smallest size
		lines[1]=minValue;
		// return an array with the smallest and greates value
		return lines;
	}
	
	/**
	 * transfer the customers among lines, if needed, following an MLMS policy
	 * The transfer occurs only when a customer benefits from it. For an instance,
	 * if the moving the customer to a smaller line would put it in the same position
	 * then the transfer will not happen
	 */
	public void transferMLMSBLL() {
		// create a parameters variable containing the smallest an greatest line
		int[] parameters=checkLinesSize();
		// the greatets line will be in position 0
		int bigLine = parameters[0];
		//the smallest line will be in position 1
		int smallLine = parameters[1];
		// if the size of the greatest line is greater than or equal than the size of the
		// smallest line + 1 and the length of the greatest line is greater than 1 then:
		if(bigLine>=(smallLine+1) && servers[bigLine].getLineOfServer().lineLength()>0){
			// add the last customer of the greatest line to the smallest line
			servers[smallLine].add(servers[bigLine].customerToTransfer());
		}
	}
}
