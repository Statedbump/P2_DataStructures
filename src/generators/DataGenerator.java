package generators;


import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Luis M.Cintron Zayas
 * Est# 841-14-1275
 * CIIC 4020- sec 030
 */
/**
 * 
 * @author pedroirivera-vega
 *
 */
public class DataGenerator {
    // R1 and R2 are fixed parameters to support data generation. We can play with
	// different values to adjust that generated data satisfies some criteria being
	// pursued. But for this project, I would say that the values given are good 
	// enough. 
	private static final int R1 = 20, R2 = 0;  
	                   
	private  int maxRangeValue;  // the largest integer value to be generated
	private int n;    // number of data generators (telephone companies in p1_4035_4020_172
	private int totalSize;   // sum of the sizes of all datasets of particular data generators
	private Integer[] sizes;    // sizes of datasets provided (or generated) by C for E
	private Integer[] dataSet; // dataSet[i] will be the generated data for input_i
	private int sizeFactor; 
	
	private Random rnd; 
	
	/**
	 * 
	 * @param n
	 * @param m
	 * @param totalSize
	 */
	public DataGenerator(int n,  int totalSize) {
		this.n = n; 
	
		// In computing MaxRangeValue, I just picked the value 10 arbitrarily in 
		// the next expression. Think about better possibilities, perhaps involving
		// n and m too... 
		// maxRangeValue is essentially the number of different values (+1) that 
		// final elements are chosen from...
		this.sizeFactor = totalSize/(n);   // to be used while generating sizes
		this.totalSize = totalSize;   // Sizes must satisfy sum_ij(sizes[i][j]) == totalSize
		rnd = new Random();  // to generate random values
	}
	
	/**
	 * 
	 * @return
	 */
	public Object[] generateData() {
		dataSet = new Integer[n];
		generateSizes(); 
		for (int i=0; i<n; i++) { 
			
				//HashSet<Integer> set = new HashSet<>(); 
				Set<Integer> set = new HashSet<>(); 
				while(set.size() != this.sizes[i]) {
					set.add(this.rnd.nextInt(maxRangeValue));
				}
				// add a common value to sets in row 0
				if (i==0) 
					set.add(maxRangeValue); 
				for(Integer E : set)
				dataSet[i] = E; 
			}
			
		

		return dataSet; 
	}

	/**
	 * 
	 */
	private void generateSizes() {
		sizes = new Integer[n]; 
		int s = 0; 
		for (int i=0; i<n; i++) 
			{ 
			sizes[i] = (rnd.nextInt(R1)-R2)+sizeFactor; 
			if (sizes[i] < 0)
				sizes[i] = 0; 
			s += sizes[i]; 
		}

		// adjust sizes for the total to be totalSize-m
		matchSizes(s, totalSize); 
		
		// determine the max size in s
		int maxSize = maxSize(sizes); 
		maxRangeValue = (int) (maxSize); 

	}
	
	private int maxSize(Integer[] sizes) {
		int mv = 0; 
		for (int i=0; i<sizes.length; i++) 
				if (sizes[i]> mv)
					mv = sizes[i]; 
		return mv;
	}

	/**
	 * 
	 * @param s
	 * @param total
	 */
	private void matchSizes(int s, int total) { 
		int step = 1; 
		if (s>total) step = -1; 
		int i = 0 ; 
		while (s != total) { 
			if (sizes[i] != 0) {
			   sizes[i] += step;     // check that some values (e.g. negative) are not valid for sizes
			   s += step; 
			}    
     			i = (i+1) % n;
			
		}
	}

	
	// THE FOLLOWING METHODS ARE HERE TO USE IN TESTING....
	public void printSizes() { 
		System.out.println("Sizes:");
		for (int i=0; i<n; i++)
			printArray(sizes);
	}
	
	public void printSets() { 
		System.out.println("Sets are: " ); 
		for (int i=0; i<n; i++) {
			 
				System.out.print("Input File_i"); 
				printArray((Integer[]) dataSet); 
			}
	}
	
	private void printArray(Integer[] numbers) {
		for (int i=0; i<numbers.length; i++) 
			System.out.print(numbers[i] + "  "); 
		System.out.println(); 
	}


}
