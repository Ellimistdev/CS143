import java.util.*;
import java.io.*;

public class QuestionTree {

   public static Scanner console = new Scanner(System.in);

   /*
      METHOD: yesTo
      DESCRIPTION: Displays prompt to user and forces them
      to give valid Y/N response.
      @param prompt The prompt to be displayed before (y/n)? 
      @return true if user answered y in response to the prompt;
         false otherwise
      DO NOT MODIFY THIS CODE IN ANY WAY.
   */
   public boolean yesTo(String prompt) {
      System.out.print(prompt + " (y/n)? ");
      String response = console.nextLine().trim().toLowerCase();
      while (!response.equals("y")&& !response.equals("n")) {
         System.out.println("Please answer y or n.");
         System.out.print(prompt + " (y/n)? ");
         response = console.nextLine().trim().toLowerCase();
      }
      return response.equals("y");
   }//end yesTo() method  
   
}//end QuestionTree class