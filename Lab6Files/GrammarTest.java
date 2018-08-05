/**
   Program: GrammarTest
   Programmer: Hilda Halliday
   Purpose: This program can be used to test the GrammarProcessor
   class as it is being implemented. It is much more simplistic and less
   versatile than the GrammarProcessorClient class. You may change the
   name of the file in line 21 to used other grammars.
   
   SUGGESTION: Comment out lines of code in this tester that 
   refer to GrammarProcessor methods that are not yet implemented.
*/

import java.io.*;
import java.util.*;

public class GrammarTest {
    public static void main(String[] args) throws IOException {
        Scanner console = new Scanner(System.in);

        // open grammar file
        Scanner input = new Scanner(new File("sentence1.txt"));

        // read the grammar file and construct the grammarProcessor
        List<String> grammar = new ArrayList<String>();
        while (input.hasNextLine()) {
            String nextRule = input.nextLine().trim();
            if (nextRule.length() > 0)
                grammar.add(nextRule);
        }
        GrammarProcessor processor = new GrammarProcessor(grammar);
        
        System.out.println(processor.getSymbols()); //test getSymbols()
        System.out.print("Which symbol do you want? ");
        String symbol = console.nextLine();
        if ( processor.grammarContains(symbol) ) //test grammarContains()
            System.out.println(processor.generate(symbol)); //test 
        else
            System.out.println(symbol + " not in grammar!");
    }
}