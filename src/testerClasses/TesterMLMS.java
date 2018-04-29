//Kelvin Garcia Muñiz || Luis Cintrón Zayas
//802142644 || StudentNumberHere
//CIIC4020 - 030
package testerClasses;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.LinkedList;
import java.util.Scanner;

import customers.Customer;
import waitingPolicies.MLMS;

public class TesterMLMS {

	public static void main(String[] args) throws FileNotFoundException, CloneNotSupportedException {
		String directory = "inputFiles";
		String file = "data_2.txt";
		Scanner scanner = new Scanner(new File(directory, file));
		long arrivalTime, serviceTime;
		int numOfServers = 1;
		LinkedList<Customer> list = new LinkedList<>();
		LinkedList<Customer> list2 = new LinkedList<>();
		while(scanner.hasNextInt()){
			arrivalTime = scanner.nextInt();
			serviceTime =scanner.nextInt();
			Customer customer = new Customer(arrivalTime, serviceTime);
			list.add(customer); 
		}
		scanner.close();
		System.out.println("--------------------------------");
		NumberFormat formatter = new DecimalFormat("#0.00");     
		for(int i =0; i<3 ; i++) {
			list2 = copyList(list);
			MLMS policy= new MLMS(list2,numOfServers);
			
			policy.performService();
			System.out.println("SLMS " + numOfServers+ ": " + policy.getTotalTime()+ "\t" + 
			formatter.format(policy.getAverageTime()));
			numOfServers+=2;
		}
		System.out.println("--------------------------------");
	}
	public static LinkedList<Customer> copyList(LinkedList<Customer> list){
		LinkedList<Customer> list2 = new LinkedList<>();
		for(Customer E: list){
			list2.add(E);
		}
		return list2;
	}
}
