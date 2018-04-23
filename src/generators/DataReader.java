//Kelvin Garcia Muñiz
//802142644
//CIIC4020 - 030
package generators;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 * @author pedroirivera-vega
 *
 */
public class DataReader {

	private int n;    // number of data generators
	private Integer[] dataSet; 
	private String parentDirectory; 


	public DataReader() throws FileNotFoundException {
		parentDirectory = "inputFiles"; 
		Scanner parameters = new Scanner(new File(parentDirectory, "parameters.txt")); 
		this.n = parameters.nextInt(); 
		parameters.close();
	}

	public Object[] readDataFiles() throws FileNotFoundException {
		dataSet = new Integer[n];

		for (int i=0; i<n; i++) { 
			String fileName = "data_" + i + ".txt"; 
			Scanner inputFile = new Scanner(new File(parentDirectory, fileName)); 
			ArrayList<Integer> fileContent = new ArrayList<>(); 
			while (inputFile.hasNext())
				fileContent.add(inputFile.nextInt());
			inputFile.close();
			dataSet[i] = (Integer) fileContent.toArray(new Integer[0]);  
		}	
		return dataSet; 
	}


	public void printSets() { 
		System.out.println("Sets Fij are: " ); 
		for (int i=0; i<n; i++){
			System.out.print("Set["+i+"]["+j+"] = "); 
			printArray((Integer[]) dataSet[i]); 
		}
	}

	private void printArray(Integer[] numbers) {
		for (int i=0; i<numbers.length; i++) 
			System.out.print(numbers[i] + "  "); 
		System.out.println(); 
	}


}
