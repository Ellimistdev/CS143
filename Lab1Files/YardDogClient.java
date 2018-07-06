/**
   PROGRAM: This is a client program to test the 
   YardDog class described in the Lab 1 Specifications. 

   PROGRAMMER: ???
   
   ATTENTION PROGRAMMERS!!!
   
   1) Make sure your YardDog.java file is in the same folder
      as this YardDogClient.java file.
 
   2) Be sure you have uncommented all the methods that
      test the stages of Lab 1 that you implemented.
   
   3) Be sure you have commented out the lines of the methods
      that test items from Lab 1 that you did not implement.

*/
import java.util.*;
import java.io.*;

public class YardDogClient {

   private static Scanner kbd = new Scanner(System.in);

 	public static void main(String[] args) throws IOException {
      String choice;
      do {
         System.out.println("Stages of YardDog testing");
         System.out.println("A. Test Stage 1 and 3 code");
         System.out.println("B. Test Stage 2-default constructor; toString()");
         System.out.println("C. Test Stage 4-boneFound())");
         System.out.println("D. Test Stage 5-digHoles()");
         System.out.println("E. Quit testing");
         System.out.print("Your menu choice(A-E)? ");
         choice = kbd.next();
         System.out.println();
         if(choice.equalsIgnoreCase("A"))
            stage1N3_Test();
         else if(choice.equalsIgnoreCase("B"))
            stage2_Test();
         else if(choice.equalsIgnoreCase("C"))
            stage4_Test();
         else if(choice.equalsIgnoreCase("D"))
            stage5_Test();
      }while(!choice.equalsIgnoreCase("E"));
      System.out.println("Good-bye!");
   
 	}//end main method
   
   /******************************************************************
      Test class constants, 3-parameter constructor, buildYard() and 
      Stage-1 Accessor and Mutator methods
      
      This code contains a try-catch statement.
      Try-catch statements are the oppposite of throw-statements.
      That is, try-catch statements catch the exceptions that
      throw-statements generate.
   *******************************************************************/
   public static void stage1N3_Test() {
      //Test class constants
      System.out.printf("DEFAULT_ROWS = %d, DEFAULT_COLS = %d, \n"+
                        "MIN_ROWS = %d, MIN_COLS = %d, MAX_COLS = %d\n",
                        YardDog.DEFAULT_ROWS,YardDog.DEFAULT_COLS,
                        YardDog.MIN_ROWS, YardDog.MIN_COLS, YardDog.MAX_COLS);

      //Test 3-parameter constructor and Stage-1 Accessor methods
      YardDog fido = new YardDog(10,10,"Fido");
      System.out.println("YardDog fido = new YardDog(10,10,\"Fido\")"); 
      System.out.printf("Dog named %s has yard with %d rows and %d columns\n",
                        fido.getName(), fido.getNumRows(), fido.getNumColumns());
      fido.print();
      int row = 0, col = 0;
      do {
         try { //try to access fido.elementAt(row,col) specified by user
            System.out.print("Enter a row and column number " + 
                             "(negative values to quit) ");
            row = kbd.nextInt();
            col = kbd.nextInt();
            //The next line should throw an IllegalArgumentException if
            //row or col are invalid index values into fido's yard. 
            System.out.printf("fido.elementAt(%d,%d) is '%c'\n", 
                              row, col, fido.elementAt(row,col));
            System.out.printf("After fido.setElement(%d,%d,'%c'):\n", row, col, '#');
            fido.setElement(row,col,'#');
            fido.print();
         }
         catch(Exception e) {
            System.out.printf("Row %d Column %d caused exception.\n", 
                              row, col);
         }
       }while(row>=0 && col>=0);//negative row or col means quit
      
   }//end stage1N3-Test() method

   /******************************************************************
      Test default constructor, and toString()
   *******************************************************************/
   public static void stage2_Test() {
      YardDog dog = new YardDog(); //default YardDog object
      System.out.println("YardDog dog = new YardDog()");
      System.out.printf("YardDog named %s has yard with %d rows and %d columns\n",
                        dog.getName(), dog.getNumRows(), dog.getNumColumns());
      System.out.println("System.out.print(dog) shows:");
      System.out.print(dog); //tests toString() accessor
   }//end stage2_Test() method
    
   /******************************************************************
      Test boneFound()
   *******************************************************************/
   public static void stage4_Test() {
      //Declare and display default YardDog object
      YardDog dog = new YardDog();
      System.out.print(dog);

      //Make copy of dog's yard
      char[][] yardCopy = new char[dog.DEFAULT_ROWS][dog.DEFAULT_COLS];
      for(int r=0; r<dog.getNumRows(); r++)
         for(int c=0; c<dog.getNumColumns(); c++)
            yardCopy[r][c] = dog.elementAt(r,c);
            
      //Mark with x in yardCopy coordinates that "find" a bone in dog's yard.
      for(int r=0; r<dog.getNumRows(); r++)
         for(int c=0; c<dog.getNumColumns(); c++)
            for(char bone='A'; bone<='C'; bone++)
               if(dog.boneFound(r,c))
                  yardCopy[r][c] = 'x';

      //Copy yardCopy elements into dog's yard
      for(int r=0; r<dog.getNumRows(); r++)
         for(int c=0; c<dog.getNumColumns(); c++)
            for(char bone='A'; bone<='C'; bone++)
               dog.setElement(r,c,yardCopy[r][c]);

      System.out.println("x will be displayed at all locations that \"find\" a bone.");
      System.out.print(dog);
   }//end stage4_Test() method
   
   /******************************************************************
      Test digHoles()
   *******************************************************************/
   public static void stage5_Test() {
      YardDog dog = new YardDog(10,10,"Digger");
      int count = dog.digHoles();
      System.out.printf("%s dug %d holes to locate a bone.\n",
                         dog.getName(), count);
   }//end stage5_Test() method
   
   
}//end of YardDogClient