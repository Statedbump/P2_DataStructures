//Kelvin Garcia Muñiz
//802142644
//CIIC4020 - 030
package generators;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;

//import generators.DataGenerator;

public class FilesGeneratorMain {

	public static void main(String[] args) throws FileNotFoundException {
		if (args.length <= 3) {
			int n = 10; 
			int m = 10; 
			//int size = 50000; 
			if (args.length >= 1) 
				n = Integer.parseInt(args[0]); 
			if (args.length >= 2) 
				m = Integer.parseInt(args[1]); 
//			if (args.length == 3) 
//				size = Integer.parseInt(args[2]); 
			generateFiles(n, m); 
		} 
		else 
			System.out.println("Invalid number of parameters. Must be <= 2.");
	}

	private static void generateFiles(int n, int m) throws FileNotFoundException {
		String parentDirectory = "inputFiles";   // must exist in current directory

		Random rand = new Random(); 
		PrintWriter paramsFile = new PrintWriter(new File(parentDirectory, "parameters.txt")); 
		paramsFile.println(n);   // save parameter n
		paramsFile.println(m);	 // save parameter m
		paramsFile.close();
		PrintWriter out = new PrintWriter(new File(parentDirectory, "dataFiles.txt")); 
		for (int i=0; i<n; i++){
			String fileName = "data_" + i + ".txt"; 

			PrintWriter out2 = new PrintWriter(new File(parentDirectory, fileName)); 
			for (int j=0; j<m;j++){
				out2.print(rand.nextInt(10));
				out2.print(" ");
				out2.println(rand.nextInt(10));
			}
			out.println("data_" + i + ".txt");
			out2.close();

		}
		out.close();
	}
}
