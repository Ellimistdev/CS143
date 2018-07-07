/**
   PROGRAM: This is a client program to test the 
   YardDog hierarchy of classes along each
   stage of development as described in the Lab 2 Specifications. 

   PROGRAMMER: Nick Rodriguez
   
   ATTENTION PROGRAMMERS!!!
   
   1) Make sure your YardDog.java file (and eventually 
      LeapingDog.java, SmartLeaper.java and BurrowingDog.java)
      is in the same folder as this YardDogClient.java file.
 
   2) Be sure you have uncommented all the methods that
      test the stages of Lab 2 that you implemented in
      part or fully.
   
   3) Be sure you have commented out the lines of the methods
      that test items from Lab 2 that you did not implement.

*/
import java.util.*;
import java.io.*;

public class YardDogClient {

   private static Scanner kbd = new Scanner(System.in);

 	public static void main(String[] args) throws IOException {
      String choice;
      do {
         System.out.println("Stages of testing");
         System.out.println("1. Test modified/abstract YardDog & Leaping dog");
         System.out.println("2. Test BurrowingDog");
         System.out.println("3. Test SmartLeaper");
         System.out.println("4. Test Polymorphism and Casting");
         System.out.println("5. Test Saveable interface");
         System.out.println("6. Exit");
         System.out.print("What stage are you at? ");
         choice = kbd.nextLine();
         System.out.println();
         if(choice.equals("1"))
            stage1_Test();
         else if(choice.equals("2"))
            stage2_Test();
         else if(choice.equals("3"))
            stage3_Test();
         else if(choice.equals("4"))
            stage4_Test();
         else if(choice.equals("5"))
            stage5_Test();
      }while(!choice.equals("6"));
      System.out.println("Good-bye!");
 	}//end main method
   
   /******************************************************************
      Test abstract modified YardDog and LeapingDog
   *******************************************************************/
   public static void stage1_Test() {
      //YardDog is correctly abstract if the next Java statement causes
      //a compiler error. In that case, comment out the statement.
      //YardDog shouldnotWork = new YardDog();
   
      //test alternate LeapingDog constructor
      LeapingDog rover = new LeapingDog(10,10,"Rover");
      System.out.println("LeapingDog rover = new LeapingDog(10,10,\"Rover\")"); 
      System.out.print(rover);
      LeapingDog defaultLeaper = new LeapingDog(); //test default constructor
      System.out.println("After LeapingDog defaultLeaper = new LeapingDog():"); 
      System.out.print(defaultLeaper);

      //test LeapingDog digHoles() and leap() methods
      System.out.println("After defaultLeaper.digHoles() and defaultLeaper.leap():");
      int holeCount = defaultLeaper.digHoles();
      defaultLeaper.leap();
      System.out.print(defaultLeaper);
      System.out.printf("%s dug %d holes.\n", defaultLeaper.getName(), 
                         holeCount);
   }

   
   /******************************************************************
      Test BurrowingDog implementation        
   *******************************************************************/
   public static void stage2_Test() {
      BurrowingDog dog = new BurrowingDog(10,10,"Digger");
      System.out.println("BurrowingDog dog = new BurrowingDog(10,10,\"Digger\")"); 
      System.out.print(dog);
      int holes = dog.digHoles();
      System.out.print("After dog.digHoles(),\n" +
                         "dog yard is:\n" + dog);
      System.out.printf("%s dug %d holes.\n", dog.getName(), holes);  
         
      BurrowingDog dog2 = new BurrowingDog();
      System.out.println("\n\nBurrowingDog dog2 = new BurrowingDog()"); 
      System.out.print(dog2);
      holes = dog2.digHoles();
      dog2.burrow();
      System.out.print("After dog2.digHoles() and dog2.burrow(),\n" +
                         "dog2 yard is:\n" + dog2);
      System.out.printf("%s dug %d holes.\n", dog2.getName(), holes);
   }//end stage2_Test() method
   
