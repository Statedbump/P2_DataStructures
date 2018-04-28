package testerClasses;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Scanner;
import customers.Customer;
import waitingPolicies.SLMS;

public class TesterSLMS {

	public static void main(String[] args) {
		String directory = "inputFiles";
		String directory2 = "outputFiles";
		String dataFile = "dataFiles.txt";
		Scanner scanner;
		ArrayList<Customer> list = new ArrayList<>();
		ArrayList<Customer> list2 = new ArrayList<>();
		NumberFormat formatter = new DecimalFormat("#0.00"); 
		int counter=0, numOfServers = 1;
		long arrivalTime, serviceTime;    
		try {
			scanner = new Scanner(new File(directory, dataFile));
			while(scanner.hasNextLine()){
				PrintWriter out = new PrintWriter(new File(directory2, "data_" + counter + "_OUT.txt"));
				String file = scanner.nextLine();
				System.out.println(file);
				Scanner scanner2;
				try {
					scanner2 = new Scanner(new File(directory, file));
					SLMS[] policy1 = new SLMS[3];
					initializePolicies(policy1);
					while(scanner2.hasNextInt()){
						arrivalTime = scanner2.nextInt();
						serviceTime =scanner2.nextInt();
						Customer customer = new Customer(0, arrivalTime, serviceTime);
						list.add(customer); 
					}
					scanner2.close();
					out.println("-----------------------------------"); 
					for(int i =0; i<3 ; i++) {
						list2 = new ArrayList<Customer>();
						list2 = copyList(list);
						policy1[i].performService(numOfServers, list2);
						out.println("SLMS " + numOfServers+ ": " + policy1[i].getsTotalTime()+ "\t" + 
								formatter.format(policy1[i].getAverageWaiting()));
						numOfServers+=2;
					}
					out.println("-----------------------------------");
				} catch (FileNotFoundException e) {
					out.println("Input file not found.");
				}
				out.close();
				counter++;
			}
			scanner.close();
		} catch (FileNotFoundException e1) {
			System.out.println("The directory path specified was not found");
		}
	}
	public static ArrayList<Customer> copyList(ArrayList<Customer> list){
		ArrayList<Customer> list2 = new ArrayList<>();
		for(Customer E: list){
			list2.add(E);
		}
		return list2;
	}

	public static void initializePolicies(SLMS[]policy1) {
		for(int i = 0; i< 3;i++) {
			policy1[i]= new SLMS();
		}
	}

}