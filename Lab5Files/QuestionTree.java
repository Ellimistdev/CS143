/*
Programmer: Nick Rodriguez
Description: Describes a binary Q/A tree
Time spent on assignment 5: 2 hours, 23 minutes
*/
import java.util.*;
import java.io.*;

public class QuestionTree {

   private static Scanner console = new Scanner(System.in);
   private QuestionNode root = null;

   private static class QuestionNode { 
      private QuestionNode left = null;
      private QuestionNode right = null;
      private String data = null;

      public QuestionNode(String data) {
         if (data == null || data.isEmpty()) {
            throw new IllegalArgumentException("No data received");
         }
         this.data = data;
      }
   }

   public QuestionTree() {
      root = new QuestionNode("Is it an animal?");
      root.right = new QuestionNode("computer");
      root.left = new QuestionNode("Does it hop?");
      root.left.left = new QuestionNode("frog");
      root.left.right = new QuestionNode("dog");
   }

   public void write(PrintStream output) {
      if (root != null) {
         printPreOrder(root, output);
      }
   }

   // Performs a pre-order traversal of the target node, printing the data to the printstream
   private void printPreOrder(QuestionNode node, PrintStream output) {
      if (node != null) {
         if (node.left == null && node.right == null) {
            output.printf("A:\n");
         } else {
            output.printf("Q:\n");
         }
         output.printf("%s\n", node.data);
         printPreOrder(node.left, output);
         printPreOrder(node.right, output);
      }
   }

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
