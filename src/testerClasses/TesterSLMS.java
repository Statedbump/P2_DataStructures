package testerClasses;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import customers.Customer;
import waitingPolicies.SLMS;

public class TesterSLMS {

	public static void main(String[] args) throws FileNotFoundException, CloneNotSupportedException {
		String directory = "inputFiles";
		String file = "data_0.txt";
		Scanner scanner = new Scanner(new File(directory, file));
		long arrivalTime, serviceTime;
		int numOfServers = 1;
		ArrayList<Customer> list = new ArrayList<>();
		ArrayList<Customer> list2 = new ArrayList<>();
		SLMS[] policy1 = new SLMS[3];
		initializePolicies(policy1);
		while(scanner.hasNextInt()){
			arrivalTime = scanner.nextInt();
			serviceTime =scanner.nextInt();
			Customer customer = new Customer(0, arrivalTime, serviceTime);
			list.add(customer); 
		}
		scanner.close();
		System.out.println("--------------------------------");
		for(int i =0; i<3 ; i++) {
			list2 = new ArrayList<Customer>();
			list2 = copyList(list);
			policy1[i].performService(numOfServers, list2);
			System.out.println("SLMS " + numOfServers+ ": " + policy1[i].getTotalWaitingTime()+ "\t" +policy1[i].getAverageWaiting());
			numOfServers+=2;
		}
		System.out.println("--------------------------------");
	}
	public static ArrayList<Customer> copyList(ArrayList<Customer> list){
		ArrayList<Customer> list2 = new ArrayList<>();
		for(Customer E: list){
			list2.add(E);
		}
		return list2;
	}
//	public static LinkedQueue<Customer> copyOf (LinkedQueue<Customer> list) {
//		LinkedQueue<Customer> copy = new LinkedQueue<>();
//
//		int i = 0;
//		while(!(i==list.size())) {
//			Customer c = list.dequeue();
//			Customer copyC = new Customer();
//
//			copyC.setArrival(c.getArrival());
//			copyC.setDeparture(c.getDeparture());
//			copyC.setiD(c.getID());
//			copyC.setServiceTime(c.getServiceTime());
//
//			list.enqueue(c);
//			copy.enqueue(copyC);
//			i++;
//		}
//
//		return copy;
//	}

	//	public static float time(LinkedQueue<Customer> serviceCompletedQueue, int n ) {
	//		   //Calculates time in system
	//		
	//  		float totalTime = 0;
	//  		float arrVal = 0;
	//  		float serVal = 0;
	//  		int i =0;
	//  		
	//  		while(!serviceCompletedQueue.isEmpty()) {
	//  			
	//  			totalTime= (serviceCompletedQueue.dequeue().getWaitingTime() ) + totalTime;
	//  		
	//  			
	//  		}
	//  		totalTime= totalTime/n;
	//  	
	//  		return totalTime;
	//	}

	public static void initializePolicies(SLMS[]policy1) {
		for(int i = 0; i< 3;i++) {
			
			policy1[i]= new SLMS();
			
		}
	}

}
