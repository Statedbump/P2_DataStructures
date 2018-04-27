package testerClasses;
import java.io.BufferedReader;
import java.io.File;
import java.util.ArrayList;

import customers.Customer;
import implementations.ArrayQueue;
import implementations.LinkedQueue;
import waitingPolicies.SLMS;

public class TesterSLMS {

	public static void main(String[] args) {
		Customer c1 = new Customer(1,0,3);
		Customer c2 = new Customer(1,2,1);
		Customer c3 = new Customer(1,4,3);
		Customer c4 = new Customer(1,5,2);
		Customer c5 = new Customer(1,6,6);
		Customer c6 = new Customer(1,7,6);
		Customer c7 = new Customer(1,9,3);
		Customer c8 = new Customer(1,10,7);
		Customer c9 = new Customer(1,11,3);
		Customer c10 = new Customer(1,15,4);
		
		ArrayList<Customer> list = new ArrayList<>();
		list.add(c1);
		list.add(c2);
		list.add(c3);
		list.add(c4);
		list.add(c5);
		list.add(c6);
		list.add(c7);
		list.add(c8);
		list.add(c9);
		list.add(c10);
		
		SLMS serv1= new SLMS();
		
		serv1.performService(1, list);
		System.out.println(serv1.getsTotalTime());

		
		
	}
	public static LinkedQueue<Customer> copyOf (LinkedQueue<Customer> list) {
		LinkedQueue<Customer> copy = new LinkedQueue<>();
		
		int i = 0;
		while(!(i==list.size())) {
			Customer c = list.dequeue();
			Customer copyC = new Customer();
			
			copyC.setArrival(c.getArrival());
			copyC.setDeparture(c.getDeparture());
			copyC.setiD(c.getID());
			copyC.setServiceTime(c.getServiceTime());
			
			list.enqueue(c);
			copy.enqueue(copyC);
			i++;
		}
		
		return copy;
	}

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
	


}
