/*
Programmer: Nick Rodriguez
Description: Describes a binary Q/A tree
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
            throw new IllegalArgumentException("No data received, cannot create an empty node");
         }
         this.data = data;
      }

      private boolean isLeaf() {
         return (this.left == null && this.right == null);
      }
   }

   /* Constructors */
   public QuestionTree() {
      root = new QuestionNode("computer");
   }

   /* Public methods */
   public void askQuestions() {
      root = askQuestions(root);
   }

   public void read(Scanner input) {
      read(input, root);
   }

   public void write(PrintStream output) {
      if (root != null) {
         printPreOrder(root, output);
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

   /* Helpers */  
   private QuestionNode askQuestions(QuestionNode node) {
      if (node.isLeaf()){
         if (yesTo(String.format("Would your object happen to be %s?", node.data))) {
            System.out.println("Great, I got it right!");
         } else {
            return createNewNode(node);
         }
      } else {
         if (yesTo(node.data)) {
            node.left = askQuestions(node.left);
         } else {
            node.right = askQuestions(node.right);
         }
      }
      return node;
   }

   private QuestionNode createNewNode(QuestionNode node) {
      System.out.printf("What is the name of your object? ");
      QuestionNode object = new QuestionNode(console.nextLine().trim().toLowerCase());
      System.out.println("Give me a yes/no question to distinguish");
      System.out.printf("\tobject %s from object %s\n", object.data, node.data);
      QuestionNode question = new QuestionNode(console.nextLine().trim());
      
      if (yesTo(String.format("What is the answer for object %s", object.data))){
         question.right = node;
         question.left = object;
      } else {
         question.right = object;
         question.left = node;
      }
      return question;
   }
 
   // Given input, adds nodes to this.root
   private void read(Scanner input, QuestionNode node) {
      if (input.hasNextLine()) {
         if (input.nextLine().equals("Q:")) { // consumes line
            node.data = input.nextLine();
            getChildNodes(input, node);
         }
         if (node.data.equals("temp")) {
            node.data = input.nextLine();
         }
      }
   }

   private void getChildNodes(Scanner input, QuestionNode node) {
      addQuestionNode(input, node);
      getRightNode(input, node);
   }

   private void addQuestionNode(Scanner input, QuestionNode node) {
      // since read() edits the passed node, create a dummy node to use
      node.left = new QuestionNode("temp");
      read(input, node.left);
   }

   private void getRightNode(Scanner input, QuestionNode node) {
      boolean answer = input.nextLine().equals("A:"); // consumes line
      node.right = new QuestionNode(input.nextLine());
      if (!answer && input.hasNextLine()){
         // we're operating one node removed to avoid replacing good data
         getChildNodes(input, node.right);
      }
   }

   // Performs a pre-order traversal of the target node,
   // writing data to the printstream
   private void printPreOrder(QuestionNode node, PrintStream output) {
      if (node != null) {
         if (node.isLeaf()) {
            output.printf("A:\n");
         } else {
            output.printf("Q:\n");
         }
         output.printf("%s\n", node.data);
         printPreOrder(node.left, output);
         printPreOrder(node.right, output);
      }
   }
}
