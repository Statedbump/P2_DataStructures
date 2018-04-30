# Project-2-Data-Structures
## Kelvin Garcia Muñiz | Luis M. Cintrón Zayas
## 802142644 | 841141275
## CIIC4020 - 030

## To Run the Program:
Before executing any of these commands from the CMD or Terminal make sure you are in the correct directory path. If using Eclipse or a 
similar IDE, these procedures may not be required.
### To Run Main Program:
From terminal: java -classpath ./bin main/Main
    
       This command will execute the Main.java class and output results according to the data gotten from the input files. If 
       the execution is successful, the names of the files generated will be displayed on the console.
       
       The execution of the program will be successful if the following conditions are satisfied:
       
          1. There is a directory under the name specified by the program ("inputFiles" by default).
         
               - In addition, there must be a dataFiles.txt file containing the names of the files to be tested.
          
          2. There is a directory under the name specified by the program ("outputFiles" by default) containing the
             output files of the program.
             
          3. The command is executed while in the correct directory path (.../p2_40354020_172).
          
       * For detailed information head to the in-code implementation *
From IDE: Run Main.java from the main package
### To Run Files Generator:
From terminal: java -classpath ./bin generators/FilesGeneratorMain

       This command will execute the FilesGeneratorMain.java class and output files according to specified parameters. 
       If the execution is sucessful, a message should be displayed on the console, describing the amount of files and
       and the amount of customers (integer values) in each file
       
        1. There is a directory under the name specified by the program ("inputFiles" by default).
          
        2. There is a directory under the name specified by the program ("outputFiles" by default).
             
        3. The command is executed while in the correct directory path (.../p2_40354020_172).
       
     * For detailed information head to the in-code implementation *
From IDE: Run filesGeneratorMain.java from the generators package
### To View Files:
From terminal: 
    
       Once located in the right directory path of the project (.../p2_40354020_172), head to the desired directory using 
       the following commands: 
            
            To view the inputFiles:
            
                cd inputFiles               (by default)
                
                cat data_i.txt              (where i is the number of the file to view, by default)
                
                ls                          (lists all the files in the directory)
                
            To view the outputFiles:
            
                cd outputFiles               (by default)
                
                cat data_i_OUT.txt           (where i is the number of the file to view, by default)
                
                ls                           (lists all the files in the directory)
                
      * For detailed information head to the in-code implementation *
From IDE: Open the desired directory, followed by the desired file.
## What the program does:
This software allows the user to simulate various scenarios in which customers wait to be served under different waiting policies.
The following, is a list of the simulated schemes:
### 1) Single Line Multiple Servers (SLMS):
This policy formats the customers into one waiting line and at least one service post (denoted as servers in-code). When a service
is finished (if any) then, the first customer in the waiting line will proceed to be served. Should more than one server be available,
the customer will proceed to the first service post available (the server with lowest index value)
### 2) Multiple Lines Multiple Servers (MLMS):
Customers formatted under this policy are placed into various waiting lines (one line per server). No transfers between lines occur 
under this policy. If a customer arrives, it will be allocated in the first line with the fewest number of people waiting.
### 3) Multiple Lines Multiple Servers and Balanced Line Lengths (MLMSBLL):
This policy formats the customers into multiple waiting lines and at least one service post. However, under this scheme, customers are allowed to transfer between lines but only, if and only if, that transfer is beneficial for the customer. For example, suppose there are 2 lines. On line 1, the last customer is customer #5. If, for an instance, the length on line 2 is 4, then the customer would not benefit from this change, since moving it to the second line would place it in the same position (4+1=5). However, if the line 2 has only 3 customer, then the last customer at line 1 would benefit from the transfer since it would place it in position 4. In addition, should more than one server be available, the customer will be transfered to the line closes to the its right.
### 4) Multiple Lines Multiple Servers and Balanced Line Lengths (MLMSBWT):
Similar to the waiting policy MLMS, the customers are placed in various waiting lines where no transfers can occur. However, under this policy, customers are allocated, after their arrival, on the line with the lowest waiting time, that is, the fastest moving line. Should more than one line be available, the transfer will occur to the line with lower index
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
