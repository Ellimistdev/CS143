/*
Programmer: Nick Rodriguez
Description: Creates an instance of QuestionTree and plays a game of 20 Q's
Time spent on assignment 5: 5 hours, 25 minutes
*/
import java.util.*;
import java.io.*;

public class QuestionTreeClient {
   public static void main(String[] args) throws IOException{
      QuestionTree tree = new QuestionTree();

      if (tree.yesTo("Do you want to read in the previous tree?")) {
         tree.read(new Scanner(new File("questions.txt")));
      }

      do {
         System.out.println("Please think of an object for me to guess.");
         tree.askQuestions();
      }while (tree.yesTo("\nDo you want to go again?"));

      if (tree.yesTo("Do you want to save this tree?")) {
         tree.write(new PrintStream(new File("questions.txt")));
         System.out.println("Tree has been saved.");
      }
   }
}
