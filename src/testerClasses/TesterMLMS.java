//Kelvin Garcia Muñiz || Luis Cintrón Zayas
//802142644 || StudentNumberHere
//CIIC4020 - 030
package testerClasses;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Scanner;

import customers.Customer;
import waitingPolicies.MLMS;


public class TesterMLMS {

	public static void main(String[] args) throws FileNotFoundException, CloneNotSupportedException {
		String directory = "inputFiles";
		String file = "data_1.txt";
		Scanner scanner = new Scanner(new File(directory, file));
		long arrivalTime, serviceTime;
		int numOfServers = 1;
		ArrayList<Customer> list = new ArrayList<>();
		ArrayList<Customer> list2 = new ArrayList<>();
		MLMS[] policy1 = new MLMS[3];
		
		initializePolicies(policy1);
		while(scanner.hasNextInt()){
			arrivalTime = scanner.nextInt();
			serviceTime =scanner.nextInt();
			Customer customer = new Customer(0, arrivalTime, serviceTime);
			list.add(customer); 
		}
		scanner.close();
		System.out.println("--------------------------------");
		NumberFormat formatter = new DecimalFormat("#0.00");     
		for(int i =0; i<3 ; i++) {
			list2 = new ArrayList<Customer>();
			list2 = copyList(list);
			policy1[i].performService(numOfServers, list2);
			System.out.println("SLMS " + numOfServers+ ": " + policy1[i].getsTotalTime()+ "\t" + 
			formatter.format(policy1[i].getAverageWaiting()));
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

	public static void initializePolicies(MLMS[]policy1 ) {
		for(int i = 0; i< 3;i++) {
			
			policy1[i]= new MLMS();
			
			
		}
	}

}
