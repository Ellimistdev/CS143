/**
   Program: GrammarChkptTest
   Programmer: Hilda Halliday
   Purpose: This program tests the GrammarProcessor constructor.
*/

import java.io.*;
import java.util.*;

public class GrammarChkptTest {
    public static void main(String[] args) throws IOException {
        Scanner console = new Scanner(System.in);

        // open grammar file
        Scanner input = new Scanner(new File("sentence1.txt"));

        // read the grammar file contents into the grammar List
        List<String> grammar = new ArrayList<String>();
        while (input.hasNextLine()) {
            String nextRule = input.nextLine().trim();
            if (nextRule.length() > 0)//don't add empty strings to grammar
                grammar.add(nextRule);
        }
        
        // Construct a GrammarProcessor object from the grammar List
        GrammarProcessor processor = new GrammarProcessor(grammar);
    }
}