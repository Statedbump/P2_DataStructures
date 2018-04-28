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
	static String alphabet = "ABCDEFGHIJKLMNOPQRST";
    static int N = alphabet.length();
	public static void main(String[] args) throws FileNotFoundException {
		if (args.length <= 3) {
			int n = 10; 
			int m = 7; 
			//int size = 50000; 
			if (args.length >= 1) 
				n = Integer.parseInt(args[0]); 
			if (args.length >= 2) 
				m = Integer.parseInt(args[1]);  
			generateFiles(n, m); 
		} 
		else 
			System.out.println("Invalid number of parameters. Must be <= 2.");
	}

	private static void generateFiles(int n, int m) throws FileNotFoundException {
		String parentDirectory = "inputFiles";   // must exist in current directory
		String parentDirectory2 = "outputFiles";

		Random rand = new Random(); 
		PrintWriter paramsFile = new PrintWriter(new File(parentDirectory, "parameters.txt")); 
		paramsFile.println(n);   // save parameter n
		paramsFile.println(m);	 // save parameter m
		paramsFile.close();
		PrintWriter out = new PrintWriter(new File(parentDirectory, "dataFiles.txt")); 
		
		for (int i=0; i<n; i++){
			String fileName = "data_" + i + ".txt";
			String outFileName = "data_" + i + "_OUT.txt";
			PrintWriter out2 = new PrintWriter(new File(parentDirectory2, outFileName));
			int fileDelim = rand.nextInt(3);
			if(fileDelim==0){
				PrintWriter out3 = new PrintWriter(new File(parentDirectory, fileName));
				for (int j=0; j<m;j++){
					out3.print(rand.nextInt(10));
					out3.print(" ");
					out3.println(1+rand.nextInt(9));
				}
				out3.close();
			}
			else if(fileDelim==1){
				PrintWriter out3 = new PrintWriter(new File(parentDirectory, fileName));
				Random r = new Random();
				for (int j=0; j<m;j++){
				        out3.print(alphabet.charAt(r.nextInt(N)));
				    }
				out3.close();
				}
			out.println("data_" + i + ".txt");
			out2.close();
		}
		out.close();
	}
}
