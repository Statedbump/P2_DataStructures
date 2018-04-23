//Kelvin Garcia Muñiz
//802142644
//CIIC4020 - 030
package generators;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import generators.DataGenerator;

public class FilesGeneratorMain {

	public static void main(String[] args) throws FileNotFoundException {
		if (args.length <= 2) {
			int n = 20; 
			int size = 50000; 
			if (args.length >= 1) 
				n = Integer.parseInt(args[0]); 
			if (args.length == 2) 
				size = Integer.parseInt(args[1]); 
			generateFiles(n, size); 
		} 
		else 
			System.out.println("Invalid number of parameters. Must be <= 2.");
	}

	private static void generateFiles(int n, int size) throws FileNotFoundException {
		String parentDirectory = "inputFiles";   // must exist in current directory
		DataGenerator dg = new DataGenerator(n, size);
		Object[] setsLists = dg.generateData();  

		PrintWriter paramsFile = new PrintWriter(new File(parentDirectory, "parameters.txt")); 
		paramsFile.println(n);   // save parameter n
		paramsFile.close();

		for (int i=0; i<n; i++){
			String fileName = "data_" + i + ".txt"; 
			PrintWriter out = new PrintWriter(new File(parentDirectory, fileName)); 
			out.println(setsLists[i]);
			out.close();
		}
	}
}
