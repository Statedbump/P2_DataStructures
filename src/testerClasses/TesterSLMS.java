package testerClasses;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import customers.Customer;
import implementations.ArrayQueue;
import implementations.LinkedQueue;
import waitingPolicies.SLMS;

public class TesterSLMS {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		LinkedQueue<Customer> arrivalQ = new LinkedQueue<Customer>();
		LinkedQueue<Customer> serviceQ = new LinkedQueue<Customer>();
		ArrayQueue<Customer> departureQ = new ArrayQueue<Customer>();
		double averageTime;
		String directory = "inputFiles";
		String fileName = "data_0.txt";
		File inputFile = new File(directory,fileName);
//		BufferedReader reader;
		String line;
		int arrivalTime=0;
		int serviceTime=0;
		
//		reader = new BufferedReader(new FileReader(inputFile));
		Scanner reader = new Scanner(new File(directory, fileName));
		while(reader.hasNextInt()){
			arrivalTime = reader.nextInt();
			serviceTime =reader.nextInt();
			System.out.println(arrivalTime+" "+serviceTime);
			Customer customer = new Customer(0, arrivalTime, serviceTime);
			arrivalQ.enqueue(customer);
		}
		reader.close();
		SLMS serv = new SLMS(arrivalQ,serviceQ, departureQ);
		int initServers = 1;
		while(initServers<6){
			serv.performService(initServers);
			averageTime = (arrivalTime - serv.getsServedTime())/departureQ.size();
			System.out.print("\nSLMS "+initServers+": "+serv.getsServedTime()+" ");
			System.out.printf("%.2f", averageTime);
			initServers+=2;
		}
		
	}
}