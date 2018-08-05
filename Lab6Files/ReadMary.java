/**
   Program: ReadMary
   Programmer: Hilda Halliday
   Background: This program is based on the introductory description
   preceding the program in Version 3: Complete Program section of BJP 10.3. 
   Purpose: Use a Scanner to read the individual words of the poem 
   "Mary Had a Little Lamb" contained in the Mary'sLamb.txt file. 
   The definition of what is a "word" will be determined by the
   useDelimiter() method of the Scanner class. Look for these 
   elements in the program.
   1. Tell the scanner what separates one word from another
      (what are the "delimiters") in the file being processed.
   2. For each token in the file stream, input the token
      and add it to a SET so that duplicates are eliminated. 
   3. Display the contents of the set on the console screen.
   
   By default, a Scanner reads from whitespace to whitespace. That is,
   whitespace is the "delimiter" that separates one token in an input
   stream from the next token in the stream. The Scanner ignores
   the whitespace delimiters in the input stream.

   We can use the useDelimiter() method of the Scanner class, 
   together with "regular expressions" to make the Scanner ignore 
   punctuation in the input stream, rather than ignoring only whitespace.
   
   Here's a regular expression to describe the contents of a word:
   "lowercase letters, uppercase letters and the apostrophe"
   [a-zA-z']
   
   
   But we want to tell the Scanner to ignore everything that is NOT
   a word. The ^ character in the regular expression has that effect:
   [^a-zA-z']
   
   But there may be multiple delimiters in a row, so we add the +
   symbol to the regular expression to indicate this. The resulting 
   regular expression is:
   [^a-zA-z']+
   
   If this regular expression is used as the argument to the
   setDelimiter() method of the Scanner class, the Scanner will
   ignore everything that isn't a letter or apostrophe.
   
*/

import java.util.*;
import java.io.*;

public class ReadMary {

   public static void main(String[] args) throws IOException {
   
      Scanner poemFile = new Scanner(new File("Mary'sLamb.txt"));
      Set<String> wordSet = new TreeSet<String>();

      //Fill wordSet with tokens from Scanner using default delimiters.
      System.out.println("Tokens with normal Scanner delimiters:");
      fillAndDisplaySet(poemFile,wordSet);
         
      //Reset the Scanner and Set
      wordSet.clear(); //Start with an empty set of words
      poemFile = new Scanner(new File("Mary'sLamb.txt"));
      
      //Fill wordSet with tokens from Scanner ignoring punctuation.
      //Everything that isn't a letter or apostrophe is a delimiter to be ignored.
      poemFile.useDelimiter("[^a-zA-Z']+"); 
      System.out.println("\nTokens with Scanner ignoring punctuation:");
      fillAndDisplaySet(poemFile,wordSet);
               
   }//end main() method
   
   //Read tokens from input stream and place them in the set.
   //Then display contents of the set, one element per line.
   public static void fillAndDisplaySet(Scanner input, Set<String> set) {
      while( input.hasNext() ) {
         String token = input.next();
         //Make token lowercase and trim any leading and trailing spaces
         //Then add the result to the Set 
         set.add(token.toLowerCase().trim());
      }
      
      //Display the elements of the set, one per line
      for ( String s : set )
         System.out.println(s);
      
   }//end fillAndDisplaySet() method  
   
}//end ReadMary class