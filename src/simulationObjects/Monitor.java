//Kelvin Garcia Muñiz || Luis Cintrón Zayas
//802142644 || 841141275
//CIIC4020 - 030
package simulationObjects;

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
	private int indexOfLargestLine() {
		Server s = servers[0];
		int index = 0;
		for(int i = 0 ; i < servers.length; i++) {
			if(servers[i].lineLength() >= s.lineLength()) {
				s = servers[i];
				index = i;
			}
		}
		return index;
	}
	
	private int indexOfSmallestLine() {
		int n= this.indexOfLargestLine();
		Server s = servers[n]; 
		int indexOfSmallest = 0;
		for(int i =1 ; i < servers.length; i++) {
			if( s.lineLength() > servers[(i+n)%servers.length].lineLength() ) {
				s =  servers[(i+n)%servers.length];
				indexOfSmallest =  (i+n)%servers.length;
			}
		}
		return indexOfSmallest;
	}
	
	/**
	 * transfer the customers among lines, if needed, following an MLMS policy
	 * The transfer occurs only when a customer benefits from it. For an instance,
	 * if the moving the customer to a smaller line would put it in the same position
	 * then the transfer will not happen
	 */
	public void transferMLMSBLL() {
		// create a parameters variable containing the smallest an greatest line
		
		// index of server with the greatets line 
		int indexOfLargest = this.indexOfLargestLine();
		//the smallest line 
		int indexOfSmallest = this.indexOfSmallestLine();
		Server sL = servers[indexOfLargest];
		Server sM = servers[indexOfSmallest];
		
		// if the size of the greatest line is greater than or equal than the size of the
		// smallest line + 1 and the length of the greatest line is greater than 1 then:
		if((sL.lineLength() - sM.lineLength()) >1 && sL.lineLength()>=1){
			// add the last customer of the greatest line to the smallest line
			if(!sM.isServing()) {
				sM.add(sL.customerToTransfer());
			}else {
				sM.getLineOfServer().add(sL.customerToTransfer());
			}
			
		}
	}
}
