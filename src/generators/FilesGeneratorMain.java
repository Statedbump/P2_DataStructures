//Kelvin Garcia Muñiz || Luis Cintrón Zayas
//802142644 || 841141275
//CIIC4020 - 030
package generators;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;

/**
 * Generates input and output files
 * @author Kelvin Garcia & Luis Cintrón
 *
 */
public class FilesGeneratorMain {
	public static void main(String[] args) throws FileNotFoundException {
		if (args.length <= 3) {
			int n = 10; //files parameter
			int m = 7;  // customers per file parameter
			if (args.length >= 1) 
				n = Integer.parseInt(args[0]); 
			if (args.length >= 2) 
				m = Integer.parseInt(args[1]);  
			generateFiles(n, m); // generate files with the specified parameters
			System.out.println("----------------------------------------------------------------");
			System.out.println("Files have been generated: "+n+" files with "+m+" customers each");
			System.out.println("----------------------------------------------------------------");
		} 
		else 
			System.out.println("Invalid number of parameters. Must be <= 2.");
	}

	/**
	 * generates files according to given parameters
	 * @param n - number of files
	 * @param m - customers per file
	 * @throws FileNotFoundException
	 */
	private static void generateFiles(int n, int m) throws FileNotFoundException {
		String alphabet = "ABCDEFGHIJKLMNOPQRST"; // used to generate invalid files
	    int N = alphabet.length(); // used to generate invalid files
		String parentDirectory = "inputFiles";   // must exist in current directory
		String parentDirectory2 = "outputFiles"; // must exist in current directory
		Random rand = new Random(); // random variable to created random numbers
		PrintWriter paramsFile = new PrintWriter(new File(parentDirectory, "parameters.txt")); 
		paramsFile.println(n);   // save parameter n
		paramsFile.println(m);	 // save parameter m
		paramsFile.close(); 	// close printer
		PrintWriter out = new PrintWriter(new File(parentDirectory, "dataFiles.txt")); // file containing names of all files
		for (int i=0; i<n; i++){ 
			String fileName = "data_" + i + ".txt"; //input file
			String outFileName = "data_" + i + "_OUT.txt"; //output file
			PrintWriter out2 = new PrintWriter(new File(parentDirectory2, outFileName)); // printer for outputFiles
			int fileDelim = rand.nextInt(3); // file delimeter - gives 3 values (0, 1, 2)
			if(fileDelim==0){ // if the delimeter = 0, it creates a valid file
				PrintWriter out3 = new PrintWriter(new File(parentDirectory, fileName)); // printer for inputFiles
				for (int j=0; j<m;j++){
					out3.print(rand.nextInt(10)); // print a number from 0 to 10
					out3.print(" "); 
					out3.println(1+rand.nextInt(9)); // print a number from 1 to 10 (service time of a customer CANNOT be 0)
				}
				out3.close(); // close out3
			}
			else if(fileDelim==1){ // if the delimeter = 1, it creates an invalid file;
				PrintWriter out3 = new PrintWriter(new File(parentDirectory, fileName)); // printer for inputFiles
				for (int j=0; j<m;j++){
				        out3.print(alphabet.charAt(rand.nextInt(N))); // generate a random string
				    }
				out3.close(); // close out 3
				}
			//regardless of the number of the delimeter it will print its name in the major input file
			//however, if the delimeter = 2, it will print its name without creating a file
			//hence, it creates a non-existing file
			out.println("data_" + i + ".txt"); // print the name of the file in dataFile.txt
			out2.close(); //close out 2
		}
		out.close(); // close out
	}
}