   /******************************************************************
      Test SmartLeaper implementation
   *******************************************************************/
   public static void stage3_Test() {
      SmartLeaper newton = new SmartLeaper(); //test default constructor
      System.out.print(newton);
      System.out.println("Default SmartLeaper after digHoles() and leap():"); 
      int numHoles = newton.digHoles();
      newton.leap();
      System.out.print(newton);
      System.out.printf("%s dug %d holes.\n", newton.getName(), 
                         numHoles);  

      //test alternate SmartLeaper constructor
      SmartLeaper einstein = new SmartLeaper(10,10,"Einstein");
      System.out.println("Alternate SmartLeaper after digHoles() and leap():"); 
      numHoles = einstein.digHoles();
      einstein.leap();
      System.out.print(einstein);
      System.out.printf("%s dug %d holes.\n", einstein.getName(), 
                         numHoles);  
   }//end stage3_Test() method
   
   /******************************************************************
      Test Polymorphism and Casting from YardDog to derived class
   *******************************************************************/
   public static void stage4_Test() {
      YardDog[] doggos = { new LeapingDog(), new BurrowingDog(), new SmartLeaper() };
      
      for (YardDog doggo : doggos) {
         doggo.digHoles();
         System.out.println(doggo);
         if (doggo instanceof LeapingDog) {
            System.out.println("Leaping");
            ((LeapingDog) doggo).leap();
            System.out.println(doggo);
         } else if (doggo instanceof BurrowingDog) {
            System.out.println("Burrowing");
            ((BurrowingDog) doggo).burrow();
            System.out.println(doggo);
         }
      }      
   }//end stage4_Test() method
   
   /******************************************************************
      Test implementation of Saveable interface.
         YardDog: save(PrintStream output), retrieve(Scanner input)
         LeaperDog: save(String filename), retrieve(String filename)
         BurrowingDog: save(String filename), retrieve(String filename)
   *******************************************************************/
   public static void stage5_Test() throws IOException {
   /* 
      //Test LeapingDog save() and retrieve() by testing SmartLeaper
      SmartLeaper dogL1 = new SmartLeaper(5,5,"Newton");
      System.out.println("SmartLeaper dogL1 = new SmartLeaper(5,5,\"Newton\");");
      System.out.print("dogL1:\n" + dogL1);
      dogL1.save("saveDogL1.txt");
      System.out.println("dogL1.save(\"saveDogL1.txt\");");

      SmartLeaper dogL2 = new SmartLeaper(10,10,"Einstein");
      System.out.println("SmartLeaper dogL2 = new SmartLeaper(10,l0,\"Einstein\");");
      System.out.print("dogL2:\n" + dogL2);
      dogL2.retrieve("saveDogL1.txt");
      System.out.print("After dogL2.retrieve(\"saveDogL1.txt\")"); 
      System.out.println("dogL2 should now contain dogL1's data:\n" + dogL2);
       
      //Test BurrowingDog save() and retrieve()
      BurrowingDog dogB1 = new BurrowingDog(5,5,"Digger");
      System.out.println("BurrowingDog dogB1 = new BurrowingDog(5,5,\"Digger\");");
      System.out.print("dogB1:\n" + dogB1);
      dogB1.save("saveDogB1.txt");
      System.out.println("dogB1.save(\"saveDogB1.txt\");");

      BurrowingDog dogB2 = new BurrowingDog(10,10,"Rover");
      System.out.println("BurrowingDog dogB2 = new BurrowingDog(10,l0,\"Rover\");");
      System.out.print("dogB2:\n" + dogB2);
      dogB2.retrieve("saveDogB1.txt");
      System.out.print("After dogB2.retrieve(\"saveDogB1.txt\")"); 
      System.out.println("dogB2 should now contain dogB1's data:\n" + dogB2);
      
   */   
   }//end stage5_Test() method   
}//end of YardDogClient