/** 
   Program: GrammarProcessorClient
   Programmer: Hilda Halliday
   Purpose: GrammarProcessorClient contains code to test the GrammarProcessor class.
   It asks the user for the name of a BNF grammar file and then gives the user 
   the opportunity to generate random versions of various elements of the grammar. 
*/ 

import java.io.*; 
import java.util.*; 
 
public class GrammarProcessorClient { 
   public static void main(String[] args) throws IOException { 
      Scanner console = new Scanner(System.in); 

      // Fill list with BNF rules in file, then create a grammarProcessor
      List<String> grammar = grammarFromFile(console);
      GrammarProcessor processor = new GrammarProcessor(grammar); 
        
      // Generate expressions based on grammar elements chosen by user
      System.out.println("\n" + processor.getSymbols()); 
      System.out.print("Which symbol do you want? (press <enter> to quit) ");
      String symbol = console.nextLine();
      while ( symbol.length() != 0 ) {
         if ( !processor.grammarContains(symbol) ) 
            System.out.println(symbol + " not in grammar!");
         else {
            try {
               System.out.print("How many expressions? ");
               int howMany = Integer.parseInt(console.nextLine());
               String[] expressions = processor.generate(symbol,howMany);
               for( String s : expressions )
                  System.out.println(s);
            }catch(Exception e ){
               System.out.println("Invalid value for number of expressions.");
            }
         }
         System.out.println("\n" + processor.getSymbols()); //test getSymbols()
         System.out.print("Which symbol do you want? (press <enter> to quit) ");
         symbol = console.nextLine();
      }

    }//end main() method 
    
    /**
      The grammarFromFile() method asks the user for the name of a text file
      containing a BNF description of a grammar. It creates, fills and returns
      a list containing each line from the file.
      @param console has been linked to System.in
      @return list of string values from grammar file
    */
    public static List<String> grammarFromFile(Scanner console) throws IOException {
      // open grammar file 
      System.out.print("What is the name of the grammar file? "); 
      String fileName = console.nextLine(); 
      Scanner input = new Scanner(new File(fileName)); 

      // read the grammar file and fill the list with each line from the file
      List<String> grammar = new ArrayList<String>(); 
      while (input.hasNextLine()) 
         grammar.add(input.nextLine()); 
      return grammar;    
    }

} //end GrammarProcessorClient  
 