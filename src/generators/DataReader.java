
package generators;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import customers.Customer;
import implementations.ArrayQueue;
import implementations.LinkedQueue;

public class DataReader {
	public static void main(String[] args) {
		LinkedQueue<Customer> arrivalQ= new LinkedQueue<>();
		ArrayQueue<Customer> serviceQ = new ArrayQueue<>();
		ArrayQueue<Customer> departureQ = new ArrayQueue<>();
		int n;    
		String parentDirectory = "inputFiles";
		Integer[] data;
		Customer customer = new Customer();
		int iD = 1;
		
		long arrivalTime;
		long departureTime;
		long serviceTime=0;
		
		Scanner parameters;
		
		try {
			parameters = new Scanner(new File(parentDirectory, "parameters.txt"));
			// the values of n and m shall be read from file: "inputFiles/parameters.txt". 
			n = parameters.nextInt(); 
			for(int i=0; i<n;i++){
				String fileName = "data_" + i + ".txt"; 
				Scanner inputFile = new Scanner(new File(parentDirectory, fileName)); 
				while (inputFile.hasNext()){
					customer = new Customer(iD, inputFile.nextInt(), inputFile.nextInt());
					arrivalQ.enqueue(customer);
				}
			}
			System.out.println("This works?");
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		while (!arrivalQ.isEmpty() || !serviceQ.isEmpty()) {
			if (!serviceQ.isEmpty()) {
				serviceQ.first().setServed(true);

				if (serviceQ.first().getServiceTime() == 0) {
					serviceQ.first().setDeparture(serviceTime);

					departureQ.enqueue(serviceQ.dequeue());
				} else {
					serviceQ.enqueue(serviceQ.dequeue());
				}

			}

			if(!arrivalQ.isEmpty() && arrivalQ.first().getArrival() == serviceTime){
				serviceQ.enqueue(arrivalQ.dequeue());
			}

			serviceTime++;
		}
		

		int nElements = departureQ.size();
		int avg_time = 0;
		while (!departureQ.isEmpty()) {
			Customer etr = departureQ.dequeue();
			avg_time += etr.getDeparture() - etr.getArrival();
		}

		double avg_system_time = avg_time/nElements;

		System.out.println("Average processing time in system: " + avg_system_time);
	}
}
