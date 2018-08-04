/*
Programmer: Nick Rodriguez
Description: Creates an instance of QuestionTree and plays a game of 20 Q's
Time spent on assignment 5: 2 hours, 23 minutes
*/
import java.util.*;
import java.io.*;

public class QuestionTreeClient {

      /*
         NOTE: The only input main() should perform is by calling the yesTo()
         method of the QuestionTree class appropriately. DO NOT DECLARE A 
         Scanner OBJECT LINKED TO System.in WITHIN THIS CLASS.

         In the post-checkpoint version of your program, have main() call the
         QuestionTree(), yesTo(), read(), write() and askQuestions()
         methods of the QuestionTree class appropriately to perform the following tasks:

         1. Create a QuestionTree object.

         2. If the user wants to override the tree with data from questions.txt,
            read the data from the file into the tree.

         3. Keep playing the guessing game until the user wants to quit.

         4. Save the current tree in questions.txt if the user desires.
      */

   public static void main(String[] args) {
      QuestionTree tree = new QuestionTree();
      tree.write(System.out);
   }//end main() method
}//end QuestionTreeClient class
