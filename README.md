# Project-2-Data-Structures
## Kelvin Garcia Muñiz | Luis M. Cintrón Zayas
## 802142644 | 841141275
## CIIC4020 - 030

## To Run the Program:
Before executing any of these commands from the CMD or Terminal make sure you are in the correct directory path. If using Eclipse or a 
similar IDE, these procedures may not be required.
### To Run Main Program:
From terminal: java -classpath ./bin main/Main
    
       This command will execute the Main.java class and output files according to the data gotten from the input files.
       
       The execution of the program will be successful if the following conditions are satisfied:
       
          1. There is a directory under the name specified by the program ("inputFiles" by default)
         
               - In addition, there must be a dataFiles.txt file containing the names of the files to be tested
          
          2. There is a directory under the name specified by the program ("outputFiles" by default) containing the
          output files of the program

From IDE: Run Main.java from the main package
### To Run Files Generator:
From terminal: java -classpath ./bin  p1MainClasses/Part2Main  n  m  isize  fsize  istep  rep

    Where:
    
       n = number of companies
       
       m = number of crimes
       
       isize = initial size for experimentations
       
       fsize = final size for experimentations
       
       isetp = incremental size step (how much the size increases by trial)
       
       rep = number of repetitions per size
       
     * For detailed information head to the in-code implementation *
From IDE: Run Part2Main.java, with the desired parameters, from the p1MainClasses package
## Part 1:
This part includes a working version of the four strategies to be implemented (P1, P2, P3, P4). 
Output produces the results for a particular input in which the intersection of all the sets is the set containing the corresponding
elements. (See results sample in "Part1 Sample Results Sheet.pdf")
## Part 2:
This part produces the empirical results for the execution times of each one of the four strategies in "part2Results/allResults.txt". (See running results sample graphs in "Part 2 Sample Results Sheets.pdf").
## Understanding the code:
In order to understand the hierarchy of the classes, head to the "UML Kelvin Garcia CIIC4020-030.ucls" file or to the "README.pdf" file.
  ### Package dataGenerator
   #### Includes:
       DataGenerator.java (public): generates the data given parameter n, m, and totalSize
       DataReader.java (public): reads the data from the files generated
  ### Package interfaces
   #### Includes:
       IntersectionFinder.java (interface) - an objects of type finds the intersection of a family of sets.
       MySet.java (interface) - used by the intersectSets methods that the experiments will be implementing.
  ### Package mySetImplementations
   #### Includes: 
       AbstractMySet.java (public abstract)- an abstract class of MySet.
       Set1.java (public) - used by strategy P1 and implements ArrayLists
       Set2.java (public) - used by the remaining strategies and implements HashMaps
  ### Package p1MainClasses
   #### Includes: 
       FilesGeneratorMain.java (public) - generates the files to be used in Part 1, with the given number of crimes and companies
       Part1Main.java (public) - main class to run Part1 of the project. Outputs the intersections given by each of the strategies
       Part2Main.java (public) - main class to run Part2 of the project. Outputs the execution times of the strategies given certain parameters. (See "To Run the program" or head to the in-code implementation of the class for more information)
  ### Package setIntersectionFinders
   #### Includes:
       AbstractIntersectionFinder.java (public abstract) - an abstract class of IntersectionFinder
  ### Package solutionsAndAidClasses
   #### Includes:
       P1P2.java (public) - contains the instersectSets method with the implementation used for P1 and P2
       P3.java (public) - contains the instersectSets method with the implementation used for P3
       P4.java (public) - contains the instersectSets method with the implementation used for P4
       Part2Methodology.java (public) - includes constructor needed to run Part 2 in Part2Main.java with specfied parameters. 
                     Generates data and saves results
       StrategiesTimeCollection.java (public)- includes auxiliary methods to aid in the management of time data
       UnionFinder.java (public) - countains methods unionWriter and arrayToList. The first is implemented by all strategies and returns 
                     the corresponding set representing the union of sets given certain parameters. The latter is implemented by P3 and 
                     P4 and converts a given array into an ArrayList
  ### Package testerClasses
   #### Includes: 
       DataGeneratorTester.java (public) - a tester for the DataGenerator.java class
  ### Directory doc
   #### Includes:
          All proper java documentation for created methods and classes 
  ### Directory inputFiles
   #### Includes: 
       Files generated by the FileGenerator.java class
       parameters.txt - parameters used in Part 1
  ### Directory experimentalResults
   #### Includes: 
       allResults.txt - file contianing results of Part 2
  ### UMLKelvinGarciaCIIC4020-030.ucls 
   #### UML diagram with class hierarchy 
## Understanding the different strategies:
### P1
    Finds the intersection by iterating over the sets and removing those elements that do not belong to that crime. Implements Set1
### P2
    Finds the intersection by iterating over the sets and removing those elements that do not belong to that crime. Implements Set2
### P3
    Finds the intersection by counting the number of occurrences of each element in the multiset T0+T1+... + Tm-1. Implements Set2 but
    unlike P4, P3 iterates over a list to count these frequencies.
### P4
     Finds the intersection by counting the number of occurrences of each element in the multiset T0+T1+... + Tm-1. Implements Set2 but
     unlike P3, P4 uses HashMaps to count these frequencies.
  #### To further understand how these strategies are implemented head to the in-code descriptions
