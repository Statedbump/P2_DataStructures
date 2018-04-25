package generators;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

import customers.Customer;
import waitingPolicies.SLMS;
import implementations.ArrayQueue;
import implementations.LinkedQueue;

public class DataReader{

	public static void main(String[] args) throws IOException {
		
		LinkedQueue<Customer> arrivalQueue = new LinkedQueue<Customer>();
		LinkedQueue<Customer> serviceStartsQueue = new LinkedQueue<Customer>();
		LinkedQueue<Customer> serviceCompletedQueue = new LinkedQueue<Customer>();
		int n;
		double averageTime, value;
		SLMS[] firstPolicy = {null,null,null};
		
		String parentDirectory = "inputFiles";
		Scanner parameters = new Scanner(new File(parentDirectory, "parameters.txt")); 
		// the values of n and m shall be read from file: "inputFiles/parameters.txt". 
		n = parameters.nextInt(); 
		String fileName = "data_0.txt"; 
		
		File inputfile = new File(parentDirectory, fileName);
		BufferedReader dataReader = null;
        String dataline;
        int arrTime = 0;
        int serTime =0;

            dataReader = new BufferedReader(new FileReader(inputfile));
            while ((dataline = dataReader.readLine()) != null) {

               String[] data =dataline.split(",");             
              
               arrTime = Integer.parseInt(data[0]);
               serTime = Integer.parseInt(data[1].substring(data[1].length()-1));
               Customer element = new Customer(0, arrTime, serTime);
              
              arrivalQueue.enqueue(element);  
              
            }           
            if (dataReader != null) {
                    dataReader.close();

        }
        LinkedQueue<SLMS> serv = new LinkedQueue<SLMS>();
        SLMS serv1 = new SLMS(arrivalQueue, serviceStartsQueue, serviceCompletedQueue);
        int numofServers = 1;
        while(numofServers<6){
        	serv1.performService(numofServers);
//        	value = (double)(arrTime - serv1.getsServedTime())/serviceCompletedQueue.size();
        	value = time(serviceCompletedQueue);
//    		averageTime = new BigDecimal(value).setScale(2, RoundingMode.HALF_UP).doubleValue();
        	System.out.print("\nSLMS "+numofServers+": "+serv1.getsServedTime()+" ");
        	System.out.printf("%.2f", value);
        	numofServers+=2;
        }      
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

	public static float time(LinkedQueue<Customer> serviceCompletedQueue ) {
		   //Calculates time in system
  		float totalTime = 0;
  		float arrVal = 0;
  		float serVal = 0;
  		float count = 0;
  		LinkedQueue<Customer> copyServiceCompletedQueue = copyOf(serviceCompletedQueue);
  		while(!(copyServiceCompletedQueue.isEmpty())) {
  			arrVal = copyServiceCompletedQueue.first().getArrival();
  			serVal = copyServiceCompletedQueue.first().getServiceTime();
  			totalTime= (arrVal - serVal ) + totalTime;
  			count++;
  	
  			copyServiceCompletedQueue.dequeue();
  		}
  		totalTime= totalTime/count;
  	
  		return totalTime;
	}
	
}